package com.felipegabriel.footballservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.felipegabriel.classificationservice.api.dto.ClassificationDTO;
import com.felipegabriel.classificationservice.api.exception.SeasonNotFoundException;
import com.felipegabriel.classificationservice.api.client.ImageClient;
import com.felipegabriel.classificationservice.api.service.ClassificationService;


@ExtendWith(SpringExtension.class)
public class ClassificationServiceTest {
	
//	private ClassificationService classificationService;
//
//	@MockBean
//	private MatchRepository matchRepository;
//
//	@MockBean
//	private ImageClient imageClient;
//
//	@BeforeEach
//	public void setUp() {
//		this.classificationService = new ClassificationService(matchRepository, imageClient);
//	}
//
//	@Test
//	public void shouldReturnAnClassificationBySeason() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.size()).isEqualTo(4);
//	}
//
//	@Test
//	public void shouldCalculateTeamPoints() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Athl??tico-PR")).collect(Collectors.toList()).get(0).getPoints()).isEqualTo(7);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Gr??mio")).collect(Collectors.toList()).get(0).getPoints()).isEqualTo(4);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Guarani")).collect(Collectors.toList()).get(0).getPoints()).isEqualTo(3);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Vasco")).collect(Collectors.toList()).get(0).getPoints()).isEqualTo(3);
//	}
//
//	@Test
//	public void shouldCalculateTeamGoalsFor() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Athl??tico-PR")).collect(Collectors.toList()).get(0).getGoalsFor()).isEqualTo(7);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Gr??mio")).collect(Collectors.toList()).get(0).getGoalsFor()).isEqualTo(7);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Guarani")).collect(Collectors.toList()).get(0).getGoalsFor()).isEqualTo(6);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Vasco")).collect(Collectors.toList()).get(0).getGoalsFor()).isEqualTo(3);
//	}
//
//	@Test
//	public void shouldCalculateTeamGoalsAgainst() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Athl??tico-PR")).collect(Collectors.toList()).get(0).getGoalsAgainst()).isEqualTo(4);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Gr??mio")).collect(Collectors.toList()).get(0).getGoalsAgainst()).isEqualTo(6);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Guarani")).collect(Collectors.toList()).get(0).getGoalsAgainst()).isEqualTo(6);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Vasco")).collect(Collectors.toList()).get(0).getGoalsAgainst()).isEqualTo(7);
//	}
//
//	@Test
//	public void shouldCalculateVictories() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Athl??tico-PR")).collect(Collectors.toList()).get(0).getVictories()).isEqualTo(2);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Vasco")).collect(Collectors.toList()).get(0).getVictories()).isEqualTo(1);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Guarani")).collect(Collectors.toList()).get(0).getVictories()).isEqualTo(1);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Gr??mio")).collect(Collectors.toList()).get(0).getVictories()).isEqualTo(1);
//	}
//
//	@Test
//	public void shouldCalculateTies() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Athl??tico-PR")).collect(Collectors.toList()).get(0).getTies()).isEqualTo(1);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Vasco")).collect(Collectors.toList()).get(0).getTies()).isZero();
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Guarani")).collect(Collectors.toList()).get(0).getTies()).isZero();
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Gr??mio")).collect(Collectors.toList()).get(0).getTies()).isEqualTo(1);
//	}
//
//	@Test
//	public void shouldCalculateDefeats() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Athl??tico-PR")).collect(Collectors.toList()).get(0).getDefeats()).isZero();
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Vasco")).collect(Collectors.toList()).get(0).getDefeats()).isEqualTo(2);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Guarani")).collect(Collectors.toList()).get(0).getDefeats()).isEqualTo(2);
//		assertThat(classification.stream().filter(c -> c.getTeam().equals("Gr??mio")).collect(Collectors.toList()).get(0).getDefeats()).isEqualTo(1);
//	}
//
//	@Test
//	public void shouldCalculateTeamsPosition() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.get(0).getTeam()).isEqualTo("Athl??tico-PR");
//		assertThat(classification.get(1).getTeam()).isEqualTo("Gr??mio");
//		assertThat(classification.get(2).getTeam()).isEqualTo("Guarani");
//		assertThat(classification.get(3).getTeam()).isEqualTo("Vasco");
//	}
//
//	@Test
//	public void shouldCalculateTotalOfMatchesPlayed() {
//		List<Match> matches = mockMatches();
//
//		Mockito
//			.when(matchRepository.findBySeason(Mockito.anyInt()))
//			.thenReturn(matches);
//
//		Mockito
//			.when(imageClient.findImage(Mockito.anyString()))
//		    .thenReturn(new ResponseEntity<String>("", HttpStatus.OK));
//
//		List<ClassificationDTO> classification = classificationService
//			.findClassificationBySeason(Mockito.anyInt());
//
//		assertThat(classification.get(0).getTotalMatches()).isEqualTo(3);
//		assertThat(classification.get(1).getTotalMatches()).isEqualTo(3);
//		assertThat(classification.get(2).getTotalMatches()).isEqualTo(3);
//		assertThat(classification.get(3).getTotalMatches()).isEqualTo(3);
//	}
//
//	@Test
//	public void shouldThrowAnErrorWhenSeasonDontExists() {
//		Throwable exception = catchThrowable(() -> classificationService.findClassificationBySeason(2004));
//
//		assertThat(exception)
//			.isInstanceOf(SeasonNotFoundException.class)
//			.hasMessage("Season not found!");
//
//	}
//
//	public List<Match> mockMatches() {
//		Match match1 = Match.builder()
//			.season(2003)
//			.round(1)
//			.homeTeam("Athl??tico-PR")
//			.visitorTeam("Gr??mio")
//			.homeTeamGoals(2)
//			.visitorTeamGoals(2).build();
//
//		Match match2 = Match.builder()
//			.season(2003)
//			.round(1)
//			.homeTeam("Vasco")
//			.visitorTeam("Guarani")
//			.homeTeamGoals(2)
//			.visitorTeamGoals(1).build();
//
//		Match match3 = Match.builder()
//			.season(2003)
//			.round(2)
//			.homeTeam("Athl??tico-PR")
//			.visitorTeam("Vasco")
//			.homeTeamGoals(2)
//			.visitorTeamGoals(0).build();
//
//		Match match4 = Match.builder()
//			.season(2003)
//			.round(2)
//			.homeTeam("Guarani")
//			.visitorTeam("Gr??mio")
//			.homeTeamGoals(3)
//			.visitorTeamGoals(1).build();
//
//		Match match5 = Match.builder()
//			.season(2003)
//			.round(3)
//			.homeTeam("Gr??mio")
//			.visitorTeam("Vasco")
//			.homeTeamGoals(4)
//			.visitorTeamGoals(1).build();
//
//		Match match6 = Match.builder()
//			.season(2003)
//			.round(3)
//			.homeTeam("Guarani")
//			.visitorTeam("Athl??tico-PR")
//			.homeTeamGoals(2)
//			.visitorTeamGoals(3).build();
//
//		return Arrays.asList(match1, match2, match3, match4, match5, match6);
//	}
}
