package ru.hpclab.hl.module1.model;

public class Car {
    private String vin;
    private String model;
    private String color;
    private double rentalCostPerDay;
    private String city;
    private String salonName;

    // Конструктор с параметрами
    public Car(String vin, String model, String color, double rentalCostPerDay, String city, String salonName) {
        this.vin = vin;
        this.model = model;
        this.color = color;
        this.rentalCostPerDay = rentalCostPerDay;
        this.city = city;
        this.salonName = salonName;
    }

    //Для работы с JSON
    public Car(){

    }
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getRentalCostPerDay() {
        return rentalCostPerDay;
    }

    public void setRentalCostPerDay(double rentalCostPerDay) {
        this.rentalCostPerDay = rentalCostPerDay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

}
