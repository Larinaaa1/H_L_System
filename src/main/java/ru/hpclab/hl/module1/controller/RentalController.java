package ru.hpclab.hl.module1.controller;

import ru.hpclab.hl.module1.dto.RentalDTO;
import ru.hpclab.hl.module1.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // Получить все аренды
    @GetMapping
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
    }

    // Получить аренду по ID
    @GetMapping("/{id}")
    public RentalDTO getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    // Создать новую аренду
    @PostMapping
    public RentalDTO addRental(@RequestBody RentalDTO rentalDTO) {
        return rentalService.saveRental(rentalDTO);
    }

    // Удалить аренду по ID
    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
    }

    // Обновить информацию об аренде
    @PutMapping("/{id}")
    public RentalDTO updateRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {
        return rentalService.updateRental(id, rentalDTO);
    }
}