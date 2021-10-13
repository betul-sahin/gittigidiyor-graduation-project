package com.betulsahin.loanapplicationservice.services;

import com.betulsahin.loanapplicationservice.client.CreditScoreService;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.dtos.request.SmsRequest;
import com.betulsahin.loanapplicationservice.exceptions.CustomerNotFoundException;
import com.betulsahin.loanapplicationservice.exceptions.IdentificationNumberNotValidException;
import com.betulsahin.loanapplicationservice.exceptions.LoanNotFoundException;
import com.betulsahin.loanapplicationservice.mappers.LoanMapper;
import com.betulsahin.loanapplicationservice.models.Customer;
import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.models.enums.CreditResult;
import com.betulsahin.loanapplicationservice.repositories.CustomerRepository;
import com.betulsahin.loanapplicationservice.repositories.LoanRepository;
import com.betulsahin.loanapplicationservice.services.abstractions.LoanService;
import com.betulsahin.loanapplicationservice.services.abstractions.SmsSender;
import com.betulsahin.loanapplicationservice.services.validators.IdentificationNumberValidator;
import com.betulsahin.loanapplicationservice.utils.AppErrorMessages;
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

    private final CreditScoreService creditScoreService;
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final IdentificationNumberValidator identificationNumberValidator;
    private final LoanMapper loanMapper;
    private final SmsSender smsSender;

    /**
     * Creates new loan to database.
     *
     * @param request the dto object required to create a new loan application
     * @param score the credit score point of the customer
     * @return the saved loan application as {@link Optional<Loan>}
     */
    @Transactional
    @Override
    public Optional<Loan> create(LoanDtoInput request) {

        int score = 500;//creditScoreService.getCreditScoreByIdentificationNumber(request.getIdentificationNumber());

        // Is the identification number valid ?
        boolean isValidIdentificationNumber = identificationNumberValidator.
                test(request.getIdentificationNumber());

        if (!isValidIdentificationNumber) {
            throw new IdentificationNumberNotValidException(AppErrorMessages.IDENTIFICATION_NUMBER_NOT_VALID);
        }

        LOGGER.info("Validate identification number {}", request.getIdentificationNumber());

        // If he/she is current customer or not
        Optional<Customer> customerOptional = customerRepository.findByIdentificationNumber(
                request.getIdentificationNumber());
        Customer customer = new Customer();
        if(!customerOptional.isPresent()){
            // new customer
            customer.setIdentificationNumber(request.getIdentificationNumber());
            customer.setFirstName(request.getFirstName());
            customer.setLastName(request.getLastName());
            customer.setPhoneNumber(request.getPhoneNumber());
            customer.setMonthlyIncome(request.getMonthlyIncome());
            customerRepository.save(customer);
        }else{
            // current customer
            customer = customerOptional.get();
        }

        Loan loan = this.calculateCreditAmountAndCreditResult(customer, score);

        // save loan object to db
        loan.setCustomer(customer);
        Loan savedLoan = loanRepository.save(loan);
        LOGGER.info("Save loan object {}", savedLoan);

        // send credit informations via sms
        SmsRequest smsRequest = new SmsRequest(customer.getPhoneNumber(), "bu bir mesaj");
        //smsSender.sendSms(smsRequest);
        LOGGER.info("Send sms {}", request);

        return Optional.of(savedLoan);
    }

    /**
     * Calculates credit amount and credit result of customer.
     *
     * @param customer the customer object required to calculate result of credit
     * @param creditScore the credit score point of the customer
     * @return a {@link Loan}
     */
    private Loan calculateCreditAmountAndCreditResult(Customer customer, int creditScore){

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

        return loan;
    }

    /**
     * Gets all loan applications.
     *
     * @return a {@link List<LoanDtoOutput>}
     */
    @Transactional(readOnly = true)
    @Override
    public List<LoanDtoOutput> getAll() {
        return loanRepository.findAll()
                .stream()
                .map(loanMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets the loan by id.
     *
     * @param id required to filter loan applications
     * @return a {@link LoanDtoOutput}
     */
    @Transactional(readOnly = true)
    @Override
    public LoanDtoOutput getById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(LOAN_NOT_FOUND));

        return loanMapper.mapToDto(loan);
    }

    /**
     * Gets the loan by identification number.
     *
     * @param identificationNumber required to filter loan applications
     * @return a {@link LoanDtoOutput}
     */
    @Transactional(readOnly = true)
    @Override
    public LoanDtoOutput getByIdentificationNumber(String identificationNumber){
        Loan loan = loanRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new LoanNotFoundException(LOAN_NOT_FOUND));

        return loanMapper.mapToDto(loan);
    }

    /**
     * Deletes the loan by id.
     *
     * @param id required to delete loan applications
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        if(!loanOptional.isPresent()){
            throw new LoanNotFoundException(LOAN_NOT_FOUND);
        }

        loanRepository.delete(loanOptional.get());

        LOGGER.info("Delete this customer id {}", id);
    }
}
