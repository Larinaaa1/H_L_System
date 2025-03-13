package ru.hpclab.hl.module1.controller;

import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Получить все автомобили
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // Получить автомобиль по VIN
    @GetMapping("/{vin}")
    public Car getCarByVin(@PathVariable String vin) {
        return carService.getCarByVin(vin);
    }

    // Создать новый автомобиль
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    // Удалить автомобиль по VIN
    @DeleteMapping("/{vin}")
    public void deleteCar(@PathVariable String vin) {
        carService.deleteCar(vin);
    }

    // Обновить информацию об автомобиле
    @PutMapping("/{vin}")
    public Car updateCar(@PathVariable String vin, @RequestBody Car updatedCar) {
        return carService.updateCar(vin, updatedCar);
    }
}