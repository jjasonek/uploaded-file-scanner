package com.partnest.virusscan.mapper;

import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.dto.FileResponseDto;
import com.partnest.virusscan.entity.File;

import java.util.UUID;

public final class FileMapper {

    public static FileResponseDto entityToResponseDto(File file) {
        return FileResponseDto.builder()
                              .fileId(file.getFileId().toString())
                              .fileName(file.getFileName())
                              .fileStatus(file.getFileStatus())
                              .build();
    }

    public static FileDto entityToDto(File file) {
        return FileDto.builder()
                      .fileId(file.getFileId().toString())
                      .fileName(file.getFileName())
                      .fileStatus(file.getFileStatus())
                      .fileData(file.getFileData())
                      .build();
    }

    public static File dtoToEntity(FileDto fileDto) {
        return File.builder()
                   .fileId(fileDto.getFileId() != null ? UUID.fromString(fileDto.getFileId()) : null)
                   .fileName(fileDto.getFileName())
                   .fileStatus(fileDto.getFileStatus())
                   .fileData(fileDto.getFileData())
                   .build();
    }

}
