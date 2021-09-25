package com.betulsahin.loanapplicationservice.exceptions;

public class PhoneNumberNotValidException extends RuntimeException {
    public PhoneNumberNotValidException(String message) {
        super(message);
    }
}
