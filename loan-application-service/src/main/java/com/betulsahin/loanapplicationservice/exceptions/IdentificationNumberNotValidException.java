package com.betulsahin.loanapplicationservice.exceptions;

public class IdentificationNumberNotValidException extends RuntimeException {
    public IdentificationNumberNotValidException(String message) {
        super(message);
    }
}
