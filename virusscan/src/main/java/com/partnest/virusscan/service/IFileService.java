package com.partnest.virusscan.service;

import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.dto.FileResponseDto;

public interface IFileService {

    public FileResponseDto persistFile(FileDto fileDto);

    public FileStatus getFileStatus(String fileName);

//    public void updateFileStatus(Long FileId, FileStatus fileStatus);

}
