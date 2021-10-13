package com.betulsahin.loanapplicationservice.services.abstractions;

import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.models.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<Loan> create(LoanDtoInput request);
    List<LoanDtoOutput> getAll();
    LoanDtoOutput getByIdentificationNumber(String identificationNumber);
    LoanDtoOutput getById(Long id);
    void deleteById(Long id);
}
