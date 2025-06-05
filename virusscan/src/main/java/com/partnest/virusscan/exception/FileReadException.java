package com.partnest.virusscan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileReadException extends RuntimeException {

    public FileReadException(String message) {
        super(message);
    }
}
