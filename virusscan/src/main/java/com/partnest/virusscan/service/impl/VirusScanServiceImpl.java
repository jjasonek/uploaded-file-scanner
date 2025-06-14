package com.partnest.virusscan.service.impl;

import com.partnest.virusscan.apiclient.VirusScanClient;
import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.entity.File;
import com.partnest.virusscan.exception.FileScanException;
import com.partnest.virusscan.service.IFileService;
import com.partnest.virusscan.service.IVirusScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class VirusScanServiceImpl implements IVirusScanService {

    private final VirusScanClient virusScanClient;
    private final IFileService fileService;

    @Override
    public void scanFile(String fileId) {
        log.info("Scanning file: {}", fileId);
        File file = fileService.getFile(fileId);
        File updatedFile = fileService.updateFileStatus(file, FileStatus.SCANNING);
        String result = virusScanClient.scanFile(file.getFileData());
        log.info("Scan result: {}", result);
        fileService.updateFileStatus(updatedFile, evaluateScanResult(result, fileId));
    }

    private FileStatus evaluateScanResult(String scanResult, String fileId) {
        return switch (scanResult) {
            case "clean" -> FileStatus.CLEAN;
            case "infected" -> FileStatus.INFECTED;
            default -> throw new FileScanException(String.format(
                    FileConstants.FILE_SCAN_EXCEPTION_MESSAGE,
                    fileId
            ));
        };
    }
}
