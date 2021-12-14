package com.felipegabriel.classificationservice.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TeamDTO {
    private String teamName;

    private String teamCrest;
}
