package com.loanapplicationsystem.backend.services.abstractions;

import com.loanapplicationsystem.backend.dtos.ExceptionLogDto;

import java.time.LocalDate;
import java.util.List;

public interface ExceptionLogService {
    void log(String exceptionType, String exceptionMessage, LocalDate throwedDate);
    List<ExceptionLogDto> findAllByExceptionType(String exceptionType);
    List<ExceptionLogDto> findAllByThrowedDate(LocalDate throwedDate);
}
