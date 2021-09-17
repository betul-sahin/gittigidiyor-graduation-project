package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.dtos.CustomerDtoInput;
import com.loanapplicationsystem.backend.dtos.CustomerDtoOutput;
import com.loanapplicationsystem.backend.exceptions.CustomerIsAlreadyExistException;
import com.loanapplicationsystem.backend.mappers.CustomerMapper;
import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.loanapplicationsystem.backend.utils.ErrorMessage.CUSTOMER_FOUND;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public Optional<Customer> create(CustomerDtoInput request) {
        Optional<Customer> customerOptional = customerRepository.
                findByIdentificationNumber(request.getIdentificationNumber());

        if(customerOptional.isPresent()){
            throw new CustomerIsAlreadyExistException(
                    String.format(CUSTOMER_FOUND, request.getIdentificationNumber()));
        }

        Customer customer = customerRepository.save(
                customerMapper.map(request));

        return Optional.of(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDtoOutput> findAll(){
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDtoOutput findById(long id) {
        Customer customer = customerRepository.findById(id).get();

        return customerMapper.mapToDto(customer);
    }

    @Transactional
    public Optional<Customer> update(CustomerDtoInput request) {
        Customer customer = customerRepository.save(
                customerMapper.map(request));

        return Optional.of(customer);
    }

    @Transactional
    public void deleteById(long id) {
        customerRepository.deleteById(id);
    }
}
