package com.loanapplicationsystem.backend.mappers;

import com.loanapplicationsystem.backend.dtos.ExceptionLogDto;
import com.loanapplicationsystem.backend.models.ExceptionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExceptionLogMapper {
    ExceptionLogDto mapToDto(ExceptionLog exceptionLog);
}
