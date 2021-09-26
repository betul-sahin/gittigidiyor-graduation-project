package com.betulsahin.loanapplicationservice.services.validators;

import com.betulsahin.loanapplicationservice.exceptions.LoanTransactionLoggerDateTimeParseException;
import com.betulsahin.loanapplicationservice.utils.AppErrorMessages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LoanValidator {
    public static void validateTransactionDate(String transactionDate,
                                               DateTimeFormatter formatter) {
        try{
            LocalDate.parse(transactionDate, formatter);
        }catch (DateTimeParseException e){
            throw new LoanTransactionLoggerDateTimeParseException(AppErrorMessages.DATE_FORMAT_WRONG);
        }
    }
}
