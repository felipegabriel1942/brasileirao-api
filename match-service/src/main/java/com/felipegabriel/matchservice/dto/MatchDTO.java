package com.felipegabriel.matchservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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
