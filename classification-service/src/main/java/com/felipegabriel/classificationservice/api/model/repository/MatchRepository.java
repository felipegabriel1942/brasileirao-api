package com.felipegabriel.classificationservice.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipegabriel.classificationservice.api.model.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer>{
	
	List<Match> findBySeason(Integer season);
	
	List<Match> findBySeasonAndRound(Integer season, Integer round);

}
