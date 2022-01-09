package com.felipegabriel.classificationservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiErrorDTO {

    private HttpStatus status;

    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    public ApiErrorDTO(String message, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public ApiErrorDTO(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

}
