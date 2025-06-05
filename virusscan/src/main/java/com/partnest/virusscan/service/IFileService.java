package com.partnest.virusscan.service;

import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    public void persistFile(String fileName, MultipartFile fileData);

    public FileStatus getFileStatus(String fileName);

    public void updateFileStatus(Long FileId, FileStatus fileStatus);

}
