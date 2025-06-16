package com.partnest.virusscan.service;

import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.UploadedFileDto;
import com.partnest.virusscan.dto.UploadedFileResponseDto;
import com.partnest.virusscan.entity.UploadedFile;

public interface IFileService {

    public UploadedFileResponseDto persistFile(UploadedFileDto uploadedFileDto);

    public UploadedFileResponseDto getFileStatus(String fileId);

    public UploadedFile getFile(String fileId);

    public UploadedFile updateFileStatus(UploadedFile uploadedFile, FileStatus fileStatus);

}
