import argparse
import random
from faker import Faker
import requests
from datetime import datetime, timedelta

fake = Faker()

BASE_URL = "http://localhost:8080"

def clear_all():
    requests.delete(f"{BASE_URL}/cars/clear")
    requests.delete(f"{BASE_URL}/clients/clear")
    requests.delete(f"{BASE_URL}/rentals/clear")

def clear_endpoint(endpoint):
    requests.delete(f"{BASE_URL}/{endpoint}/clear")

def generate_car():
    brands = ["Toyota", "BMW", "Mercedes", "Audi", "Ford", "Tesla"]
    models = {
        "Toyota": ["Camry", "Corolla", "RAV4"],
        "BMW": ["X5", "M5", "i8"],
        "Mercedes": ["C-Class", "E-Class", "S-Class"],
        "Audi": ["A4", "Q7", "RS6"],
        "Ford": ["Focus", "Mustang", "Explorer"],
        "Tesla": ["Model S", "Model 3", "Model X"]
    }
    brand = random.choice(brands)
    model = random.choice(models[brand])
    return {
        "vin": fake.unique.bothify(text='??#####???'),  # 10 символов для VIN
        "model": f"{brand} {model}",
        "color": fake.safe_color_name(),
        "rentalCostPerDay": round(random.uniform(20.0, 200.0), 2),
        "city": fake.city(),
        "salonName": f"{brand} Center {fake.city()}"
    }

def generate_client():
    return {
        "fullName": fake.name(),
        "driverLicense": fake.unique.bothify(text='??######????'),  # Формат водительских прав
        "phoneNumber": fake.unique.numerify('+7-9##-###-##-##')
    }

def generate_rental(client_id, car_id):
    start_date = datetime.now() + timedelta(days=random.randint(1, 10))
    end_date = start_date + timedelta(days=random.randint(1, 30))
    days = (end_date - start_date).days

    # Получаем стоимость аренды автомобиля за день
    car_response = requests.get(f"{BASE_URL}/cars/{car_id}")
    rental_cost_per_day = car_response.json()["rentalCostPerDay"] if car_response.ok else 100.0

    total_cost = round(days * rental_cost_per_day, 2)

    return {
        "carId": car_id,
        "clientId": client_id,
        "startDate": start_date.strftime("%Y-%m-%d"),
        "endDate": end_date.strftime("%Y-%m-%d"),
        "totalCost": total_cost
    }

def get_client_id_by_license(driver_license):
    """Получаем ID клиента по номеру прав через GET /clients"""
    clients = requests.get(f"{BASE_URL}/clients").json()
    for client in clients:
        if client.get("driverLicense") == driver_license:
            return client.get("id")
    return None

def get_car_id_by_vin(vin):
    """Получаем ID машины по VIN через GET /cars"""
    cars = requests.get(f"{BASE_URL}/cars").json()
    for car in cars:
        if car.get("vin") == vin:
            return car.get("id")
    return None

def populate(endpoint, count, clear=False):
    if clear:
        clear_endpoint(endpoint)
        if count == 0:  # Если только очистка без добавления данных
            print(f"Таблица {endpoint} успешно очищена")
            return

    if endpoint == "cars":
        clear_endpoint("cars")
        for _ in range(count):
            data = generate_car()
            response = requests.post(f"{BASE_URL}/cars", json=data)
            if not response.ok:
                print(f"Ошибка создания машины: {response.status_code}")
                print("Ответ сервера:", response.text)

    elif endpoint == "clients":
        clear_endpoint("clients")
        for _ in range(count):
            data = generate_client()
            response = requests.post(f"{BASE_URL}/clients", json=data)
            if not response.ok:
                print(f"Ошибка создания клиента: {response.status_code}")
                print("Ответ сервера:", response.text)

    elif endpoint == "rentals":
        clear_endpoint("rentals")
        # Создаем клиентов и сохраняем их номера прав
        driver_licenses = []
        for _ in range(count):
            client_data = generate_client()
            response = requests.post(f"{BASE_URL}/clients", json=client_data)
            if response.ok:
                driver_licenses.append(client_data["driverLicense"])
            else:
                print(f"Error creating client: {response.status_code}")

        # Создаем машины и сохраняем их VIN
        car_vins = []
        for _ in range(count):
            car_data = generate_car()
            response = requests.post(f"{BASE_URL}/cars", json=car_data)
            if response.ok:
                car_vins.append(car_data["vin"])
            else:
                print(f"Error creating car: {response.status_code}")

        # Создаем аренды
        successful_rentals = 0
        for _ in range(count):
            if not driver_licenses or not car_vins:
                print("Not enough clients or cars")
                break

            # Получаем случайные license и vin
            license = random.choice(driver_licenses)
            vin = random.choice(car_vins)

            # Получаем ID через GET-запросы
            client_id = get_client_id_by_license(license)
            car_id = get_car_id_by_vin(vin)

            if not client_id or not car_id:
                print(f"Warning: Client or car not found (license: {license}, vin: {vin})")
                continue

            rental_data = generate_rental(client_id, car_id)
            response = requests.post(f"{BASE_URL}/rentals", json=rental_data)
            if response.ok:
                successful_rentals += 1
            else:
                print(f"Error creating rental: {response.status_code}")

        print(f"Successfully created {successful_rentals}/{count} rentals")

    else:
        print(f"Unknown endpoint: {endpoint}")


def main():
    parser = argparse.ArgumentParser(description="Test data generator for Car Rental System")
    parser.add_argument("--count", type=int, default=500, help="Number of objects to create")
    parser.add_argument("--endpoint", type=str, required=True,
                        choices=["cars", "clients", "rentals", "all"],
                        help="API endpoint to populate")
    parser.add_argument("--clear", action="store_true",
                        help="Clear the table before populating")
    args = parser.parse_args()

    if args.endpoint == "all":
        if args.clear:
            clear_all()
        if args.count > 0:
            populate("cars", args.count, args.clear)
            populate("clients", args.count, args.clear)
            populate("rentals", args.count, args.clear)
    else:
        populate(args.endpoint, args.count, args.clear)

if __name__ == "__main__":
    main()
