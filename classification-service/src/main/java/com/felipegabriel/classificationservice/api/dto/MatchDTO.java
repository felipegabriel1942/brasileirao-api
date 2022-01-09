package com.felipegabriel.classificationservice.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MatchDTO {

	private Integer season;

	private Integer round;

	private LocalDateTime date;

	private String stadium;

	private Integer homeTeamGoals;

	private Integer visitorTeamGoals;

	private String homeTeam;

	private String visitorTeam;

	private String visitorTeamCrest;

	private String homeTeamCrest;

	private Integer matchNumber;

	private String division;

}
