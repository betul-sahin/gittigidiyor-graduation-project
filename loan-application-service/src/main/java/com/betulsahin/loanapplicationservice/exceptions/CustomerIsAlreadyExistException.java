package com.betulsahin.loanapplicationservice.exceptions;

public class CustomerIsAlreadyExistException extends RuntimeException {
    public CustomerIsAlreadyExistException(String message) {
        super(message);
    }
}
