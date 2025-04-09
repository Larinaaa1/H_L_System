import random
from datetime import datetime, timedelta

def generate_sql(filename, num_cars=5, num_clients=5, num_rentals=5):
    # Списки для генерации данных
    car_models = ["Hyundai", "Cherry", "Skoda", "Mazda", "Lamborghini"]
    colors = ["white", "black", "grey", "red", "blue"]
    cities = ["Sochi", "Moscow", "Saint Petersburg", "Kazan", "Novosibirsk"]
    salon_names = ["Star", "Premium", "Luxury", "Elite", "Gold"]
    rental_statuses = ["RENT", "FREE"]

    # Данные о клиентах
    clients = [
        ("Иван Иванов", "1535-D", "89674562415"),
        ("Павел Алексеев", "2487-D", "89674785617"),
        ("Андрей Дубинин", "4658-D", "89677514863"),
        ("Анастасия Алексеева", "7513-D", "89677854123"),
        ("Маргарита Петренко", "9514-D", "89679754238")
    ]

    with open(filename, 'w', encoding='utf-8') as f:
        # Создание таблиц
        f.write("""
        BEGIN TRANSACTION;

        CREATE TABLE t_car (
            id BIGSERIAL PRIMARY KEY,
            vin VARCHAR(17) UNIQUE NOT NULL,
            model VARCHAR(100),
            color VARCHAR(50),
            rental_cost_per_day DECIMAL(10,2),
            city VARCHAR(100),
            salon_name VARCHAR(255),
            status VARCHAR(50) NOT NULL CHECK (status IN ('RENT', 'FREE'))
        );

        CREATE TABLE t_client (
            id BIGSERIAL PRIMARY KEY,
            full_name VARCHAR(255) NOT NULL,
            driver_license VARCHAR(50) UNIQUE NOT NULL,
            phone_number VARCHAR(20)
        );

        CREATE TABLE t_rental (
            id BIGSERIAL PRIMARY KEY,
            car_id BIGINT NOT NULL REFERENCES t_car(id) ON DELETE CASCADE,
            client_id BIGINT NOT NULL REFERENCES t_client(id) ON DELETE CASCADE,
            start_date DATE NOT NULL,
            end_date DATE NOT NULL,
            total_cost DECIMAL(10,2) NOT NULL
        );

        COMMIT;
        """)

        # Начало транзакции для вставки данных
        f.write("\nBEGIN TRANSACTION;\n")

        # Генерация данных для таблицы t_car
        for i in range(1, num_cars + 1):
            vin = str(random.randint(100, 999))  # Генерация случайного VIN
            model = random.choice(car_models)
            color = random.choice(colors)
            rental_cost_per_day = round(random.uniform(1000, 3000), 2)  # Стоимость аренды от 1000 до 3000
            city = random.choice(cities)
            salon_name = random.choice(salon_names)
            status = random.choice(rental_statuses)  # Случайный статус аренды
            f.write(
                f"INSERT INTO t_car (vin, model, color, rental_cost_per_day, city, salon_name, status) "
                f"VALUES ('{vin}', '{model}', '{color}', {rental_cost_per_day}, '{city}', '{salon_name}', '{status}');\n"
            )

        # Генерация данных для таблицы t_client
        for full_name, driver_license, phone_number in clients:
            f.write(
                f"INSERT INTO t_client (full_name, driver_license, phone_number) "
                f"VALUES ('{full_name}', '{driver_license}', '{phone_number}');\n"
            )

        # Генерация данных для таблицы t_rental
        for i in range(1, num_rentals + 1):
            car_id = random.randint(1, num_cars)  # Случайный car_id
            client_id = random.randint(1, num_clients)  # Случайный client_id
            start_date = (datetime.now() + timedelta(days=random.randint(1, 30))).strftime('%Y-%m-%d')  # Случайная дата начала
            end_date = (datetime.now() + timedelta(days=random.randint(31, 60))).strftime('%Y-%m-%d')  # Случайная дата окончания
            total_cost = round(random.uniform(1000, 5000), 2)  # Случайная итоговая стоимость
            f.write(
                f"INSERT INTO t_rental (car_id, client_id, start_date, end_date, total_cost) "
                f"VALUES ({car_id}, {client_id}, '{start_date}', '{end_date}', {total_cost});\n"
            )

        # Завершение транзакции
        f.write("COMMIT;\n")

# Генерация SQL-файла
generate_sql("./scripts_python/generate_car_rental.sql", num_cars=5, num_clients=5, num_rentals=5)