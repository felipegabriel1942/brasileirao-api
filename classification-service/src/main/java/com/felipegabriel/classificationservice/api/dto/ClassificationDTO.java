package com.felipegabriel.classificationservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationDTO {

	private TeamDTO team;
	private int victories;
	private int ties;
	private int defeats;
	private int points;
	private int goalsFor;
	private int goalsAgainst;
	private int goalsDifference;
	private int totalMatches;

}
