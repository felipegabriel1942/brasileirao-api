package com.felipegabriel.classificationservice.api.service;

import java.util.List;
import java.util.stream.Collectors;

import com.felipegabriel.classificationservice.api.client.MatchClient;
import com.felipegabriel.classificationservice.api.dto.ClassificationDTO;
import com.felipegabriel.classificationservice.api.dto.MatchDTO;
import com.felipegabriel.classificationservice.api.dto.TeamDTO;
import com.felipegabriel.classificationservice.api.exception.ErrorMessage;
import org.springframework.stereotype.Service;

import com.felipegabriel.classificationservice.api.exception.SeasonNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassificationService {

	private final MatchClient matchClient;

	// TODO: IMPLEMENTAR CIRCUIT BREAKER
	public List<ClassificationDTO> findClassificationBySeason(int season, String division) {
		List<MatchDTO> matches = matchClient.findMatchesBySeason(season, division);

		validateMatches(matches);

		return matches.stream()
			.distinct()
			.map(this::convertToClassificationDTO)
			.map(c -> setPoints(c, matches))
			.map(c -> setGoalsFor(c, matches))
			.map(c -> setGoalsAgainst(c, matches))
			.map(c -> setVictories(c, matches))
			.map(c -> setTies(c, matches))
			.map(c -> setDefeats(c, matches))
			.map(c -> setTotalMatches(c, matches))
			.map(this::setGoalsDifference)
			.sorted(this::orderByGoalsDifference)
			.sorted(this::orderByVictories)
			.sorted(this::orderByPoints)
			.collect(Collectors.toList());
	}

	private void validateMatches(List<MatchDTO> matches) {
		if (matches.isEmpty()) {
			throw new SeasonNotFoundException(ErrorMessage.MSG_ERR_SEASON_NOT_FOUND.getMessage());
		};
	}

	private ClassificationDTO convertToClassificationDTO(MatchDTO matchDTO) {
		return ClassificationDTO.builder()
				.team(matchDTO.getHomeTeam())
				.teamCrest(matchDTO.getHomeTeamCrest()).build();
	}
	
	private ClassificationDTO setPoints(ClassificationDTO classification, List<MatchDTO> matches) {
		Integer pointsAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.map(m -> calculatePoints(m.getVisitorTeamGoals(), m.getHomeTeamGoals()))
				.reduce(0, Integer::sum);
		
		Integer pointsAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.map(m -> calculatePoints(m.getHomeTeamGoals(), m.getVisitorTeamGoals()))
				.reduce(0, Integer::sum);
		
		classification.setPoints(pointsAsVisitor + pointsAsHome);
		
		return classification;
	} 

	private Integer calculatePoints(int goalsFor, int goalsAgainst) {		
		if (goalsAgainst > goalsFor) {
			return 0;
		}
		
		return goalsFor > goalsAgainst ? 3 : 1;
	}
	
	private ClassificationDTO setGoalsFor(ClassificationDTO classification, List<MatchDTO> matches) {
		Integer goalsAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.map(MatchDTO::getHomeTeamGoals)
				.reduce(0, Integer::sum);
		
		Integer goalsAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.map(MatchDTO::getVisitorTeamGoals)
				.reduce(0, Integer::sum);
		
		classification.setGoalsFor(goalsAsVisitor + goalsAsHome);
		
		return classification;
	}
	
	private ClassificationDTO setGoalsAgainst(ClassificationDTO classification, List<MatchDTO> matches) {
		Integer goalsAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.map(MatchDTO::getHomeTeamGoals)
				.reduce(0, Integer::sum);
		
		Integer goalsAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.map(MatchDTO::getVisitorTeamGoals)
				.reduce(0, Integer::sum);
		
		classification.setGoalsAgainst(goalsAsVisitor + goalsAsHome);
		
		return classification;
	}
	
	private int orderByVictories(ClassificationDTO c1, ClassificationDTO c2) {
		return Integer.valueOf(c2.getVictories()).compareTo(c1.getVictories());
	}
		
	private int orderByGoalsDifference(ClassificationDTO c1, ClassificationDTO c2) {
		return Integer.valueOf(c2.getGoalsDifference()).compareTo(c1.getGoalsDifference());
	}
	
	private int orderByPoints(ClassificationDTO c1, ClassificationDTO c2) {
		return Integer.valueOf(c2.getPoints()).compareTo(c1.getPoints());
	}
	
	private ClassificationDTO setGoalsDifference(ClassificationDTO classification) {
		classification.setGoalsDifference(classification.getGoalsFor() - classification.getGoalsAgainst());
		
		return classification;
	}
	
	private ClassificationDTO setVictories(ClassificationDTO classification, List<MatchDTO> matches) {
		Integer victoriesAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.filter(m -> m.getHomeTeamGoals() > m.getVisitorTeamGoals())
				.collect(Collectors.toList()).size();
		
		Integer victoriesAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.filter(m -> m.getVisitorTeamGoals() > m.getHomeTeamGoals())
				.collect(Collectors.toList()).size();
		
		classification.setVictories(victoriesAsVisitor + victoriesAsHome);
		
		return classification;
	}
	
	private ClassificationDTO setDefeats(ClassificationDTO classification, List<MatchDTO> matches) {
		Integer defeatsAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.filter(m -> m.getHomeTeamGoals() < m.getVisitorTeamGoals())
				.collect(Collectors.toList()).size();
		
		Integer defeatsAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.filter(m -> m.getVisitorTeamGoals() < m.getHomeTeamGoals())
				.collect(Collectors.toList()).size();
		
		classification.setDefeats(defeatsAsVisitor + defeatsAsHome);
		
		return classification;
	}
	
	private ClassificationDTO setTies(ClassificationDTO classification, List<MatchDTO> matches) {
		Integer tiesAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.filter(m -> m.getHomeTeamGoals().equals(m.getVisitorTeamGoals()))
				.collect(Collectors.toList()).size();
		
		Integer tiesAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.filter(m -> m.getVisitorTeamGoals().equals(m.getHomeTeamGoals()))
				.collect(Collectors.toList()).size();
		
		classification.setTies(tiesAsHome + tiesAsVisitor);
		
		return classification;
	}
	
	private ClassificationDTO setTotalMatches(ClassificationDTO classification, List<MatchDTO> matches) {
		int totalMatches = matches.stream()
				.filter(m -> isTeamMatch(classification, m))
				.collect(Collectors.toList())
				.size();
		
		classification.setTotalMatches(totalMatches);
		
		return classification;
	}
	
	private boolean isTeamMatch(ClassificationDTO classification, MatchDTO match) {
		return match.getHomeTeam().equals(classification.getTeam()) || match.getVisitorTeam().equals(classification.getTeam());
	}
}
