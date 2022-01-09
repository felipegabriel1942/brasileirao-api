package com.felipegabriel.matchservice.exception;

public class DivisionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DivisionNotFoundException(String s) {
        super(s);
    }
}
