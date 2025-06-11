package com.partnest.virusscan.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class RabbitMQProducer {

    private final RabbitMQConfigProperties rabbitMQConfigProperties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String fileId) {
        rabbitTemplate.convertAndSend(
                rabbitMQConfigProperties.exchange(),
                rabbitMQConfigProperties.routing().key(),
                fileId
        );
    }
}
