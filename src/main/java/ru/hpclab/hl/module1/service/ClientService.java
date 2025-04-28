package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.ClientDTO;
import ru.hpclab.hl.module1.mapper.ClientMapper;
import ru.hpclab.hl.module1.model.Client;
import ru.hpclab.hl.module1.repository.ClientRepository;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ObservabilityService observabilityService;

    // Получить всех клиентов
    public List<ClientDTO> getAllClients() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllClients");
        List<ClientDTO> clients = clientRepository.findAll().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllClients");
        return clients;
    }

    // Получить клиента по ID
    public ClientDTO getClientById(Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getClientById");
        Optional<Client> clientEntity = clientRepository.findById(id);
        ClientDTO clientDTO = clientEntity.map(ClientMapper::toDTO).orElse(null);
        this.observabilityService.stop(getClass().getSimpleName() + ":getClientById");
        return clientDTO;
    }

    // Сохранить клиента
    public ClientDTO saveClient(ClientDTO clientDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":saveClient");
        Client client = ClientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        ClientDTO result = ClientMapper.toDTO(savedClient);
        this.observabilityService.stop(getClass().getSimpleName() + ":saveClient");
        return result;
    }

    // Обновить информацию о клиенте
    public ClientDTO updateClient(Long id, ClientDTO updatedClientDTO) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateClient");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        client.setFullName(updatedClientDTO.getFullName());
        client.setDriverLicense(updatedClientDTO.getDriverLicense());
        client.setPhoneNumber(updatedClientDTO.getPhoneNumber());

        Client updatedClient = clientRepository.save(client);
        ClientDTO result = ClientMapper.toDTO(updatedClient);
        this.observabilityService.stop(getClass().getSimpleName() + ":updateClient");
        return result;
    }

    // Удалить клиента по ID
    public void deleteClient(Long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteClient");
        clientRepository.deleteById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteClient");
    }

    // Метод очистки данных
    public void clearAll() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAll");
        clientRepository.deleteAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAll");
    }
}
