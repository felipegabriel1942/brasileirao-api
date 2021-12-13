package com.felipegabriel.matchservice.controller;

import com.felipegabriel.matchservice.dto.MatchDTO;
import com.felipegabriel.matchservice.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1")
@AllArgsConstructor
public class MatchController {

    private final MatchService service;

    @GetMapping("/matches-by-season-and-round")
	public ResponseEntity<List<MatchDTO>> findMatchesBySeasonAndRound(
			@RequestParam int season,
			@RequestParam int round) {
		return new ResponseEntity<>(service.findMatchesBySeasonAndRound(season, round), HttpStatus.OK);
	}

	@GetMapping("/matches-by-season")
	public ResponseEntity<List<MatchDTO>> findMatchesBySeason(@RequestParam int season) {
		return new ResponseEntity<>(service.findMatchesBySeason(season), HttpStatus.OK);
	}
}
