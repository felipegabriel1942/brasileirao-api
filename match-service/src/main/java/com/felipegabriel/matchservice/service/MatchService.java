package com.felipegabriel.matchservice.service;

import com.felipegabriel.matchservice.client.ImageClient;
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

	private ImageClient imageClient;

	private final ModelMapper modelMapper;

    public List<MatchDTO> findMatchesBySeason(int season) {
        return matchRepository.findBySeason(season).stream()
			.sorted(this::orderByTime)
			.sorted(this::orderByDate)
			.map(this::convertToMatchDTO)
			.collect(Collectors.toList());
    }

    public List<MatchDTO> findMatchesBySeasonAndRound(int season, int round) {
        return matchRepository.findBySeasonAndRound(season, round).stream()
            .sorted(this::orderByTime)
            .sorted(this::orderByDate)
			.map(this::convertToMatchDTO)
			.map(this::setTeamsCrest)
            .collect(Collectors.toList());
	}

	// TODO: OPREÇÃO MUITO CUSTOSA, BUSCAR MELHOR FORMA DE RESOLVER O PROBLEMA.
	// TODO: IMPLEMENTAR CIRCUIT BREAKER
	private MatchDTO setTeamsCrest(MatchDTO match) {
//		match.setVisitorTeamCrest(imageClient.findImage(match.getVisitorTeam()).getBody());
//		match.setHomeTeamCrest(imageClient.findImage(match.getHomeTeam()).getBody());
		return match;
	}

	private MatchDTO convertToMatchDTO(Match match) {
		return modelMapper.map(match, MatchDTO.class);
	}

	private int orderByTime(Match m1, Match m2) {
		return m1.getTime().compareTo(m2.getTime());
	}

	private int orderByDate(Match m1, Match m2) {
		return m1.getDate().compareTo(m2.getDate());
	}
}
