package com.felipegabriel.classificationservice.api.client;

import com.felipegabriel.classificationservice.api.dto.MatchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "match-service/v1")
public interface MatchClient {

    @GetMapping("/matches-by-season")
    public ResponseEntity<List<MatchDTO>> findMatchesBySeason(@RequestParam int season);
}
