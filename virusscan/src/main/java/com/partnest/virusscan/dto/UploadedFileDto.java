package com.partnest.virusscan.dto;

import com.partnest.virusscan.constants.FileStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UploadedFileDto {
    private String fileId;
    private String fileName;
    private FileStatus fileStatus;
    private byte[] fileData;
}
