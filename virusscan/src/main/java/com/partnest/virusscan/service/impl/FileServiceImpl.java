package com.partnest.virusscan.service.impl;

import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.UploadedFileDto;
import com.partnest.virusscan.dto.UploadedFileResponseDto;
import com.partnest.virusscan.entity.UploadedFile;
import com.partnest.virusscan.exception.FileNotFoundException;
import com.partnest.virusscan.mapper.FileMapper;
import com.partnest.virusscan.rabbit.RabbitMQProducer;
import com.partnest.virusscan.repository.FileRepository;
import com.partnest.virusscan.service.IFileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private final FileRepository fileRepository;
    private final RabbitMQProducer rabbitMQProducer;

    @Override
    @Transactional
    public UploadedFileResponseDto persistFile(UploadedFileDto uploadedFileDto) {
        UploadedFile uploadedFile = fileRepository.save(FileMapper.dtoToEntity(uploadedFileDto));
        rabbitMQProducer.sendMessage(uploadedFile.getFileId().toString());
        return FileMapper.entityToResponseDto(uploadedFile);
    }

    @Override
    public UploadedFileResponseDto getFileStatus(String fileId) {
        UploadedFile uploadedFile = findFile(fileId);
        return FileMapper.entityToResponseDto(uploadedFile);
    }

    @Override
    public UploadedFile getFile(String fileId) {
        return findFile(fileId);
    }

    @Override
    public UploadedFile updateFileStatus(UploadedFile uploadedFile, FileStatus fileStatus) {
        uploadedFile.setFileStatus(fileStatus);
        return fileRepository.save(uploadedFile);
    }

    private UploadedFile findFile(String fileId) {
        return fileRepository.findById(UUID.fromString(fileId))
                             .orElseThrow(() -> new FileNotFoundException(String.format(
                                     FileConstants.FILE_NOT_FOUND_EXCEPTION_MESSAGE,
                                     fileId
                             )));
    }
}
