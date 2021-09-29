package com.betulsahin.loanapplicationservice.repositories;

import com.betulsahin.loanapplicationservice.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT loan FROM Loan loan WHERE loan.customer.identificationNumber = ?1")
    Optional<Loan> findByIdentificationNumber(String identificationNumber);
}
