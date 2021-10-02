package com.betulsahin.loanapplicationservice.services;

import com.betulsahin.loanapplicationservice.dtos.CustomerDtoOutput;
import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.models.LoanTransactionLogger;
import com.betulsahin.loanapplicationservice.repositories.LoanTransactionLoggerRepository;
import com.betulsahin.loanapplicationservice.services.abstractions.LoanTransactionLoggerService;
import com.betulsahin.loanapplicationservice.services.validators.LoanValidator;
import com.betulsahin.loanapplicationservice.utils.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanTransactionLoggerServiceImpl.class);

    private final ClientRequestInfo clientRequestInfo;
    private final LoanTransactionLoggerRepository loanTransactionLoggerRepository;

    /**
     * Saves transactions of the loan.
     *
     * @param loan the loan object required to save a new transaction
     */
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

        LOGGER.info("Save transactions for loan {}", transactionLogger);
    }

    /**
     * Gets all transactions operations with date.
     *
     * @param transactionDate the date of the transaction required to filter transactions
     * @param pageNumber the current page number of transaction page
     * @param pageSize max page size
     * @param pageable the pageable object required to paging process
     * @return a {@link Page<List<LoanTransactionLogger>>}
     */
    @Override
    public Page<List<LoanTransactionLogger>> getAllTransactionsWithDate(String transactionDate,
                                                                        Integer pageNumber,
                                                                        Integer pageSize,
                                                                        Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LoanValidator.validateTransactionDate(transactionDate, formatter);
        LocalDate transactionDateResult = LocalDate.parse(transactionDate, formatter);

        LOGGER.info("Validate transaction date {}", transactionDate);

        if (pageNumber != null && pageSize != null) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByRequestDate(transactionDateResult, pageable);
    }

    /**
     * Gets all transactions operations with customer id.
     *
     * @param customerId the id of the customer required to filter transactions
     * @param pageNumber the current page number of transaction page
     * @param pageSize max page size
     * @param pageable the pageable object required to paging process
     * @return a {@link Page<List<LoanTransactionLogger>>}
     */
    @Override
    public Page<List<LoanTransactionLogger>> getAllByCustomerId(long customerId,
                                                                Integer pageNumber,
                                                                Integer pageSize,
                                                                Pageable pageable) {
        if (pageNumber != null && pageSize != null) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByCustomerId(customerId, pageable);
    }

    /**
     * Gets all transactions operations with credit result.
     *
     * @param creditResult the credit result of the loan application
     * @param pageNumber the current page number of transaction page
     * @param pageSize max page size
     * @param pageable the pageable object required to paging process
     * @return a {@link Page<List<LoanTransactionLogger>>}
     */
    @Override
    public Page<List<LoanTransactionLogger>> getAllByCreditResult(String creditResult,
                                                                  Integer pageNumber,
                                                                  Integer pageSize,
                                                                  Pageable pageable) {
        if (pageNumber != null && pageSize != null) {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByCreditResult(creditResult, pageable);
    }
}
