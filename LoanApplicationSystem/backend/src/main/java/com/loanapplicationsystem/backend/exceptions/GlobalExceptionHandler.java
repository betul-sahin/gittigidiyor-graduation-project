package com.loanapplicationsystem.backend.exceptions;

import com.loanapplicationsystem.backend.dtos.AppErrorResponse;
import com.loanapplicationsystem.backend.services.abstractions.ExceptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ExceptionLogService exceptionLogService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CustomerIsAlreadyExistException.class})
    @ResponseBody
    public AppErrorResponse handleException(CustomerIsAlreadyExistException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getClass().getSimpleName());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({LoanTransactionLoggerDateTimeParseException.class})
    @ResponseBody
    public AppErrorResponse handleException(LoanTransactionLoggerDateTimeParseException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getClass().getSimpleName());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IdentificationNumberNotValidException.class})
    @ResponseBody
    public AppErrorResponse handleException(IdentificationNumberNotValidException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getClass().getSimpleName());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PhoneNumberNotValidException.class})
    @ResponseBody
    public AppErrorResponse handleException(PhoneNumberNotValidException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getClass().getSimpleName());
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CustomerNotFoundException.class})
    @ResponseBody
    public AppErrorResponse handleException(CustomerNotFoundException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getClass().getSimpleName());
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({LoanNotFoundException.class})
    @ResponseBody
    public AppErrorResponse handleException(LoanNotFoundException ex){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getClass().getSimpleName());
        return response;
    }

    private AppErrorResponse prepareErrorResponse(HttpStatus badRequest, String exceptionMessage, String exceptionType) {
        AppErrorResponse response = new AppErrorResponse();
        response.setStatus(badRequest.value());
        response.setMessage(exceptionMessage);
        response.setTimestamp(System.currentTimeMillis());

        exceptionLogService.log(exceptionType, exceptionMessage, LocalDate.now());

        return response;
    }
}
