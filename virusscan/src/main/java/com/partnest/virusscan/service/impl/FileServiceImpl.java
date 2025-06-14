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
    public FileStatus getFileStatus(String fileName) {
        File file = fileRepository.findFileByFileName(fileName)
                                  .orElseThrow(() -> new FileNotFoundException(String.format(
                                          FileConstants.FILE_NOT_FOUND_EXCEPTION_MESSAGE,
                                          fileName
                                  )));
        return file.getFileStatus();
    }

//    @Override
//    public void updateFileStatus(Long FileId, FileStatus fileStatus) {
//        File file = fileRepository.findById(FileId)
//                .orElseThrow(() -> new FileNotFoundException(FileConstants.FILE_NOT_FOUND_EXCEPTION_MESSAGE));
//        file.setFileStatus(fileStatus);
//        fileRepository.save(file);
//    }
}
