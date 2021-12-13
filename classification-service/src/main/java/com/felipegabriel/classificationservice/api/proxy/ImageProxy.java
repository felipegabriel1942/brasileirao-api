package com.felipegabriel.classificationservice.api.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "image-service")
public interface ImageProxy {
	
	@GetMapping("/image-service/find-image")
	public ResponseEntity<String> findImage(@RequestParam String fileName);
}
