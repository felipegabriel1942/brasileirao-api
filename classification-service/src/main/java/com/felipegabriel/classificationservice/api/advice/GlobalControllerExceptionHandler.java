package com.felipegabriel.classificationservice.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.felipegabriel.classificationservice.api.exception.SeasonNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler(SeasonNotFoundException.class)
	public ResponseEntity<Object> handleSeasonNotFoundException(SeasonNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
