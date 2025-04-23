/*
package ru.hpclab.hl.module1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")  // Базовый путь для админ-эндпоинтов
public class DataClearController {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final RentalRepository rentalRepository;

    // Инъекция зависимостей через конструктор
    public DataClearController(
            CarRepository carRepository,
            ClientRepository clientRepository,
            RentalRepository rentalRepository
    ) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.rentalRepository = rentalRepository;
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAll() {
        carRepository.deleteAll();
        clientRepository.deleteAll();
        rentalRepository.deleteAll();
        return ResponseEntity.ok("Все данные очищены");
    }


}
*/
