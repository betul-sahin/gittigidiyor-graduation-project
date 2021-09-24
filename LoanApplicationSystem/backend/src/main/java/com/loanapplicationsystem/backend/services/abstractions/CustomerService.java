package com.loanapplicationsystem.backend.services.abstractions;

import com.loanapplicationsystem.backend.dtos.CustomerDtoInput;
import com.loanapplicationsystem.backend.dtos.CustomerDtoOutput;
import com.loanapplicationsystem.backend.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> create(CustomerDtoInput request);
    List<CustomerDtoOutput> findAll();
    CustomerDtoOutput findById(Long id);
    Optional<Customer> update(CustomerDtoInput request);
    void deleteById(Long id);
}
