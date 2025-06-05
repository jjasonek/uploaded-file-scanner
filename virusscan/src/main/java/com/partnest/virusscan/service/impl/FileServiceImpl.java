package com.partnest.virusscan.service.impl;

import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.entity.File;
import com.partnest.virusscan.exception.FileNotFoundException;
import com.partnest.virusscan.exception.FileReadException;
import com.partnest.virusscan.repository.FileRepository;
import com.partnest.virusscan.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private final FileRepository fileRepository;

    @Override
    public void persistFile(String fileName, MultipartFile fileData) {
        File file = new File();
        file.setFileName(fileName);
        file.setFileStatus(FileStatus.NEW);
        try {
            file.setFileData(fileData.getBytes());
        } catch (IOException e) {
            throw new FileReadException(FileConstants.FILE_READ_EXCEPTION_MESSAGE);
        }
        fileRepository.save(file);
    }

    @Override
    public FileStatus getFileStatus(String fileName) {
        File file = fileRepository.findFileByFileName(fileName)
                      .orElseThrow(() -> new FileNotFoundException(FileConstants.FILE_NOT_FOUND_EXCEPTION_MESSAGE));
        return file.getFileStatus();
    }

    @Override
    public void updateFileStatus(Long FileId, FileStatus fileStatus) {
        File file = fileRepository.findById(FileId)
                .orElseThrow(() -> new FileNotFoundException(FileConstants.FILE_NOT_FOUND_EXCEPTION_MESSAGE));
        file.setFileStatus(fileStatus);
        fileRepository.save(file);
    }
}
