package com.felipegabriel.classificationservice.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.felipegabriel.classificationservice.api.dto.ClassificationDTO;
import com.felipegabriel.classificationservice.api.dto.MatchDTO;
import com.felipegabriel.classificationservice.api.proxy.ImageProxy;
import com.felipegabriel.classificationservice.api.service.ClassificationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("football-service")
@AllArgsConstructor
public class ClassificationController {
	
	private final ClassificationService service;
	
	private final ImageProxy imageProxy;
	
	private final ModelMapper modelMapper;
	
	@GetMapping("/classification-by-season")
	public ResponseEntity<List<ClassificationDTO>> findClassificationBySeason(@RequestParam int season) {
		return new ResponseEntity<>(service.findClassificationBySeason(season), HttpStatus.OK);
	}
	
	@GetMapping("/matches-by-season-and-round")
	public ResponseEntity<List<MatchDTO>> findMatchesBySeasonAndRound(
			@RequestParam int season,
			@RequestParam int round) {
		
		List<MatchDTO> matches = service.findMatchesBySeasonAndRound(season, round).stream()
				.map(m -> modelMapper.map(m, MatchDTO.class))
				.collect(Collectors.toList());
		
		matches
			.forEach(m -> {
				m.setVisitorTeamCrest(imageProxy.findImage(m.getVisitorTeam()).getBody());
				m.setHomeTeamCrest(imageProxy.findImage(m.getHomeTeam()).getBody());
		});
		
		return new ResponseEntity<>(matches, HttpStatus.OK);
	}
}
