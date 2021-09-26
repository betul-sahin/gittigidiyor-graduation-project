package com.betulsahin.loanapplicationservice.repositories;

import com.betulsahin.loanapplicationservice.models.LoanTransactionLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanTransactionLoggerRepository extends PagingAndSortingRepository<LoanTransactionLogger, Long> {

    @Query("SELECT t FROM LoanTransactionLogger t WHERE t.requestDate = ?1")
    Page<List<LoanTransactionLogger>> findAllByRequestDate(LocalDate requestDate, Pageable pageable);

    Page<List<LoanTransactionLogger>> findAllByCustomerId(long customerId, Pageable pageable);

    Page<List<LoanTransactionLogger>> findAllByCreditResult(String creditResult, Pageable pageable);
}
