package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Client;
import ru.hpclab.hl.module1.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service

public class ClientService {
    private static List<Client> clients = new ArrayList<>();
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clients;
    }

    // Получить клиента по водительским правам
    public Client getClientByDriverLicense(String driverLicense) {
        return clients.stream()
                .filter(client -> client.getDriverLicense().equals(driverLicense))
                .findFirst()
                .orElse(null);
    }

    public Client saveClient(Client client) {
        clients.add(client);
        return client;
    }

    // Удалить клиента по водительским правам
    public void deleteClient(String driverLicense) {
        clients.removeIf(client -> client.getDriverLicense().equals(driverLicense));
    }

    // Обновить информацию о клиенте
    public Client updateClient(String driverLicense, Client updatedClient) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getDriverLicense().equals(driverLicense))
                .findFirst();
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setFullName(updatedClient.getFullName());
            client.setPhoneNumber(updatedClient.getPhoneNumber());
            return client;
        }
        return null;
    }

}
