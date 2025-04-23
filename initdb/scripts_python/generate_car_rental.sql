
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
        
BEGIN TRANSACTION;
INSERT INTO t_car (vin, model, color, rental_cost_per_day, city, salon_name, status) VALUES ('221', 'Lamborghini', 'blue', 1712.49, 'Sochi', 'Premium', 'RENT');
INSERT INTO t_car (vin, model, color, rental_cost_per_day, city, salon_name, status) VALUES ('877', 'Mazda', 'black', 1133.42, 'Moscow', 'Star', 'RENT');
INSERT INTO t_car (vin, model, color, rental_cost_per_day, city, salon_name, status) VALUES ('891', 'Cherry', 'blue', 1315.35, 'Saint Petersburg', 'Star', 'FREE');
INSERT INTO t_car (vin, model, color, rental_cost_per_day, city, salon_name, status) VALUES ('363', 'Hyundai', 'red', 1438.2, 'Moscow', 'Star', 'RENT');
INSERT INTO t_car (vin, model, color, rental_cost_per_day, city, salon_name, status) VALUES ('426', 'Lamborghini', 'red', 2253.13, 'Kazan', 'Gold', 'FREE');
INSERT INTO t_client (full_name, driver_license, phone_number) VALUES ('Иван Иванов', '1535-D', '89674562415');
INSERT INTO t_client (full_name, driver_license, phone_number) VALUES ('Павел Алексеев', '2487-D', '89674785617');
INSERT INTO t_client (full_name, driver_license, phone_number) VALUES ('Андрей Дубинин', '4658-D', '89677514863');
INSERT INTO t_client (full_name, driver_license, phone_number) VALUES ('Анастасия Алексеева', '7513-D', '89677854123');
INSERT INTO t_client (full_name, driver_license, phone_number) VALUES ('Маргарита Петренко', '9514-D', '89679754238');
INSERT INTO t_rental (car_id, client_id, start_date, end_date, total_cost) VALUES (1, 3, '2025-04-19', '2025-06-07', 1821.69);
INSERT INTO t_rental (car_id, client_id, start_date, end_date, total_cost) VALUES (4, 5, '2025-04-28', '2025-05-14', 1401.61);
INSERT INTO t_rental (car_id, client_id, start_date, end_date, total_cost) VALUES (3, 3, '2025-04-12', '2025-06-04', 1421.98);
INSERT INTO t_rental (car_id, client_id, start_date, end_date, total_cost) VALUES (3, 2, '2025-05-03', '2025-06-06', 3301.17);
INSERT INTO t_rental (car_id, client_id, start_date, end_date, total_cost) VALUES (2, 2, '2025-05-02', '2025-05-19', 3813.27);
COMMIT;
