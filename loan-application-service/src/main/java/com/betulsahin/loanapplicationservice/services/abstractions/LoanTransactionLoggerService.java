package com.betulsahin.loanapplicationservice.services.abstractions;

import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.models.LoanTransactionLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanTransactionLoggerService {
    void saveLoanTransaction(Loan loan);
    Page<List<LoanTransactionLogger>> getAllTransactionsWithDate(String transactionDate, Integer pageNumber, Integer pageSize, Pageable pageable);
    Page<List<LoanTransactionLogger>> getAllByCustomerId(long customerId, Integer pageNumber, Integer pageSize, Pageable pageable);
    Page<List<LoanTransactionLogger>> getAllByCreditResult(String creditResult, Integer pageNumber, Integer pageSize, Pageable pageable);
}
