package com.betulsahin.loanapplicationservice.repositories;

@Repository
public interface LoanTransactionLoggerRepository extends PagingAndSortingRepository<LoanTransactionLogger, Long> {

    @Query("SELECT t FROM LoanTransactionLogger t WHERE t.requestDate = ?1")
    Page<List<LoanTransactionLogger>> findAllByRequestDate(LocalDate requestDate, Pageable pageable);

    Page<List<LoanTransactionLogger>> findAllByCustomerId(long customerId, Pageable pageable);

    Page<List<LoanTransactionLogger>> findAllByCreditResult(String creditResult, Pageable pageable);
}
