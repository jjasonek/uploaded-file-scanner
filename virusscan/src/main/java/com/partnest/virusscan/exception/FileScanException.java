package com.partnest.virusscan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileScanException extends RuntimeException {

    public FileScanException(String message) {
        super(message);
    }
}
