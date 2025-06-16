package com.partnest.virusscan.dto;

import com.partnest.virusscan.constants.FileStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UploadedFileResponseDto {
    private FileStatus fileStatus;
    private String fileName;
    private String fileId;
}
