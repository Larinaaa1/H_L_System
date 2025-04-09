package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.ClientDTO;
import ru.hpclab.hl.module1.mapper.CarMapper;
import ru.hpclab.hl.module1.model.Car;
import ru.hpclab.hl.module1.model.Client;
import ru.hpclab.hl.module1.mapper.ClientMapper;
import ru.hpclab.hl.module1.mapper.RentalMapper;
import ru.hpclab.hl.module1.model.Rental;
import ru.hpclab.hl.module1.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;



    // Получить всех клиентов
    public List<ClientDTO> getAllClients() {

        return clientRepository.findAll().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Получить клиента по ID
    public ClientDTO getClientById(Long id) {
        Optional<Client> clientEntity = clientRepository.findById(id);
        return clientEntity.map(ClientMapper::toDTO).orElse(null);
    }

    // Сохранить клиента
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = ClientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.toDTO(savedClient);
    }

    // Обновить информацию о клиенте
    public ClientDTO updateClient(Long id, ClientDTO updatedClientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));


        client.setFullName(updatedClientDTO.getFullName());
        client.setDriverLicense(updatedClientDTO.getDriverLicense());
        client.setPhoneNumber(updatedClientDTO.getPhoneNumber());


        Client updatedClient = clientRepository.save(client);

        return ClientMapper.toDTO(updatedClient);
    }

    // Удалить клиента по ID
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // Метод очистки данных
    public void clearAll() {
        clientRepository.deleteAll();
    }
}