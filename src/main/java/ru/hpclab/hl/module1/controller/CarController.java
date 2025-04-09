package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import ru.hpclab.hl.module1.dto.CarDTO;
import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.mapper.CarMapper;
import ru.hpclab.hl.module1.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;




    // Получить все автомобили
    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    // Получить автомобиль по ID
    @GetMapping("/{id}")
    public CarDTO getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    // Создать новый автомобиль
    @PostMapping
    public CarDTO addCar(@RequestBody CarDTO carDTO) {
        return carService.saveCar(carDTO);
    }

    // Обновить информацию об автомобиле
    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        return carService.updateCar(id, carDTO);
    }

    // Удалить автомобиль по ID
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    // Проверить доступность автомобиля на указанный период
    @GetMapping("/availability")
    public CarDTO getAvailableCarInfo(
            @RequestParam String city,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return carService.getAvailableCarInfo(city, startDate, endDate);
    }

    // Удалить данные
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAllCars() {
        carService.clearAll();
        return ResponseEntity.ok("Все машины были удалены");
    }
}