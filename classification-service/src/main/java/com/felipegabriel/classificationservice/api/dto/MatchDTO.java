package com.felipegabriel.classificationservice.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MatchDTO {

	private LocalDate date;

	private LocalTime time;

	private String day;

	private TeamDTO homeTeam;

	private TeamDTO visitorTeam;

	private String stadium;

	private Integer homeTeamGoals;

	private Integer visitorTeamGoals;
}
