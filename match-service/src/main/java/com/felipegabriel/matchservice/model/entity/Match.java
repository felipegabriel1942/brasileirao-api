package com.felipegabriel.matchservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    private LocalDateTime date;

    private String stadium;

    @Column(name = "home_team_goals")
    private Integer homeTeamGoals;

    @Column(name = "visitor_team_goals")
    private Integer visitorTeamGoals;

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "visitor_team")
    private String visitorTeam;

    @Column(name = "visitor_team_crest")
    private String visitorTeamCrest;

    @Column(name = "home_team_crest")
    private String homeTeamCrest;

    @Column(name = "match_number")
    private Integer matchNumber;

    private String division;

}

