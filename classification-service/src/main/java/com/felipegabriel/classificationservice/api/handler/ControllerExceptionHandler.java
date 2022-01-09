package com.felipegabriel.classificationservice.api.handler;

import com.felipegabriel.classificationservice.api.dto.ApiErrorDTO;
import com.google.gson.Gson;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.felipegabriel.classificationservice.api.exception.SeasonNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(SeasonNotFoundException.class)
	public ResponseEntity<Object> handleSeasonNotFoundException(
			SeasonNotFoundException exception
			) {
		ApiErrorDTO error = new ApiErrorDTO(exception.getMessage());
		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Object> handleFeignException(
			FeignException exception
	) {
		Map<String, String> feignErrorMap = new Gson().fromJson(exception.contentUTF8(), HashMap.class);

		ApiErrorDTO error = new ApiErrorDTO(feignErrorMap.get("message"));

		return new ResponseEntity<>(error, error.getStatus());
	}
}
