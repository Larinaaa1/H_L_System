package ru.hpclab.hl.module1.queue.dispatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.queue.EntityType;
import ru.hpclab.hl.module1.queue.KafkaOperationMessage;
import ru.hpclab.hl.module1.queue.OperationType;
import ru.hpclab.hl.module1.service.CarService;
import ru.hpclab.hl.module1.service.ClientService;
import ru.hpclab.hl.module1.service.RentalService;
import ru.hpclab.hl.module1.dto.CarDTO;
import ru.hpclab.hl.module1.dto.ClientDTO;
import ru.hpclab.hl.module1.dto.RentalDTO;


@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcher {
    private final ClientService clientService;
    private final CarService carService;
    private final RentalService rentalService;
    private final ObjectMapper objectMapper;

    public void dispatch(KafkaOperationMessage msg) {
        EntityType entity = msg.getEntity();
        OperationType operation = msg.getOperation();

        switch (entity) {
            case CLIENT -> handleClient(operation, msg.getPayload());
            case CAR -> handleCar(operation, msg.getPayload());
            case RENTAL -> handleRental(operation, msg.getPayload());
        }
    }

    private void handleClient(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> clientService.saveClient(deserialize(payload, ClientDTO.class));
            case PUT -> clientService.updateClient(payload.get("id").asLong(), deserialize(payload, ClientDTO.class));
            case DELETE -> clientService.deleteClient(payload.get("id").asLong());
            case CLEAR -> clientService.clearAll();
        }
    }

    private void handleCar(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> carService.saveCar(deserialize(payload, CarDTO.class));
            case PUT -> carService.updateCar(payload.get("id").asLong(), deserialize(payload, CarDTO.class));
            case DELETE -> carService.deleteCar(payload.get("id").asLong());
            case CLEAR -> carService.clearAll();
        }
    }

    private void handleRental(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> rentalService.saveRental(deserialize(payload, RentalDTO.class));
            case PUT -> rentalService.updateRental(payload.get("id").asLong(), deserialize(payload, RentalDTO.class));
            case DELETE -> rentalService.deleteRental(payload.get("id").asLong());
            case CLEAR -> rentalService.clearAll();
        }
    }

    private <T> T deserialize(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize payload to " + clazz.getSimpleName(), e);
        }
    }
}
