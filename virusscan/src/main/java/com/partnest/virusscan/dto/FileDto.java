package com.partnest.virusscan.dto;

import com.partnest.virusscan.constants.FileStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class FileDto {
    private String fileId;
    private String fileName;
    private FileStatus fileStatus;
    private byte[] fileData;
}
