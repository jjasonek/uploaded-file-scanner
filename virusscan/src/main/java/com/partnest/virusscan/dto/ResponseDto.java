package com.partnest.virusscan.dto;

import com.partnest.virusscan.constants.FileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private FileStatus fileStatus;
//    private String responseCode;
    private String responseMessage;
}
