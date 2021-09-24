package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.dtos.ExceptionLogDto;
import com.loanapplicationsystem.backend.mappers.ExceptionLogMapper;
import com.loanapplicationsystem.backend.models.ExceptionLog;
import com.loanapplicationsystem.backend.repositories.ExceptionLogRepository;
import com.loanapplicationsystem.backend.services.abstractions.ExceptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExceptionLogServiceImpl implements ExceptionLogService {
    private final ExceptionLogRepository exceptionLogRepository;
    private final ExceptionLogMapper exceptionLogMapper;

    /**
     * logs exception detail to database.
     *
     * @param exceptionType the type of thrown exception
     * @param exceptionMessage the message of thrown exception
     * @param throwedDate the date of thrown exception
     */
    @Transactional
    @Override
    public void log(String exceptionType, String exceptionMessage, LocalDate throwedDate) {
        exceptionLogRepository.save(new ExceptionLog(exceptionType, exceptionMessage, throwedDate));
    }

    /**
     * gets all exceptions by filters
     *
     * @param exceptionType the type of thrown exception
     * @return list of LogDto object
     */
    @Transactional(readOnly = true)
    @Override
    public List<ExceptionLogDto> findAllByExceptionType(String exceptionType) {
        List<ExceptionLog> exceptionLogList = exceptionLogRepository.findAllByExceptionType(exceptionType);

        return exceptionLogList
                .stream()
                .map(exceptionLogMapper::mapToDto)
                .collect(Collectors.toList());

    }

    /**
     * gets all exceptions by filters
     *
     * @param throwedDate the date of thrown exception
     * @return list of LogDto object
     */
    @Transactional(readOnly = true)
    @Override
    public List<ExceptionLogDto> findAllByThrowedDate(LocalDate throwedDate){
        List<ExceptionLog> exceptionLogList = exceptionLogRepository.findAllByThrowedDate(throwedDate);

        return exceptionLogList
                .stream()
                .map(exceptionLogMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
