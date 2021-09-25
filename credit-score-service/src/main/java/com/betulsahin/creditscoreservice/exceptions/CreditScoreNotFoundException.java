package com.betulsahin.creditscoreservice.exceptions;

public class CreditScoreNotFoundException extends RuntimeException {
    public CreditScoreNotFoundException(String message) {
        super(message);
    }
}
