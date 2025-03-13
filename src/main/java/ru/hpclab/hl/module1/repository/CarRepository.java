package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
    private static final List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car("VIN1", "Model1", "Red", 50.0, "City1", "Salon1"));
        cars.add(new Car("VIN2", "Model2", "Blue", 60.0, "City2", "Salon2"));
    }

    public List<Car> findAll() {
        return cars;
    }

    public Car findByVin(String vin) {
        return cars.stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst()
                .orElse(null);
    }

}
