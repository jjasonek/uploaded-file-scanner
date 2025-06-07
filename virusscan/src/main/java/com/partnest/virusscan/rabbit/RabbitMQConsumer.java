package com.partnest.virusscan.rabbit;

import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.service.IVirusScanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public final class RabbitMQConsumer {

    private final IVirusScanService virusScanService;

    @RabbitListener(queues = "virus-scan-queue")
    public void receiveMessage(String fileId) {
        virusScanService.scanFile(fileId);
    }

}
