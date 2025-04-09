# Нагрузка - это количество виртуальных пользователей - "vus"
# Время отклика - это "ms"

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

sns.set_style("whitegrid")


def plot_response_vs_load_from_csv(csv_file):
    # Загрузка данных
    df = pd.read_csv(csv_file)

    # Фильтрация данных для vus и http_req_duration
    df_vus = df[df["metric_name"] == "vus"][["timestamp", "metric_value"]]
    df_vus.rename(columns={"metric_value": "vus"}, inplace=True)

    df_http_req_duration = df[df["metric_name"] == "http_req_duration"][["timestamp", "metric_value"]]
    df_http_req_duration.rename(columns={"metric_value": "http_req_duration"}, inplace=True)

    # Преобразование timestamp в datetime
    df_vus["timestamp"] = pd.to_datetime(df_vus["timestamp"], unit="s")
    df_http_req_duration["timestamp"] = pd.to_datetime(df_http_req_duration["timestamp"], unit="s")

    # Объединение данных по ближайшему timestamp
    df_merged = pd.merge_asof(df_http_req_duration.sort_values("timestamp"),
                              df_vus.sort_values("timestamp"),
                              on="timestamp", direction="nearest")

    # Создание графика
    plt.figure(figsize=(12, 8))

    # Точечный график с улучшенными параметрами
    plt.scatter(df_merged["vus"], df_merged["http_req_duration"],
                color="dodgerblue", alpha=0.7, s=60, edgecolors="white", linewidth=0.5,
                label="Время отклика (мс)")

    

    # Настройка сетки и осей
    plt.grid(True, linestyle="--", linewidth=0.6, alpha=0.7)

    # Автоматическое определение шага для осей
    vus_step = max(2, round(df_merged["vus"].max() / 10))
    duration_step = max(500, round(df_merged["http_req_duration"].max() / 10))

    plt.xticks(np.arange(0, df_merged["vus"].max() + vus_step, step=vus_step))
    plt.yticks(np.arange(0, df_merged["http_req_duration"].max() + duration_step, step=duration_step))

    # Подписи и заголовок
    plt.xlabel("Нагрузка (количество виртуальных пользователей)", fontsize=12)
    plt.ylabel("Время отклика (мс)", fontsize=12)
    plt.title("Зависимость времени отклика от нагрузки", fontsize=14, pad=20)

    # Легенда и дополнительные элементы
    plt.legend(fontsize=10)
    plt.tight_layout()

    # Показать график
    plt.show()


plot_response_vs_load_from_csv("file.csv")