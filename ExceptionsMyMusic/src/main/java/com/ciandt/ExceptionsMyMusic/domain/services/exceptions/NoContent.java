package com.ciandt.ExceptionsMyMusic.domain.services.exceptions;

public class NoContent extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NoContent(String message) {
        super(message);
    }
}
