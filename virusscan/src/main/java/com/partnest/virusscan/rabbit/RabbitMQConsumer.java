package com.partnest.virusscan.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class RabbitMQConsumer {

    @RabbitListener(queues = "virus-scan-queue")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
    }

}
