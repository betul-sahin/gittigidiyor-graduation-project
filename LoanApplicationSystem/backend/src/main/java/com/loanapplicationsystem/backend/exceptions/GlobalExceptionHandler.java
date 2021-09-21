package com.loanapplicationsystem.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomerIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(CustomerIsAlreadyExistException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppErrorResponse> handleException(CustomerNotFoundException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({LoanNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppErrorResponse> handleException(LoanNotFoundException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private AppErrorResponse prepareErrorResponse(HttpStatus badRequest, String message) {
        AppErrorResponse response = new AppErrorResponse();
        response.setStatus(badRequest.value());
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());

        return response;
    }
}
