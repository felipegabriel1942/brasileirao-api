package com.felipegabriel.classificationservice.api.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer season;
	
	private Integer round;
	
	private LocalDate date;
	
	private LocalTime time;
	
	@Column(name = "match_day")
	private String day;
	
	@Column(name = "home_team")
	private String homeTeam;
	
	@Column(name = "visitor_team")
	private String visitorTeam;
	
	private String stadium;
	
	@Column(name = "home_team_goals")
	private Integer homeTeamGoals;
	
	@Column(name = "visitor_team_goals")
	private Integer visitorTeamGoals;
	
}
