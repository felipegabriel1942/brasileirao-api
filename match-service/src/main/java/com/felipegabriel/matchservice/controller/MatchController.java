package com.felipegabriel.matchservice.controller;

import com.felipegabriel.matchservice.dto.MatchDTO;
import com.felipegabriel.matchservice.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1")
@AllArgsConstructor
public class MatchController {

    private final MatchService service;

	@GetMapping("/matches-by-season")
	public ResponseEntity<List<MatchDTO>> findMatchesBySeason(
			@RequestParam int season,
			@RequestParam String division) {
		return new ResponseEntity<>(service.findMatchesBySeason(season, division), HttpStatus.OK);
	}

    @GetMapping("/matches-by-season-and-round")
	public ResponseEntity<List<MatchDTO>> findMatchesBySeasonAndRound(
			@RequestParam int season,
			@RequestParam int round,
			@RequestParam String division) {
		return new ResponseEntity<>(service.findMatchesBySeasonAndRound(season, round, division), HttpStatus.OK);
	}

}
