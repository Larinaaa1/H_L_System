package ru.hpclab.hl.module1.controller;

import org.springframework.http.ResponseEntity;
import ru.hpclab.hl.module1.dto.RentalDTO;
import ru.hpclab.hl.module1.service.RentalService;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;


import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final ObservabilityService observabilityService;

    public RentalController(RentalService rentalService,ObservabilityService observabilityService) {
        this.rentalService = rentalService;
        this.observabilityService = observabilityService;
    }

    // Получить все аренды
    @GetMapping
    public List<RentalDTO> getAllRentals() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllRentals");
        List<RentalDTO> temp = rentalService.getAllRentals();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllRentals");
        return temp;
    }

    // Получить аренду по ID
    @GetMapping("/{id}")
    public RentalDTO getRentalById(@PathVariable Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getRentalById");
        RentalDTO temp = rentalService.getRentalById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":getRentalById");
        return temp;
    }

    // Создать новую аренду
    @PostMapping
    public RentalDTO addRental(@RequestBody RentalDTO rentalDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":addRental");
        RentalDTO temp = rentalService.saveRental(rentalDTO);
        this.observabilityService.stop(getClass().getSimpleName() + ":addRental");
        return temp;
    }

    // Удалить аренду по ID
    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteRental");
        rentalService.deleteRental(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteRental");
    }

    // Обновить информацию об аренде
    @PutMapping("/{id}")
    public RentalDTO updateRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateRental");
        RentalDTO temp = rentalService.updateRental(id, rentalDTO);
        this.observabilityService.stop(getClass().getSimpleName() + ":updateRental");
        return temp;
    }

    // Удалить данные
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAllRentals() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllRentals");
        rentalService.clearAll();
        ResponseEntity<String> temp = ResponseEntity.ok("Все аренды были удалены");
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllRentals");
        return temp;
    }
}