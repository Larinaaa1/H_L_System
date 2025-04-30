package ru.hpclab.hl.module1.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.queue.dispatch.KafkaMessageDispatcher;
import ru.hpclab.hl.module1.service.ClientService;
import ru.hpclab.hl.module1.service.CarService;
import ru.hpclab.hl.module1.service.RentalService;

@Configuration
public class KafkaDispatcherConfig {
    @Bean
    public KafkaMessageDispatcher kafkaMessageDispatcher(
            ClientService clientService,
            CarService carService,
            RentalService rentalService,
            ObjectMapper objectMapper
    ) {
        return new KafkaMessageDispatcher(
                clientService,
                carService,
                rentalService,
                objectMapper
        );
    }
}
