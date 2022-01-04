package com.felipegabriel.scrapeservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
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
