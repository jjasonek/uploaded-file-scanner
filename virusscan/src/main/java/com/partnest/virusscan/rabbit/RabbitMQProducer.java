package com.partnest.virusscan.rabbit;

import com.partnest.virusscan.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String fileId) {
        rabbitTemplate.convertAndSend("virus-scan-exchange", "virus-scan-routing-key", fileId);
    }
}
