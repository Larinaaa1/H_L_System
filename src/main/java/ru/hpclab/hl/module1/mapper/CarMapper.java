package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.CarDTO;
import ru.hpclab.hl.module1.model.Car;

public class CarMapper {

    private CarMapper() {
    }

    /**
     * Преобразование из Entity в DTO
     */
    public static CarDTO toDto(Car entity) {
        if (entity == null) return null;

        return new CarDTO(
                entity.getId(),
                entity.getVin(),
                entity.getModel(),
                entity.getColor(),
                entity.getRentalCostPerDay(),
                entity.getCity(),
                entity.getSalonName()
        );
    }



    /**
     * Преобразование из DTO в Entity
     */
    public static Car toEntity(CarDTO dto) {
        if (dto == null) return null;

        return new Car(
                dto.getId(),
                dto.getVin(),
                dto.getModel(),
                dto.getColor(),
                dto.getRentalCostPerDay(),
                dto.getCity(),
                dto.getSalonName()
        );
    }

}