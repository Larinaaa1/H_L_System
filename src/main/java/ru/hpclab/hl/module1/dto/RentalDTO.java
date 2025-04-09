package ru.hpclab.hl.module1.dto;

import java.time.LocalDate;

public class RentalDTO {
    private Long id;
    private Long carId;
    private Long clientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalCost;

    public RentalDTO() {
    }

    public RentalDTO(Long id, Long carId, Long clientId, LocalDate startDate, LocalDate endDate, Double totalCost) {
        this.id = id;
        this.carId = carId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}