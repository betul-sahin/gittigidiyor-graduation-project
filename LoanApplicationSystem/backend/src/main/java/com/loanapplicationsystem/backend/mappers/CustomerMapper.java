package com.loanapplicationsystem.backend.mappers;

import com.loanapplicationsystem.backend.dtos.CustomerDtoInput;
import com.loanapplicationsystem.backend.dtos.CustomerDtoOutput;
import com.loanapplicationsystem.backend.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer map(CustomerDtoInput request);
    CustomerDtoOutput mapToDto(Customer customer);
}
