package com.felipegabriel.matchservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
