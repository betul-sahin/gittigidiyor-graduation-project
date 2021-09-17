package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.dtos.CreditResultResponse;
import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.exceptions.CustomerNotFoundException;
import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.models.Loan;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import com.loanapplicationsystem.backend.repositories.CustomerRepository;
import com.loanapplicationsystem.backend.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.loanapplicationsystem.backend.utils.ErrorMessage.CUSTOMER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public Optional<Loan> create(LoanDtoInput request) {

        Customer customer = customerRepository.findById(request.getId()).
                orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
        int creditScore = 400;

        Loan loan = new Loan();

        if(creditScore < 500){
             loan.setCreditResult(CreditResult.REJECTION);
        }
        else if(creditScore >= 500 &&
                creditScore < 1000 &&
                customer.getMonthlyIncome() < 5000 ){
             loan.setCreditResult(CreditResult.APPROVAL);
             loan.setCreditAmount(10.000);
        }
        else if(creditScore >= 500 &&
                creditScore < 1000 &&
                customer.getMonthlyIncome() >= 5000){
             loan.setCreditResult(CreditResult.APPROVAL);
             loan.setCreditAmount(20.000);
        }
        else if(creditScore >= 1000){
             loan.setCreditResult(CreditResult.APPROVAL);
             double credit = loan.getCreditLimitMultiplier() * customer.getMonthlyIncome();
             loan.setCreditAmount(credit);
        }


        // bilgilendirme sms i gonder

        // endpointten onay durum bilgisi don

        // return new CreditResultResponse();

        return Optional.of(loan);
    }
}
