package com.betulsahin.loanapplicationservice.mappers;

import com.betulsahin.loanapplicationservice.dtos.CustomerDtoInput;
import com.betulsahin.loanapplicationservice.dtos.CustomerDtoOutput;
import com.betulsahin.loanapplicationservice.models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer map(CustomerDtoInput request);
    CustomerDtoOutput mapToDto(Customer customer);
}
