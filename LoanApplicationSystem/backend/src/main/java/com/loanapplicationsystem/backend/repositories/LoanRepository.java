package com.loanapplicationsystem.backend.repositories;

import com.loanapplicationsystem.backend.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
