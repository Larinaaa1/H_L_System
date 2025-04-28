package ru.hpclab.hl.module1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.ClientDTO;
import ru.hpclab.hl.module1.service.ClientService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ObservabilityService observabilityService;


    // Получить всех клиентов
    @GetMapping
    public List<ClientDTO> getAllClients() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllClients");
        List<ClientDTO> temp = clientService.getAllClients();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllClients");
        return temp;
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getClientById");
        ClientDTO temp = clientService.getClientById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":getClientById");
        return temp;
    }

    // Создать нового клиента
    @PostMapping
    public ClientDTO addClient(@RequestBody ClientDTO clientDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":addClient");
        ClientDTO temp = clientService.saveClient(clientDTO);
        this.observabilityService.stop(getClass().getSimpleName() + ":addClient");
        return temp;
    }

    // Обновить информацию о клиенте
    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateClient");
        ClientDTO temp = clientService.updateClient(id, clientDTO);
        this.observabilityService.stop(getClass().getSimpleName() + ":updateClient");
        return temp;
    }

    // Удалить клиента по ID
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteClient");
        clientService.deleteClient(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteClient");
    }

    // Удалить данные
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAllClients() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllClients");
        clientService.clearAll();
        ResponseEntity<String> temp = ResponseEntity.ok("Все клиенты были удалены");
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllClients");
        return temp;
    }
}