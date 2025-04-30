package ru.hpclab.hl.module1.queue.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.queue.KafkaOperationMessage;
import ru.hpclab.hl.module1.queue.dispatch.KafkaMessageDispatcher;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final KafkaMessageDispatcher kafkaMessageDispatcher;
    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);private final AtomicInteger messageCounter = new AtomicInteger(0);

    @KafkaListener(
            topics = "${kafka.topic:var08_lar}",
            groupId = "${kafka.groupId:larina-consumer-group}",
            concurrency = "${kafka.concurrency:2}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleMessage(@Payload List<String> messageJsonList, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        for (int i = 0; i < messageJsonList.size(); i++) {
            long offset = offsets.get(i);
            String message = messageJsonList.get(i);
            try {
                KafkaOperationMessage messageKafka = objectMapper.readValue(message, KafkaOperationMessage.class);

                if (offset % 500 == 0) {
                    log.info("500th message reached! Count: {}, Message: {}",
                            offset, message);
                }
                kafkaMessageDispatcher.dispatch(messageKafka);
            } catch (Exception e) {
                log.error("Error while parsing or dispatching Kafka message: {}", message, e);
            }
        }
    }

}
