package com.partnest.virusscan.controller;

import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.dto.FileResponseDto;
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
    public ResponseEntity<FileResponseDto> uploadFile(
            @RequestPart MultipartFile fileData
    ) {
        try {
            FileDto fileDto = FileDto.builder()
                                     .fileName(fileData.getOriginalFilename())
                                     .fileStatus(FileStatus.NEW)
                                     .fileData(fileData.getBytes()).build();
            FileResponseDto fileResponseDto = fileService.persistFile(fileDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(fileResponseDto);
        } catch (IOException e) {
            throw new FileReadException(FileConstants.FILE_READ_EXCEPTION_MESSAGE);
        }
    }

    @GetMapping("/fileStatus")
    public ResponseEntity<FileResponseDto> getFileStatus(@RequestParam String fileId) {
        FileResponseDto fileResponseDto = fileService.getFileStatus(fileId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileResponseDto);
    }
}
