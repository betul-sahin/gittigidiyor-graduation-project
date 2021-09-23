package com.loanapplicationsystem.backend.utils;

import com.loanapplicationsystem.backend.exceptions.LoanTransactionLoggerDateTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LoanValidator {
    public static void validateTransactionDate(String transactionDate,
                                               DateTimeFormatter formatter) {
        try{
            LocalDate.parse(transactionDate, formatter);
        }catch (DateTimeParseException e){
            throw new LoanTransactionLoggerDateTimeParseException(ErrorMessages.DATE_FORMAT_WRONG);
        }
    }
}
