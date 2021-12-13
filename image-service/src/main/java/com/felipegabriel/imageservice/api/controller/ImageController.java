package com.felipegabriel.imageservice.api.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipegabriel.imageservice.api.service.ImageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1")
@AllArgsConstructor
public class ImageController {
	
	private ImageService service;
	
	@GetMapping("/find-image")
	public ResponseEntity<String> findImage(@RequestParam String fileName) throws IOException {
		return new ResponseEntity<>(service.findImage(fileName), HttpStatus.OK);
	}
}
