package com.loanapplicationsystem.backend.mappers;

import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.dtos.LoanDtoOutput;
import com.loanapplicationsystem.backend.models.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    Loan map(LoanDtoInput request);
    LoanDtoOutput mapToDto(Loan loan);
}
