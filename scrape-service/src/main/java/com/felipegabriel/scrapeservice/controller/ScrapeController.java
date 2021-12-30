package com.felipegabriel.scrapeservice.controller;

import com.felipegabriel.scrapeservice.dto.MatchDTO;
import com.felipegabriel.scrapeservice.service.ScrapeService;
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
public class ScrapeController {

    private final ScrapeService service;

    @GetMapping("/find-matches")
    public ResponseEntity<List<MatchDTO>> findMatches(
            @RequestParam int season,
            @RequestParam String division) {
        return new ResponseEntity<>(service.findMatches(season, division), HttpStatus.OK);
    }

    @GetMapping("/find-matches-by-match-numbers")
    public ResponseEntity<List<MatchDTO>> findMatchesByMatchNumbers(
            @RequestParam int season,
            @RequestParam String division,
            @RequestParam List<Integer> matchNumbers) {
        return new ResponseEntity<>(service.findMatchesByMatchNumbers(season, matchNumbers, division), HttpStatus.OK);
    }

}
