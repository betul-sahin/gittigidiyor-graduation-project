package com.betulsahin.creditscoreservice.exceptions;

public class IdentificationNumberNotValidException extends RuntimeException{
    public IdentificationNumberNotValidException(String message){
        super(message);
    }
}
