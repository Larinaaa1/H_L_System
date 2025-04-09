package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.ClientDTO;
import ru.hpclab.hl.module1.model.Client;

public class ClientMapper {

    // Приватный конструктор, чтобы запретить создание экземпляров класса
    private ClientMapper() {
    }

    /**
     * Преобразует модель Client в ClientDTO.
     *
     * @param client Модель клиента.
     * @return DTO клиента.
     */
    public static ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientDTO(
                client.getId(),
                client.getFullName(),
                client.getDriverLicense(),
                client.getPhoneNumber()
        );
    }

    /**
     * Преобразует ClientDTO в модель Client.
     *
     * @param clientDTO DTO клиента.
     * @return Модель клиента.
     */
    public static Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }
        return new Client(
                null, // id будет сгенерирован при сохранении в базу данных
                clientDTO.getFullName(),
                clientDTO.getDriverLicense(),
                clientDTO.getPhoneNumber()
        );
    }
}