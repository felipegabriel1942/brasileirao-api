package com.felipegabriel.matchservice.model.repository;

import java.util.List;

import com.felipegabriel.matchservice.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer>{

    List<Match> findBySeasonAndDivisionContaining(int season, String division);

    List<Match> findBySeasonAndRoundAndDivision(Integer season, Integer round, String division);

}