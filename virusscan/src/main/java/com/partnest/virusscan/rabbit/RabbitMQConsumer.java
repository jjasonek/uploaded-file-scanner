package com.partnest.virusscan.rabbit;

import com.partnest.virusscan.service.IVirusScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public final class RabbitMQConsumer {

    private final IVirusScanService virusScanService;

    public void receiveMessage(String fileId) {
        virusScanService.scanFile(fileId);
    }
}
