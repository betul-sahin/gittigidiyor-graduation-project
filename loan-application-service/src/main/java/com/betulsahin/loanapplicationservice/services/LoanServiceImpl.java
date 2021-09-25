package com.betulsahin.loanapplicationservice.services;

import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.dtos.SmsRequest;
import com.betulsahin.loanapplicationservice.exceptions.CustomerNotFoundException;
import com.betulsahin.loanapplicationservice.exceptions.LoanNotFoundException;
import com.betulsahin.loanapplicationservice.mappers.LoanMapper;
import com.betulsahin.loanapplicationservice.models.Customer;
import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.models.enums.CreditResult;
import com.betulsahin.loanapplicationservice.repositories.CustomerRepository;
import com.betulsahin.loanapplicationservice.repositories.LoanRepository;
import com.betulsahin.loanapplicationservice.services.abstractions.LoanService;
import com.betulsahin.loanapplicationservice.services.abstractions.SmsSender;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.betulsahin.loanapplicationservice.utils.AppErrorMessages.CUSTOMER_NOT_FOUND;
import static com.betulsahin.loanapplicationservice.utils.AppErrorMessages.LOAN_NOT_FOUND;

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
    public Optional<Loan> create(LoanDtoInput request, int score) {

        Customer customer = customerRepository.findByIdentificationNumber(request.getIdentificationNumber()).
                orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
        int creditScore = score;

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
