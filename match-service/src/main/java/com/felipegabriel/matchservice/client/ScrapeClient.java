package com.felipegabriel.matchservice.client;

import com.felipegabriel.matchservice.dto.MatchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "scrape-service/v1")
public interface ScrapeClient {

    @GetMapping("/find-matches")
    ResponseEntity<List<MatchDTO>> findMatches(
            @RequestParam int season,
            @RequestParam String division);

    @GetMapping("/find-matches-by-match-numbers")
    ResponseEntity<List<MatchDTO>> findMatchesByMatchNumbers(
            @RequestParam int season,
            @RequestParam String division,
            @RequestParam List<Integer> matchNumbers);
}
