package com.loanapplicationsystem.backend.repositories;

import com.loanapplicationsystem.backend.models.ExceptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Long> {
    List<ExceptionLog> findAllByExceptionType(String exceptionType);
    List<ExceptionLog> findAllByThrowedDate(LocalDate throwedDate);
}
