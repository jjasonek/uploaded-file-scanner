package com.partnest.virusscan.service;

import com.partnest.virusscan.dto.FileDto;

public interface IRabbitQueueService {

    public void sendFileToQueue(FileDto fileDto);
}
