package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.RentalDTO;
import ru.hpclab.hl.module1.model.Rental;
import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.model.Client;

public class RentalMapper {
    private RentalMapper() {
    }

    public static Rental toEntity(RentalDTO dto, Car car, Client client) {
        if (dto == null) return null;

        return new Rental(
                dto.getId(),
                car,
                client,
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getTotalCost()
        );
    }

    public static RentalDTO toDTO(Rental entity) {
        if (entity == null) return null;

        return new RentalDTO(
                entity.getId(),
                entity.getCar().getId(),
                entity.getClient().getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTotalCost()
        );
    }
}