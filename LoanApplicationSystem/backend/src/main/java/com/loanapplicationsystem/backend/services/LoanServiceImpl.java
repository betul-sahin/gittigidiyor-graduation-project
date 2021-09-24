package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.dtos.LoanDtoOutput;
import com.loanapplicationsystem.backend.dtos.SmsRequest;
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
import com.loanapplicationsystem.backend.services.abstractions.LoanService;
import com.loanapplicationsystem.backend.services.abstractions.LoanTransactionLoggerService;
import com.loanapplicationsystem.backend.services.abstractions.SmsSender;
import com.loanapplicationsystem.backend.utils.ClientRequestInfo;
import com.loanapplicationsystem.backend.utils.LoanValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LoanServiceImpl implements LoanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanServiceImpl.class);

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final LoanMapper loanMapper;
    private final SmsSender smsSender;

    @Transactional
    @Override
    public Optional<Loan> create(LoanDtoInput request) {

        Customer customer = customerRepository.findById(request.getCustomerId()).
                orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
        int creditScore = 800;

        Loan loan = new Loan();
        if (creditScore < 500) {
            loan.setCreditResult(CreditResult.REJECTION);
        }
        else if (creditScore >= 500 && creditScore < 1000 && customer.getMonthlyIncome() < 5000) {
            loan.setCreditResult(CreditResult.APPROVAL);
            loan.setCreditAmount(10.000);
        }
        else if (creditScore >= 500 && creditScore < 1000 && customer.getMonthlyIncome() >= 5000) {
            loan.setCreditResult(CreditResult.APPROVAL);
            loan.setCreditAmount(20.000);
        }
        else if (creditScore >= 1000) {
            loan.setCreditResult(CreditResult.APPROVAL);
            double credit = loan.getCreditLimitMultiplier() * customer.getMonthlyIncome();
            loan.setCreditAmount(credit);
        }

        // save loan object to db
        loan.setCustomer(customer);
        Loan savedLoan = loanRepository.save(loan);
        LOGGER.info("Save loan object {}", savedLoan);

        // send credit informations via sms
        SmsRequest smsRequest = new SmsRequest(customer.getPhoneNumber(), "bu bir mesaj");
        //smsSender.sendSms(smsRequest);
        LOGGER.info("Send sms {}", request);

        // endpointten onay durum bilgisi ve kredi bilgisi dön

        // return new CreditResultResponse();

        return Optional.of(savedLoan);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanDtoOutput> findAll() {
        return loanRepository.findAll()
                .stream()
                .map(loanMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public LoanDtoOutput findById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(LOAN_NOT_FOUND));

        return loanMapper.mapToDto(loan);
    }

    @Transactional
    @Override
    public Optional<Loan> update(LoanDtoInput request) {

        LOGGER.info("Update loan object {}", request);

        return Optional.of(new Loan());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        loanRepository.deleteById(id);

        LOGGER.info("Delete this customer id {}", id);
    }
}
