package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.dtos.LoanDtoOutput;
import com.loanapplicationsystem.backend.exceptions.CustomerNotFoundException;
import com.loanapplicationsystem.backend.exceptions.LoanNotFoundException;
import com.loanapplicationsystem.backend.mappers.LoanMapper;
import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.models.Loan;
import com.loanapplicationsystem.backend.models.LoanTransactionLogger;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import com.loanapplicationsystem.backend.repositories.CustomerRepository;
import com.loanapplicationsystem.backend.repositories.LoanRepository;
import com.loanapplicationsystem.backend.repositories.LoanTransactionLoggerRepository;
import com.loanapplicationsystem.backend.utils.ClientRequestInfo;
import com.loanapplicationsystem.backend.utils.LoanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.loanapplicationsystem.backend.utils.ErrorMessages.CUSTOMER_NOT_FOUND;
import static com.loanapplicationsystem.backend.utils.ErrorMessages.LOAN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final LoanTransactionLoggerRepository loanTransactionLoggerRepository;
    private final LoanMapper loanMapper;
    private final ClientRequestInfo clientRequestInfo;

    @Transactional
    public Optional<Loan> create(LoanDtoInput request) {

        Customer customer = customerRepository.findById(request.getId()).
                orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
        int creditScore = 400;

        Loan loan = new Loan();

        if (creditScore < 500) {
            loan.setCreditResult(CreditResult.REJECTION);
        } else if (creditScore >= 500 &&
                creditScore < 1000 &&
                customer.getMonthlyIncome() < 5000) {
            loan.setCreditResult(CreditResult.APPROVAL);
            loan.setCreditAmount(10.000);
        } else if (creditScore >= 500 &&
                creditScore < 1000 &&
                customer.getMonthlyIncome() >= 5000) {
            loan.setCreditResult(CreditResult.APPROVAL);
            loan.setCreditAmount(20.000);
        } else if (creditScore >= 1000) {
            loan.setCreditResult(CreditResult.APPROVAL);
            double credit = loan.getCreditLimitMultiplier() * customer.getMonthlyIncome();
            loan.setCreditAmount(credit);
        }

        // save loan object to db
        Loan savedLoan = loanRepository.save(loan);

        // save transactions of loan
        this.saveLoanTransaction(customer, savedLoan);

        // bilgilendirme sms i gonder

        // endpointten onay durum bilgisi ve kredi bilgisi dön

        // return new CreditResultResponse();

        return Optional.of(loan);
    }

    @Transactional(readOnly = true)
    public List<LoanDtoOutput> findAll() {
        return loanRepository.findAll()
                .stream()
                .map(loanMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LoanDtoOutput findById(long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(LOAN_NOT_FOUND));

        return loanMapper.mapToDto(loan);
    }

    @Transactional
    public Optional<Loan> update(LoanDtoInput request) {
        return Optional.of(new Loan());
    }

    @Transactional
    public void deleteById(long id) {
        loanRepository.deleteById(id);
    }

    private void saveLoanTransaction(Customer customer,
                                     Loan loan) {

        LoanTransactionLogger transactionLogger = new LoanTransactionLogger();
        transactionLogger.setCustomerId(customer.getId());
        transactionLogger.setCreditAmount(loan.getCreditAmount());
        transactionLogger.setCreditResult(loan.getCreditResult());
        transactionLogger.setRequestDate(LocalDate.now());
        transactionLogger.setClientIpAdress(clientRequestInfo.getClientIpAdress());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());

        this.loanTransactionLoggerRepository.save(transactionLogger);
    }

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

    public Page<List<LoanTransactionLogger>> getAllByCustomerId(long customerId,
                                                                Integer pageNumber,
                                                                Integer pageSize,
                                                                Pageable pageable){
        if(pageNumber != null && pageSize != null){
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return loanTransactionLoggerRepository.findAllByCustomerId(customerId, pageable);
    }

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
