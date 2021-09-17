package com.loanapplicationsystem.backend.exceptions;

public class CustomerIsAlreadyExistException extends RuntimeException {
    public CustomerIsAlreadyExistException(String message) {
        super(message);
    }
}
