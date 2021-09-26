package com.betulsahin.loanapplicationservice.services.abstractions;

import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.models.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<Loan> create(LoanDtoInput request, int score);
    List<LoanDtoOutput> getAll();
    LoanDtoOutput getById(Long id);
    Optional<Loan> update(LoanDtoInput request);
    void deleteById(Long id);
}
