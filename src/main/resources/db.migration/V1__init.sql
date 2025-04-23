
---- Создание таблицы автомобилей
    CREATE TABLE t_car (
        id BIGSERIAL PRIMARY KEY,          -- Уникальный идентификатор автомобиля
        vin VARCHAR(17) UNIQUE NOT NULL,  -- VIN автомобиля (уникальный)
        model VARCHAR(100) NOT NULL,      -- Модель автомобиля
        color VARCHAR(50) NOT NULL,       -- Цвет автомобиля
        rental_cost_per_day DECIMAL(10, 2) NOT NULL, -- Стоимость аренды в день
        city VARCHAR(100) NOT NULL,       -- Город, где находится автомобиль
        salon_name VARCHAR(255) NOT NULL  -- Название салона
        status VARCHAR(50) NOT NULL CHECK (status IN ('RENT', 'FREE'))
    );

    -- Создание таблицы клиентов
    CREATE TABLE t_client (
        id BIGSERIAL PRIMARY KEY,         -- Уникальный идентификатор клиента
        full_name VARCHAR(255) NOT NULL,  -- ФИО клиента
        driver_license VARCHAR(50) UNIQUE NOT NULL, -- Номер водительских прав (уникальный)
        phone_number VARCHAR(20) NOT NULL -- Номер телефона клиента
    );

    -- Создание таблицы аренд
    CREATE TABLE t_rental (
        id BIGSERIAL PRIMARY KEY,         -- Уникальный идентификатор аренды
        car_id BIGINT NOT NULL REFERENCES t_car(id) ON DELETE CASCADE, -- Связь с автомобилем
        client_id BIGINT NOT NULL REFERENCES t_client(id) ON DELETE CASCADE, -- Связь с клиентом
        start_date DATE NOT NULL,         -- Дата начала аренды
        end_date DATE NOT NULL,           -- Дата окончания аренды
        total_cost DECIMAL(10, 2) NOT NULL -- Итоговая стоимость аренды
        );

    -- Создание индексов
    CREATE INDEX idx_car_vin ON t_car (vin); -- Индекс по VIN автомобиля
    CREATE INDEX idx_client_driver_license ON t_client (driver_license); -- Индекс по водительским правам
    CREATE INDEX idx_rental_dates ON t_rental (start_date, end_date); -- Индекс по датам аренды
