package com.felipegabriel.classificationservice.api.service;

import java.util.List;
import java.util.stream.Collectors;

import com.felipegabriel.classificationservice.api.dto.ClassificationDTO;
import com.felipegabriel.classificationservice.api.model.entity.Match;
import com.felipegabriel.classificationservice.api.model.repository.MatchRepository;
import com.felipegabriel.classificationservice.api.proxy.ImageProxy;
import org.springframework.stereotype.Service;

import com.felipegabriel.classificationservice.api.exception.SeasonNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassificationService {
	
	private MatchRepository matchRepository;
	
	private final ImageProxy imageProxy;
	
	public List<Match> findMatchesBySeasonAndRound(int season, int round) {
		return matchRepository.findBySeasonAndRound(season, round).stream()
			.sorted(this::orderByTime)
			.sorted(this::orderByDate)
			.collect(Collectors.toList());
	}
	
	private int orderByTime(Match m1, Match m2) {
		return m1.getTime().compareTo(m2.getTime());
	}
	
	private int orderByDate(Match m1, Match m2) {
		return m1.getDate().compareTo(m2.getDate());
	}
			
	public List<ClassificationDTO> findClassificationBySeason(int season) {
		List<Match> matches = matchRepository.findBySeason(season);
		
		if (matches.isEmpty()) throw new SeasonNotFoundException("Season not found!");
		
		return matches.stream()
			.map(Match::getHomeTeam)
			.distinct()
			.map(this::createClassificationObject)
			.map(this::setTeamCrest)
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
	
	private ClassificationDTO setTeamCrest(ClassificationDTO classification) {
		classification.setTeamCrest(imageProxy.findImage(classification.getTeam()).getBody());
		return classification;
	}
	
	private ClassificationDTO createClassificationObject(String teamName) {
		return ClassificationDTO.builder().team(teamName).build();
	}
	
	private ClassificationDTO setPoints(ClassificationDTO classification, List<Match> matches) {
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
	
	private ClassificationDTO setGoalsFor(ClassificationDTO classification, List<Match> matches) {
		Integer goalsAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.map(Match::getHomeTeamGoals)
				.reduce(0, Integer::sum);
		
		Integer goalsAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.map(Match::getVisitorTeamGoals)
				.reduce(0, Integer::sum);
		
		classification.setGoalsFor(goalsAsVisitor + goalsAsHome);
		
		return classification;
	}
	
	private ClassificationDTO setGoalsAgainst(ClassificationDTO classification, List<Match> matches) {
		Integer goalsAsVisitor = matches.stream()
				.filter(m -> m.getVisitorTeam().equals(classification.getTeam()))
				.map(Match::getHomeTeamGoals)
				.reduce(0, Integer::sum);
		
		Integer goalsAsHome = matches.stream()
				.filter(m -> m.getHomeTeam().equals(classification.getTeam()))
				.map(Match::getVisitorTeamGoals)
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
	
	private ClassificationDTO setVictories(ClassificationDTO classification, List<Match> matches) {
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
	
	private ClassificationDTO setDefeats(ClassificationDTO classification, List<Match> matches) {
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
	
	private ClassificationDTO setTies(ClassificationDTO classification, List<Match> matches) {
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
	
	private ClassificationDTO setTotalMatches(ClassificationDTO classification, List<Match> matches) {	
		int totalMatches = matches.stream()
				.filter(m -> isTeamMatch(classification, m))
				.collect(Collectors.toList())
				.size();
		
		classification.setTotalMatches(totalMatches);
		
		return classification;
	}
	
	private boolean isTeamMatch(ClassificationDTO classification, Match match) {
		return match.getHomeTeam().equals(classification.getTeam()) || match.getVisitorTeam().equals(classification.getTeam());
	}
}