package com.loanapplicationsystem.backend.exceptions;

public class IdentificationNumberNotValidException extends RuntimeException {
    public IdentificationNumberNotValidException(String message) {
        super(message);
    }
}
