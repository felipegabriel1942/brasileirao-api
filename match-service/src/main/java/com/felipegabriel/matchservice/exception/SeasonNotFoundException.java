package com.felipegabriel.matchservice.exception;

public class SeasonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SeasonNotFoundException(String s) {
        super(s);
    }
}
