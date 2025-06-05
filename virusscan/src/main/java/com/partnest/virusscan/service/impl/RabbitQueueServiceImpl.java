package com.partnest.virusscan.service.impl;

import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.rabbit.RabbitMQProducer;
import com.partnest.virusscan.service.IRabbitQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitQueueServiceImpl implements IRabbitQueueService {

    private final RabbitMQProducer rabbitMQProducer;

    @Override
    public void sendFileToQueue(FileDto fileDto) {
        rabbitMQProducer.sendMessage(fileDto.toString());
    }
}
