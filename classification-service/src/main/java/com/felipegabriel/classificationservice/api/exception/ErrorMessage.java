package com.felipegabriel.classificationservice.api.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {

    MSG_ERR_SEASON_NOT_FOUND("Season not found.");

    private final String message;
}
