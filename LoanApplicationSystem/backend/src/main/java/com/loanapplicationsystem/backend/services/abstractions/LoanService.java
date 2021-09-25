package com.loanapplicationsystem.backend.services.abstractions;

import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.dtos.LoanDtoOutput;
import com.loanapplicationsystem.backend.models.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<Loan> create(LoanDtoInput request, int score);
    List<LoanDtoOutput> findAll();
    LoanDtoOutput findById(Long id);
    Optional<Loan> update(LoanDtoInput request);
    void deleteById(Long id);
}
