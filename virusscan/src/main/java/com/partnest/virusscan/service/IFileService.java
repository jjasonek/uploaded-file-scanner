package com.partnest.virusscan.service;

import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.dto.FileResponseDto;
import com.partnest.virusscan.entity.File;

public interface IFileService {

    public FileResponseDto persistFile(FileDto fileDto);

    public FileResponseDto getFileStatus(String fileId);

    public File getFile(String fileId);

    public File updateFileStatus(File file, FileStatus fileStatus);

}
