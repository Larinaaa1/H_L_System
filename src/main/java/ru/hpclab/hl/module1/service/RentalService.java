package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Rental;
import ru.hpclab.hl.module1.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // Получить аренду по ID
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public void rentCar(Rental rental) {
        rentalRepository.save(rental);
    }

    // Удалить аренду по ID
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    // Обновить информацию об аренде
    public Rental updateRental(Long id, Rental updatedRental) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            rental.setCar(updatedRental.getCar());
            rental.setClient(updatedRental.getClient());
            rental.setStartDate(updatedRental.getStartDate());
            rental.setEndDate(updatedRental.getEndDate());
            rental.setTotalCost(updatedRental.getTotalCost());
            return rental;
        }
        return null;
    }

}
