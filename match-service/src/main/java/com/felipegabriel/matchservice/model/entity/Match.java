package com.felipegabriel.matchservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate date;

    private LocalTime time;

    @Column(name = "match_day")
    private String day;

    private String stadium;

    @Column(name = "home_team_goals")
    private Integer homeTeamGoals;

    @Column(name = "visitor_team_goals")
    private Integer visitorTeamGoals;

    @JoinColumn(name = "fk_home_team")
    @ManyToOne
    private Team homeTeam;

    @JoinColumn(name = "fk_visitor_team")
    @ManyToOne
    private Team visitorTeam;

}

