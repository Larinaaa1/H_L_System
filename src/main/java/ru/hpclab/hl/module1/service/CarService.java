package ru.hpclab.hl.module1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.CarDTO;
import ru.hpclab.hl.module1.mapper.CarMapper;
import ru.hpclab.hl.module1.mapper.RentalMapper;
import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.model.Client;
import ru.hpclab.hl.module1.model.Rental;
import ru.hpclab.hl.module1.repository.CarRepository;
import ru.hpclab.hl.module1.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final RentalService rentalService;

    // Получить все автомобили
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }

    // Получить автомобиль по ID
    public CarDTO getCarById(Long id) {
        Optional<Car> carEntity = carRepository.findById(id);
        return carEntity.map(CarMapper::toDto).orElse(null);
    }

    @Transactional
    // Сохранить автомобиль
    public CarDTO saveCar(CarDTO carDTO) {
        Car car = CarMapper.toEntity(carDTO);
        Car savedCar = carRepository.save(car);
        return CarMapper.toDto(savedCar);
    }

    // Удалить автомобиль по ID
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    // Обновить информацию об автомобиле
    public CarDTO updateCar(Long id, CarDTO updatedCarDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));

        car.setVin(updatedCarDTO.getVin());
        car.setModel(updatedCarDTO.getModel());
        car.setColor(updatedCarDTO.getColor());
        car.setRentalCostPerDay(updatedCarDTO.getRentalCostPerDay());
        car.setCity(updatedCarDTO.getCity());
        car.setSalonName(updatedCarDTO.getSalonName());

        Car updatedCar = carRepository.save(car);

        return CarMapper.toDto(updatedCar);

    }
    //
    // Проверить доступность автомобиля на указанный период
  //  public CarDTO getAvailableCarInfo(String city, LocalDate startDate, LocalDate endDate) {
  //      return rentalService.getAvailableCarInfo(city, startDate, endDate);
   // }

    // Метод очистки данных
    public void clearAll() {
        carRepository.deleteAll();
    }
}