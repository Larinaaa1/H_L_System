package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCity(String city);
}
//Метод для поиска автомобилей по городу