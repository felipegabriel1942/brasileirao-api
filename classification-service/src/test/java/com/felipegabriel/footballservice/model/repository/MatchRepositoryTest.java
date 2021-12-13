package com.felipegabriel.footballservice.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MatchRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
//	@Autowired
//	MatchRepository matchRepository;
	
//	@Test
//	public void shouldReturnMatchesBySeason() {
//		List<Match> matches = mockMatches();
//
//		matches.forEach(testEntityManager::persist);
//
//		List<Match> result = matchRepository.findBySeason(2003);
//
//		assertThat(result.size()).isEqualTo(matches.size());
//	}
//
//	public List<Match> mockMatches() {
//		Match match1 = Match.builder()
//			.season(2003)
//			.round(1)
//			.homeTeam("Athlético-PR")
//			.visitorTeam("Grêmio")
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
//			.homeTeam("Athlético-PR")
//			.visitorTeam("Vasco")
//			.homeTeamGoals(2)
//			.visitorTeamGoals(0).build();
//
//		Match match4 = Match.builder()
//			.season(2003)
//			.round(2)
//			.homeTeam("Guarani")
//			.visitorTeam("Grêmio")
//			.homeTeamGoals(3)
//			.visitorTeamGoals(1).build();
//
//		Match match5 = Match.builder()
//			.season(2003)
//			.round(3)
//			.homeTeam("Grêmio")
//			.visitorTeam("Vasco")
//			.homeTeamGoals(4)
//			.visitorTeamGoals(1).build();
//
//		Match match6 = Match.builder()
//			.season(2003)
//			.round(3)
//			.homeTeam("Guarani")
//			.visitorTeam("Athlético-PR")
//			.homeTeamGoals(2)
//			.visitorTeamGoals(3).build();
//
//		return Arrays.asList(match1, match2, match3, match4, match5, match6);
//	}
}
