package ru.hpclab.hl.module1.model;


import java.time.LocalDate;

public class Rental {
    private Long id;
    private Car car;
    private Client client;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;

    // Конструкторы, геттеры и сеттеры
    public Rental(Long id, Car car, Client client, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }
    // Для работы с JSON
    public Rental() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }




}
