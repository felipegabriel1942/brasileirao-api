package com.felipegabriel.classificationservice.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationDTO {

	private String team;
	private String teamCrest;
	private int victories;
	private int ties;
	private int defeats;
	private int points;
	private int goalsFor;
	private int goalsAgainst;
	private int goalsDifference;
	private int totalMatches;

}
