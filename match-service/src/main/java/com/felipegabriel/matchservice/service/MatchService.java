package com.felipegabriel.matchservice.service;

import com.felipegabriel.matchservice.dto.MatchDTO;
import com.felipegabriel.matchservice.model.entity.Match;
import com.felipegabriel.matchservice.model.repository.MatchRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService {

    private MatchRepository matchRepository;

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

	private MatchDTO convertToMatchDTO(Match match) {
		return modelMapper.map(match, MatchDTO.class);
	}

	private int orderByDate(Match m1, Match m2) {
		return m1.getDate().compareTo(m2.getDate());
	}

	public void createMatches(Integer season, String division) {
//		matchRepository.saveAll(generateMatches(season, division));
	}

}
