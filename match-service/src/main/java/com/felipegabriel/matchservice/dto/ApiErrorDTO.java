package com.felipegabriel.matchservice.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiErrorDTO {

    private HttpStatus status;

    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    public ApiErrorDTO(String message, Throwable ex, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorDTO(String message, Throwable ex) {
        this(message, ex, HttpStatus.BAD_REQUEST);
    }

}

