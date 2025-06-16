package com.partnest.virusscan.mapper;

import com.partnest.virusscan.dto.UploadedFileDto;
import com.partnest.virusscan.dto.UploadedFileResponseDto;
import com.partnest.virusscan.entity.UploadedFile;

import java.util.UUID;

public final class FileMapper {

    public static UploadedFileResponseDto entityToResponseDto(UploadedFile uploadedFile) {
        return UploadedFileResponseDto.builder()
                                      .fileId(uploadedFile.getFileId().toString())
                                      .fileName(uploadedFile.getFileName())
                                      .fileStatus(uploadedFile.getFileStatus())
                                      .build();
    }

    public static UploadedFileDto entityToDto(UploadedFile uploadedFile) {
        return UploadedFileDto.builder()
                              .fileId(uploadedFile.getFileId().toString())
                              .fileName(uploadedFile.getFileName())
                              .fileStatus(uploadedFile.getFileStatus())
                              .fileData(uploadedFile.getFileData())
                              .build();
    }

    public static UploadedFile dtoToEntity(UploadedFileDto uploadedFileDto) {
        return UploadedFile.builder()
                           .fileId(uploadedFileDto.getFileId() != null ? UUID.fromString(uploadedFileDto.getFileId()) : null)
                           .fileName(uploadedFileDto.getFileName())
                           .fileStatus(uploadedFileDto.getFileStatus())
                           .fileData(uploadedFileDto.getFileData())
                           .build();
    }

}
