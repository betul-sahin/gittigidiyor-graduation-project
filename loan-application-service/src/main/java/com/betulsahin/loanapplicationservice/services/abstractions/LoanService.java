package com.betulsahin.loanapplicationservice.services.abstractions;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<Loan> create(LoanDtoInput request, int score);
    List<LoanDtoOutput> findAll();
    LoanDtoOutput findById(Long id);
    Optional<Loan> update(LoanDtoInput request);
    void deleteById(Long id);
}
