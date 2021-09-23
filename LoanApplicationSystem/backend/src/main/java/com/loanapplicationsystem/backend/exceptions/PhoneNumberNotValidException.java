package com.loanapplicationsystem.backend.exceptions;

public class PhoneNumberNotValidException extends RuntimeException {
    public PhoneNumberNotValidException(String message) {
        super(message);
    }
}
