package com.betulsahin.loanapplicationservice.repositories;

import com.betulsahin.loanapplicationservice.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
