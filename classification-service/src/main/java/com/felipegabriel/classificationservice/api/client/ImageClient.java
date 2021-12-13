package com.felipegabriel.classificationservice.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "image-service/v1")
public interface ImageClient {
	
	@GetMapping("/find-image")
	public ResponseEntity<String> findImage(@RequestParam String fileName);
}
