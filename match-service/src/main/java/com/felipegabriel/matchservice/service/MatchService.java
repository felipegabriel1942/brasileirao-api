package com.felipegabriel.matchservice.service;

import com.felipegabriel.matchservice.client.ScrapeClient;
import com.felipegabriel.matchservice.dto.MatchDTO;
import com.felipegabriel.matchservice.model.entity.Match;
import com.felipegabriel.matchservice.model.repository.MatchRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService {

    private MatchRepository matchRepository;

	private ScrapeClient scrapeClient;

	private final ModelMapper modelMapper;

    public List<MatchDTO> findMatchesBySeason(int season, String division) {
        return matchRepository.findBySeasonAndDivision(season, division).stream()
			.sorted(this::orderByDate)
			.map(this::convertToMatchDTO)
			.collect(Collectors.toList());
    }

    public List<MatchDTO> findMatchesBySeasonAndRound(int season, int round, String division) {
        return matchRepository.findBySeasonAndRoundAndDivision(season, round, division).stream()
            .sorted(this::orderByDate)
			.map(this::convertToMatchDTO)
            .collect(Collectors.toList());
	}

	public void saveMatches(Integer season, String division) {
		ResponseEntity<List<MatchDTO>> response = scrapeClient.findMatches(season, division);

		if (response.hasBody()) {
			List<Match> matches = response.getBody().stream()
					.map(this::convertToMatch)
					.collect(Collectors.toList());

			matchRepository.saveAll(matches);
		}
	}

	private MatchDTO convertToMatchDTO(Match match) {
		return modelMapper.map(match, MatchDTO.class);
	}

	private Match convertToMatch(MatchDTO matchDTO) { return modelMapper.map(matchDTO, Match.class);}

	private int orderByDate(Match m1, Match m2) {
		return m1.getDate().compareTo(m2.getDate());
	}
}
