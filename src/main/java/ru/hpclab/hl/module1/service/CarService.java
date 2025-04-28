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
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

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
    private final ObservabilityService observabilityService;

    // Получить все автомобили
    public List<CarDTO> getAllCars() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllCars");
        List<CarDTO> temp = carRepository.findAll().stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllCars");
        return temp;
    }

    // Получить автомобиль по ID
    public CarDTO getCarById(Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getCarById");
        Optional<Car> carEntity = carRepository.findById(id);
        CarDTO temp = carEntity.map(CarMapper::toDto).orElse(null);
        this.observabilityService.stop(getClass().getSimpleName() + ":getCarById");
        return temp;
    }

    @Transactional
    // Сохранить автомобиль
    public CarDTO saveCar(CarDTO carDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":saveCar");
        Car car = CarMapper.toEntity(carDTO);
        Car savedCar = carRepository.save(car);
        CarDTO savedCarDTO = CarMapper.toDto(savedCar);
        this.observabilityService.stop(getClass().getSimpleName() + ":saveCar");
        return savedCarDTO;
    }

    // Удалить автомобиль по ID
    public void deleteCar(Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteCar");
        carRepository.deleteById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteCar");
    }

    // Обновить информацию об автомобиле
    public CarDTO updateCar(Long id, CarDTO updatedCarDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateCar");
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));

        car.setVin(updatedCarDTO.getVin());
        car.setModel(updatedCarDTO.getModel());
        car.setColor(updatedCarDTO.getColor());
        car.setRentalCostPerDay(updatedCarDTO.getRentalCostPerDay());
        car.setCity(updatedCarDTO.getCity());
        car.setSalonName(updatedCarDTO.getSalonName());

        Car updatedCar = carRepository.save(car);
        CarDTO updatedCarDTOResult = CarMapper.toDto(updatedCar);
        this.observabilityService.stop(getClass().getSimpleName() + ":updateCar");

        return updatedCarDTOResult;

    }
    //
    // Проверить доступность автомобиля на указанный период
  //  public CarDTO getAvailableCarInfo(String city, LocalDate startDate, LocalDate endDate) {
  //      return rentalService.getAvailableCarInfo(city, startDate, endDate);
   // }

    // Метод очистки данных
    public void clearAll() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAll");
        carRepository.deleteAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAll");
    }
}