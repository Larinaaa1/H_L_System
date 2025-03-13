package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class ClientRepository {
    private static final List<Client> clients = new ArrayList<>();

    static {
        clients.add(new Client("John Doe", "DL12345", "+1234567890"));
        clients.add(new Client("Jane Doe", "DL67890", "+0987654321"));
    }

    public List<Client> findAll() {
        return clients;
    }

    public Client findByDriverLicense(String driverLicense) {
        return clients.stream()
                .filter(client -> client.getDriverLicense().equals(driverLicense))
                .findFirst()
                .orElse(null);
    }

}
