package com.partnest.virusscan.controller;

import com.partnest.virusscan.constants.FileConstants;
import com.partnest.virusscan.constants.FileStatus;
import com.partnest.virusscan.dto.FileDto;
import com.partnest.virusscan.dto.ResponseDto;
import com.partnest.virusscan.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {

    private final IFileService fileService;

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDto> uploadFile(
            @RequestPart String fileName,
            @RequestPart MultipartFile fileData
//           @RequestBody FileDto fileDto
    ) {

        fileService.persistFile(fileName, fileData);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(FileStatus.NEW, FileConstants.FILE_UPLOAD_OK_MESSAGE));
    }

    @GetMapping("/fileStatus")
    public ResponseEntity<ResponseDto> getFileStatus() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(FileStatus.NEW, FileConstants.FILE_UPLOAD_OK_MESSAGE));
    }
}
