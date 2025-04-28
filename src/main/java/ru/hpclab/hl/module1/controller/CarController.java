package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import ru.hpclab.hl.module1.dto.CarDTO;
import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.mapper.CarMapper;
import ru.hpclab.hl.module1.service.CarService;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final ObservabilityService observabilityService;


    // Получить все автомобили
    @GetMapping
    public List<CarDTO> getAllCars() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllCars");
        List<CarDTO> temp = carService.getAllCars();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllCars");
        return temp;
    }

    // Получить автомобиль по ID
    @GetMapping("/{id}")
    public CarDTO getCarById(@PathVariable Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getCarById");
        CarDTO temp = carService.getCarById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":getCarById");
        return temp;
    }

    // Создать новый автомобиль
    @PostMapping
    public CarDTO addCar(@RequestBody CarDTO carDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":addCar");
        CarDTO temp = carService.saveCar(carDTO);
        this.observabilityService.stop(getClass().getSimpleName() + ":addCar");
        return temp;
    }

    // Обновить информацию об автомобиле
    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateCar");
        CarDTO temp = carService.updateCar(id, carDTO);
        this.observabilityService.stop(getClass().getSimpleName() + ":updateCar");
        return temp;
    }

    // Удалить автомобиль по ID
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteCar");
        carService.deleteCar(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteCar");
    }


  //   Проверить доступность автомобиля на указанный период
//    @GetMapping("/availability")
//    public CarDTO getAvailableCarInfo(
//            @RequestParam String city,
//         @RequestParam LocalDate startDate,
//            @RequestParam LocalDate endDate) {
//        return carService.getAvailableCarInfo(city, startDate, endDate);
//    }

    // Удалить данные
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAllCars() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllCars");
        carService.clearAll();
        ResponseEntity<String> temp = ResponseEntity.ok("Все машины были удалены");
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllCars");
        return temp;
    }
}