package com.betulsahin.loanapplicationservice.services.abstractions;

public interface LoanTransactionLoggerService {
    void saveLoanTransaction(Loan loan);
    Page<List<LoanTransactionLogger>> getAllTransactionsWithDate(String transactionDate, Integer pageNumber, Integer pageSize, Pageable pageable);
    Page<List<LoanTransactionLogger>> getAllByCustomerId(long customerId, Integer pageNumber, Integer pageSize, Pageable pageable);
    Page<List<LoanTransactionLogger>> getAllByCreditResult(String creditResult, Integer pageNumber, Integer pageSize, Pageable pageable);
}
