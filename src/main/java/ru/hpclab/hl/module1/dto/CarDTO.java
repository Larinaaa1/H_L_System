package ru.hpclab.hl.module1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String vin;
    private String model;
   // private Long carId;
    //private String car;
    private String color;
    private double rentalCostPerDay;
    private String city;
    private String salonName;
}

