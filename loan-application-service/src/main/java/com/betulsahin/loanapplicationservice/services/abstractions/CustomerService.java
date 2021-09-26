package com.betulsahin.loanapplicationservice.services.abstractions;

import com.betulsahin.loanapplicationservice.dtos.CustomerDtoInput;
import com.betulsahin.loanapplicationservice.dtos.CustomerDtoOutput;
import com.betulsahin.loanapplicationservice.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> create(CustomerDtoInput request);
    List<CustomerDtoOutput> getAll();
    CustomerDtoOutput getById(Long id);
    Optional<Customer> update(CustomerDtoInput request);
    void deleteById(Long id);
}
