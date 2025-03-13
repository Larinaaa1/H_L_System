package ru.hpclab.hl.module1.controller;

import ru.hpclab.hl.module1.model.Rental;
import ru.hpclab.hl.module1.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")

public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    // Получить аренду по ID
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    public void rentCar(@RequestBody Rental rental) {
        rentalService.rentCar(rental);
    }

    // Удалить аренду по ID
    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
    }

    // Обновить информацию об аренде
    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable Long id, @RequestBody Rental updatedRental) {
        return rentalService.updateRental(id, updatedRental);
    }

}