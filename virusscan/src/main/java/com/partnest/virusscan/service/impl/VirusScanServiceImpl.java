package com.partnest.virusscan.service.impl;

import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.entity.File;
import com.partnest.virusscan.exception.FileNotFoundException;
import com.partnest.virusscan.repository.FileRepository;
import com.partnest.virusscan.service.IVirusScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class VirusScanServiceImpl implements IVirusScanService {

    private final FileRepository fileRepository;

    @Override
    public void scanFile(String fileId) {
        log.info("Scanning file: {}", fileId);
        File file = fileRepository.findById(UUID.fromString(fileId))
                .orElseThrow(() -> new FileNotFoundException(FileConstants.FILE_NOT_FOUND_EXCEPTION_MESSAGE));
        file.setFileStatus(FileStatus.SCANNING);
        fileRepository.save(file);
        // TODO implement virus scan for the File file.
    }
}
