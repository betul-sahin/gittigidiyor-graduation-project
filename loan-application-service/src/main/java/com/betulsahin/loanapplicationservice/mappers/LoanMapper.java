package com.betulsahin.loanapplicationservice.mappers;

import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.repositories.CustomerRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LoanMapper {
    @Autowired
    protected CustomerRepository customerRepository;

    @Mapping(target = "customer", expression = "java(customerRepository.findByIdentificationNumber(request.getIdentificationNumber()).get())")
    public abstract Loan map(LoanDtoInput request);

    public abstract LoanDtoOutput mapToDto(Loan loan);
}
