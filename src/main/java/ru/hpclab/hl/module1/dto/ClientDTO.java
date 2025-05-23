package ru.hpclab.hl.module1.dto;

public class ClientDTO {
    private Long id;
    private String fullName;
    private String driverLicense;
    private String phoneNumber;

    // Конструктор по умолчанию (для работы с JSON)
    public ClientDTO() {
    }

    // Конструктор с параметрами
    public ClientDTO(Long id, String fullName, String driverLicense, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.driverLicense = driverLicense;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
