package com.loanapplicationsystem.backend.utils;

import com.loanapplicationsystem.backend.exceptions.LoanTransactionLoggerDateTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
