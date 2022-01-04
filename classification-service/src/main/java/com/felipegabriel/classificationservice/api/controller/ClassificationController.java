package com.felipegabriel.classificationservice.api.controller;

import java.util.List;

import com.felipegabriel.classificationservice.api.dto.ClassificationDTO;
import com.felipegabriel.classificationservice.api.service.ClassificationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "v1")
@AllArgsConstructor
public class ClassificationController {
	
	private final ClassificationService service;

	@GetMapping("/classification-by-season")
	public ResponseEntity<List<ClassificationDTO>> findClassificationBySeason(
			@RequestParam int season,
			@RequestParam String division) {
		return new ResponseEntity<>(service.findClassificationBySeason(season, division), HttpStatus.OK);
	}
}
