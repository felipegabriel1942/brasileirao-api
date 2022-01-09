package com.felipegabriel.matchservice.handler;

import com.felipegabriel.matchservice.dto.ApiErrorDTO;
import com.felipegabriel.matchservice.exception.DivisionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DivisionNotFoundException.class)
    public ResponseEntity<Object> handleDivisionNotFoundException(
            DivisionNotFoundException exception
    ) {
        ApiErrorDTO error = new ApiErrorDTO(exception.getMessage(), exception);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
