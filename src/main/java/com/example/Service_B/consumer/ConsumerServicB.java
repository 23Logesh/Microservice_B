package com.example.Service_B.consumer;

import com.example.Service_B.ServiceInter.ServiceInterServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerServicB {

    @Autowired
    private ServiceInterServiceB serviceB;

    @KafkaListener(topics = "ServerA-topic", groupId="my-group")
    public void saveEntity(String message) {
        log.info("[Consumer] Received message: {}", message);

        serviceB.saveB(message);

        log.info("[Consumer] Processed message and saved entity.");
    }
}
