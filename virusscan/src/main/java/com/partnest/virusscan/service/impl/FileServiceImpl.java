package com.partnest.virusscan.service.impl;

import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.dto.FileResponseDto;
import com.partnest.virusscan.entity.File;
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
    public FileResponseDto persistFile(FileDto fileDto) {
        File file = fileRepository.save(FileMapper.dtoToEntity(fileDto));
        rabbitMQProducer.sendMessage(file.getFileId().toString());
        return FileMapper.entityToResponseDto(file);
    }

    @Override
    public FileResponseDto getFileStatus(String fileId) {
        File file = findFile(fileId);
        return FileMapper.entityToResponseDto(file);
    }

    @Override
    public File getFile(String fileId) {
        return findFile(fileId);
    }

    @Override
    public File updateFileStatus(File file, FileStatus fileStatus) {
        file.setFileStatus(fileStatus);
        return fileRepository.save(file);
    }

    private File findFile(String fileId) {
        return fileRepository.findById(UUID.fromString(fileId))
                             .orElseThrow(() -> new FileNotFoundException(String.format(
                                     FileConstants.FILE_NOT_FOUND_EXCEPTION_MESSAGE,
                                     fileId
                             )));
    }
}
