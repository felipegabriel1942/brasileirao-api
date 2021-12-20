package com.felipegabriel.matchservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class MatchDTO {

    private LocalDate date;

    private TeamDTO homeTeam;

    private TeamDTO visitorTeam;

    private String stadium;

    private Integer homeTeamGoals;

    private Integer visitorTeamGoals;
}
