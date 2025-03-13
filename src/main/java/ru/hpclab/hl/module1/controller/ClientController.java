package ru.hpclab.hl.module1.controller;

import ru.hpclab.hl.module1.model.Client;
import ru.hpclab.hl.module1.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Получить всех клиентов
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Получить клиента по водительским правам
    @GetMapping("/{driverLicense}")
    public Client getClientByDriverLicense(@PathVariable String driverLicense) {
        return clientService.getClientByDriverLicense(driverLicense);
    }

    // Создать нового клиента
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    // Удалить клиента по водительским правам
    @DeleteMapping("/{driverLicense}")
    public void deleteClient(@PathVariable String driverLicense) {
        clientService.deleteClient(driverLicense);
    }

    // Обновить информацию о клиенте
    @PutMapping("/{driverLicense}")
    public Client updateClient(@PathVariable String driverLicense, @RequestBody Client updatedClient) {
        return clientService.updateClient(driverLicense, updatedClient);
    }
}