package com.partnest.virusscan.controller;

import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.UploadedFileDto;
import com.partnest.virusscan.dto.UploadedFileResponseDto;
import com.partnest.virusscan.exception.FileReadException;
import com.partnest.virusscan.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {

    private final IFileService fileService;

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<UploadedFileResponseDto> uploadFile(
            @RequestPart MultipartFile fileData
    ) {
        try {
            UploadedFileDto uploadedFileDto = UploadedFileDto.builder()
                                                             .fileName(fileData.getOriginalFilename())
                                                             .fileStatus(FileStatus.NEW)
                                                             .fileData(fileData.getBytes()).build();
            UploadedFileResponseDto uploadedFileResponseDto = fileService.persistFile(uploadedFileDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(uploadedFileResponseDto);
        } catch (IOException e) {
            throw new FileReadException(FileConstants.FILE_READ_EXCEPTION_MESSAGE);
        }
    }

    @GetMapping("/fileStatus")
    public ResponseEntity<UploadedFileResponseDto> getFileStatus(@RequestParam String fileId) {
        UploadedFileResponseDto uploadedFileResponseDto = fileService.getFileStatus(fileId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(uploadedFileResponseDto);
    }
}
