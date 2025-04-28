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
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

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
    private final ObservabilityService observabilityService;

    public List<RentalDTO> getAllRentals() {
        observabilityService.start(getClass().getSimpleName() + ":getAllRentals");
        List<RentalDTO> rentals = rentalRepository.findAll().stream()
                .map(RentalMapper::toDTO)
                .collect(Collectors.toList());
        observabilityService.stop(getClass().getSimpleName() + ":getAllRentals");
        return rentals;
    }

    public RentalDTO getRentalById(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":getRentalById");
        Optional<Rental> rentalEntity = rentalRepository.findById(id);
        RentalDTO result = rentalEntity.map(RentalMapper::toDTO).orElse(null);
        observabilityService.stop(getClass().getSimpleName() + ":getRentalById");
        return result;
    }

    public RentalDTO saveRental(RentalDTO rentalDTO) {
        observabilityService.start(getClass().getSimpleName() + ":saveRental");
        Car car = carRepository.findById(rentalDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Автомобиль не найден"));

        Client client = clientRepository.findById(rentalDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        Rental rentalEntity = RentalMapper.toEntity(rentalDTO, car, client);
        RentalDTO result = RentalMapper.toDTO(rentalRepository.save(rentalEntity));
        observabilityService.stop(getClass().getSimpleName() + ":saveRental");
        return result;
    }

    public void deleteRental(Long id) {
        observabilityService.start(getClass().getSimpleName() + ":deleteRental");
        rentalRepository.deleteById(id);
        observabilityService.stop(getClass().getSimpleName() + ":deleteRental");
    }

    public RentalDTO updateRental(Long id, RentalDTO updatedRentalDTO) {
        observabilityService.start(getClass().getSimpleName() + ":updateRental");
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

            RentalDTO result = RentalMapper.toDTO(rentalRepository.save(rentalEntity));
            observabilityService.stop(getClass().getSimpleName() + ":updateRental");
            return result;
        }
        observabilityService.stop(getClass().getSimpleName() + ":updateRental");
        return null;
    }

    public void clearAll() {
        observabilityService.start(getClass().getSimpleName() + ":clearAll");
        rentalRepository.deleteAll();
        observabilityService.stop(getClass().getSimpleName() + ":clearAll");
    }
}
