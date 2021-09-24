package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.models.Loan;
import com.loanapplicationsystem.backend.models.LoanTransactionLogger;
import com.loanapplicationsystem.backend.repositories.LoanTransactionLoggerRepository;
import com.loanapplicationsystem.backend.services.abstractions.LoanTransactionLoggerService;
import com.loanapplicationsystem.backend.utils.ClientRequestInfo;
import com.loanapplicationsystem.backend.utils.LoanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionLoggerServiceImpl implements LoanTransactionLoggerService {
    private final ClientRequestInfo clientRequestInfo;
    private final LoanTransactionLoggerRepository loanTransactionLoggerRepository;

    @Override
    public void saveLoanTransaction(Loan loan) {
        LoanTransactionLogger transactionLogger = new LoanTransactionLogger();
        transactionLogger.setCustomerId(loan.getCustomer().getId());
        transactionLogger.setCreditAmount(loan.getCreditAmount());
        transactionLogger.setCreditResult(loan.getCreditResult());
        transactionLogger.setRequestDate(LocalDate.now());
        transactionLogger.setClientIpAdress(clientRequestInfo.getClientIpAdress());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());

        loanTransactionLoggerRepository.save(transactionLogger);
    }

    @Override
    public Page<List<LoanTransactionLogger>> getAllTransactionsWithDate(String transactionDate,
                                                                        Integer pageNumber,
                                                                        Integer pageSize,
                                                                        Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LoanValidator.validateTransactionDate(transactionDate, formatter);
        LocalDate transactionDateResult = LocalDate.parse(transactionDate, formatter);

        if(pageNumber != null && pageSize != null){
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByRequestDate(transactionDateResult, pageable);
    }

    @Override
    public Page<List<LoanTransactionLogger>> getAllByCustomerId(long customerId,
                                                                Integer pageNumber,
                                                                Integer pageSize,
                                                                Pageable pageable){
        if(pageNumber != null && pageSize != null){
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByCustomerId(customerId, pageable);
    }

    @Override
    public Page<List<LoanTransactionLogger>> getAllByCreditResult(String creditResult,
                                                                  Integer pageNumber,
                                                                  Integer pageSize,
                                                                  Pageable pageable){
        if(pageNumber != null && pageSize != null){
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByCreditResult(creditResult, pageable);
    }
}
