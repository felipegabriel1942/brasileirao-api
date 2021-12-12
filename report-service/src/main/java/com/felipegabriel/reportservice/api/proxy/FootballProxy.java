package com.felipegabriel.reportservice.api.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.felipegabriel.reportservice.api.dto.ClassificationDTO;

@FeignClient(name = "football-service")
public interface FootballProxy {
	
	@GetMapping("/football-service/classification-by-season")
	public ResponseEntity<List<ClassificationDTO>> getClassificationBySeason(
			@RequestParam("season") int season);
}
