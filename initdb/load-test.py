import requests
import sys
import time
 =
# Параметры: URL, соотношение запросов (например, "5/95"), общее количество запросов
url = sys.argv[1]
ratio = sys.argv[2]
total_requests = int(sys.argv[3])

read_percent = int(ratio.split('/')[1])
write_percent100 - read_percent

for i in range(total_requests):
    if i % 100 < read_percent:
        # Чтение
        requests.get(f"{url}/api/read-endpoint")
    else:
        # Запись
        requests.post(f"{url}/api/write-endpoint", json={...})
    time.sleep(0.01)  # небольшая пауза