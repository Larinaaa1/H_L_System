package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.CarDTO;
import ru.hpclab.hl.module1.dto.RentalDTO;
import ru.hpclab.hl.module1.mapper.CarMapper;
import ru.hpclab.hl.module1.model.Rental;
import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.model.Client;
import ru.hpclab.hl.module1.mapper.RentalMapper;
import ru.hpclab.hl.module1.repository.RentalRepository;
import ru.hpclab.hl.module1.repository.CarRepository;
import ru.hpclab.hl.module1.repository.ClientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(RentalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RentalDTO getRentalById(Long id) {
        Optional<Rental> rentalEntity = rentalRepository.findById(id);
        return rentalEntity.map(RentalMapper::toDTO).orElse(null);
    }

    public RentalDTO saveRental(RentalDTO rentalDTO) {
        Car car = carRepository.findById(rentalDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));

        Client client = clientRepository.findById(rentalDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        Rental rentalEntity = RentalMapper.toEntity(rentalDTO, car, client);
        return RentalMapper.toDTO(rentalRepository.save(rentalEntity));
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    public RentalDTO updateRental(Long id, RentalDTO updatedRentalDTO) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        if (rentalOptional.isPresent()) {
            Rental rentalEntity = rentalOptional.get();

            Car car = carRepository.findById(updatedRentalDTO.getCarId())
                    .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));

            Client client = clientRepository.findById(updatedRentalDTO.getClientId())
                    .orElseThrow(() -> new RuntimeException("Клиент не найден"));

            rentalEntity.setCar(car);
            rentalEntity.setClient(client);
            rentalEntity.setStartDate(updatedRentalDTO.getStartDate());
            rentalEntity.setEndDate(updatedRentalDTO.getEndDate());
            rentalEntity.setTotalCost(updatedRentalDTO.getTotalCost());

            return RentalMapper.toDTO(rentalRepository.save(rentalEntity));
        }
        return null;
    }


    public CarDTO getAvailableCarInfo(String city, LocalDate startDate, LocalDate endDate) {
        // Получаем все автомобили в указанном городе
        List<Car> carsInCity = carRepository.findByCity(city);

        // Получаем все аренды, которые пересекаются с указанным периодом
        List<Rental> overlappingRentals = rentalRepository.findOverlappingRentals(startDate, endDate);

        Car result = carsInCity.stream()
                .filter(car -> overlappingRentals.stream()
                        .noneMatch(rental -> rental.getCar().getId().equals(car.getId())))
                .findFirst() // Возвращаем первый доступный автомобиль
                .orElse(null); // Если доступных автомобилей нет, возвращаем null

        // Находим первый доступный автомобиль
        return CarMapper.toDto(result);
    }
}