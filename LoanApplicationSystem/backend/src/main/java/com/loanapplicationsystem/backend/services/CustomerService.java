package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.dtos.CustomerDtoInput;
import com.loanapplicationsystem.backend.dtos.CustomerDtoOutput;
import com.loanapplicationsystem.backend.exceptions.CustomerIsAlreadyExistException;
import com.loanapplicationsystem.backend.exceptions.CustomerNotFoundException;
import com.loanapplicationsystem.backend.exceptions.IdentificationNumberNotValidException;
import com.loanapplicationsystem.backend.mappers.CustomerMapper;
import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.repositories.CustomerRepository;
import com.loanapplicationsystem.backend.utils.ErrorMessages;
import com.loanapplicationsystem.backend.utils.IdentificationNumberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.loanapplicationsystem.backend.utils.ErrorMessages.CUSTOMER_FOUND;
import static com.loanapplicationsystem.backend.utils.ErrorMessages.CUSTOMER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final IdentificationNumberValidator identificationNumberValidator;

    @Transactional
    public Optional<Customer> create(CustomerDtoInput request) {
        // Is the identification number valid ?
        boolean isValidIdentificationNumber = identificationNumberValidator.
                test(request.getIdentificationNumber());

        if (!isValidIdentificationNumber) {
            throw new IdentificationNumberNotValidException(ErrorMessages.IDENTIFICATION_NUMBER_NOT_VALID);
        }

        // Is the customer already registered ?
        Optional<Customer> customerOptional = customerRepository.
                findByIdentificationNumber(request.getIdentificationNumber());

        if (customerOptional.isPresent()) {
            throw new CustomerIsAlreadyExistException(CUSTOMER_FOUND);
        }

        Customer customer = customerMapper.map(request);

        Customer savedCustomer = customerRepository.save(customer);

        return Optional.of(savedCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDtoOutput> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDtoOutput findById(long id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));

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
