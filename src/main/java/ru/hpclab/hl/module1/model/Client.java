package ru.hpclab.hl.module1.model;

public class Client {
    private String fullName;
    private String driverLicense;
    private String phoneNumber;

    // Конструктор с параметрами
    public Client(String fullName, String driverLicense, String phoneNumber) {
        this.fullName = fullName;
        this.driverLicense = driverLicense;
        this.phoneNumber = phoneNumber;

    }

    // Для работы с JSON
    public Client(){

    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
