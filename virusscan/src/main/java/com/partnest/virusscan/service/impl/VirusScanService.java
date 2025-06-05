package com.partnest.virusscan.service.impl;

import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.repository.FileRepository;
import com.partnest.virusscan.service.IVirusScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class VirusScanService implements IVirusScanService {

    private final FileRepository fileRepository;

    @Override
    public void scanFile(FileDto fileDto) {

    }
}
