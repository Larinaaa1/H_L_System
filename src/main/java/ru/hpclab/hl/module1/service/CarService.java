package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private static List<Car> cars = new ArrayList<>();
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalService rentalService;

    public List<Car> getAllCars() {
        return cars;
    }

    // Получить автомобиль по VIN
    public Car getCarByVin(String vin) {
        return cars.stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst()
                .orElse(null);
    }

    public Car saveCar(Car car) {
        cars.add(car);
        return car;
    }

    public void deleteCar(String vin) {
        cars.removeIf(car -> car.getVin().equals(vin));
    }

    // Обновить информацию об автомобиле
    public Car updateCar(String vin, Car updatedCar) {
        Optional<Car> carOptional = cars.stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst();
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            car.setModel(updatedCar.getModel());
            car.setColor(updatedCar.getColor());
            car.setRentalCostPerDay(updatedCar.getRentalCostPerDay());
            car.setCity(updatedCar.getCity());
            car.setSalonName(updatedCar.getSalonName());
            return car;
        }
        return null;
    }

    // Проверить доступность автомобиля на указанный период
    public boolean isCarAvailable(String city, LocalDate startDate, LocalDate endDate) {
        return rentalService.isCarAvailable(city, startDate, endDate);
    }

}




