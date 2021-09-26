package com.betulsahin.loanapplicationservice.services.validators;

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
