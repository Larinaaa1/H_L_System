package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Rental;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class RentalRepository {
    private static final List<Rental> rentals = new ArrayList<>();
    private static long nextId = 1;

    public List<Rental> findAll() {
        return rentals;
    }


    // Найти аренду по ID
    public Optional<Rental> findById(Long id) {
        return rentals.stream()
                .filter(rental -> rental.getId().equals(id))
                .findFirst();
    }

    // Сохранить аренду
    public void save(Rental rental) {
        if (rental.getId() == null) {
            rental.setId(nextId++); // Генерация ID
        }
        rentals.add(rental);
    }

    // Удалить аренду по ID
    public void deleteById(Long id) {
        rentals.removeIf(rental -> rental.getId().equals(id));
    }
}
