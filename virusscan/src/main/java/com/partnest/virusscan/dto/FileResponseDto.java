package com.partnest.virusscan.dto;

import com.partnest.virusscan.constants.FileStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FileResponseDto {
    private FileStatus fileStatus;
    private String fileName;
    private String fileId;
}
