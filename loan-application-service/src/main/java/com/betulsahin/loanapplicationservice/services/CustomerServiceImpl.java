package com.betulsahin.loanapplicationservice.services;

import com.betulsahin.loanapplicationservice.dtos.CustomerDtoInput;
import com.betulsahin.loanapplicationservice.dtos.CustomerDtoOutput;
import com.betulsahin.loanapplicationservice.exceptions.CustomerIsAlreadyExistException;
import com.betulsahin.loanapplicationservice.exceptions.CustomerNotFoundException;
import com.betulsahin.loanapplicationservice.exceptions.IdentificationNumberNotValidException;
import com.betulsahin.loanapplicationservice.exceptions.PhoneNumberNotValidException;
import com.betulsahin.loanapplicationservice.mappers.CustomerMapper;
import com.betulsahin.loanapplicationservice.models.Customer;
import com.betulsahin.loanapplicationservice.repositories.CustomerRepository;
import com.betulsahin.loanapplicationservice.services.abstractions.CustomerService;
import com.betulsahin.loanapplicationservice.services.validators.IdentificationNumberValidator;
import com.betulsahin.loanapplicationservice.services.validators.PhoneNumberValidator;
import com.betulsahin.loanapplicationservice.utils.AppErrorMessages;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.betulsahin.loanapplicationservice.utils.AppErrorMessages.*;

/**
 * The CustomerServiceImpl class implements a CustomerService that
 * simply performs database operations such as create, update, delete.
 *
 * @author  Betül Şahin
 * @version 1.0
 * @since   2021-10-01
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final IdentificationNumberValidator identificationNumberValidator;
    private final PhoneNumberValidator phoneNumberValidator;

    /**
     * Creates new customer to database.
     *
     * @param request the dto object required to create a new customer
     * @return the saved customer as {@link Optional<Customer>}
     */
    @Transactional
    @Override
    public Optional<Customer> create(CustomerDtoInput request) {

        // Is the identification number valid ?
        boolean isValidIdentificationNumber = identificationNumberValidator.
                test(request.getIdentificationNumber());

        if (!isValidIdentificationNumber) {
            throw new IdentificationNumberNotValidException(AppErrorMessages.IDENTIFICATION_NUMBER_NOT_VALID);
        }

        LOGGER.info("Validate identification number {}", request.getIdentificationNumber());

        // Is the customer already registered ?
        Optional<Customer> customerOptional = customerRepository.
                findByIdentificationNumber(request.getIdentificationNumber());

        if (customerOptional.isPresent()) {
            throw new CustomerIsAlreadyExistException(CUSTOMER_FOUND);
        }

        // Is the phone number valid ?
        boolean isValidPhoneNumber = phoneNumberValidator.test(request.getPhoneNumber());
        if(!isValidPhoneNumber){
            throw new PhoneNumberNotValidException(PHONE_NUMBER_NOT_VALID);
        }

        LOGGER.info("Validate phone number {}", request.getPhoneNumber());

        Customer customer = customerMapper.map(request);

        Customer savedCustomer = customerRepository.save(customer);

        LOGGER.info("Save customer {}", savedCustomer);

        return Optional.of(savedCustomer);
    }

    /**
     * Gets all customers.
     *
     * @return a {@link List<CustomerDtoOutput>}
     */
    @Transactional(readOnly = true)
    @Override
    public List<CustomerDtoOutput> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets the customer by id
     *
     * @param id the id of the customer
     * @return a {@link CustomerDtoOutput}
     */
    @Transactional(readOnly = true)
    @Override
    public CustomerDtoOutput getById(Long id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));

        return customerMapper.mapToDto(customer);
    }

    /**
     * Updates the customer.
     *
     * @param request the dto object required to update current customer.
     * @return the updated customer as {@link Optional<Customer>}
     */
    @Transactional
    @Override
    public Optional<Customer> update(CustomerDtoInput request) {

        // Is the identification number valid ?
        boolean isValidIdentificationNumber = identificationNumberValidator.
                test(request.getIdentificationNumber());

        if (!isValidIdentificationNumber) {
            throw new IdentificationNumberNotValidException(AppErrorMessages.IDENTIFICATION_NUMBER_NOT_VALID);
        }

        LOGGER.info("Validate identification number {}", request.getIdentificationNumber());

        // Is the customer found ?
        Optional<Customer> customerOptional = customerRepository.
                findByIdentificationNumber(request.getIdentificationNumber());

        if (!customerOptional.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }

        // Is the phone number valid ?
        boolean isValidPhoneNumber = phoneNumberValidator.test(request.getPhoneNumber());
        if(!isValidPhoneNumber){
            throw new PhoneNumberNotValidException(PHONE_NUMBER_NOT_VALID);
        }

        LOGGER.info("Validate phone number {}", request.getPhoneNumber());

        Customer selectedCustomer = customerMapper.map(request);
        selectedCustomer.setId(customerOptional.get().getId());

        Customer updatedCustomer = customerRepository.save(selectedCustomer);

        LOGGER.info("Update customer {}", updatedCustomer);

        return Optional.of(updatedCustomer);
    }

    /**
     * Deletes the customer by id.
     *
     * @param id the id of the customer required to delete customer
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        // Is the customer found ?
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (!customerOptional.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }

        customerRepository.delete(customerOptional.get());

        LOGGER.info("Delete this customer id {}", id);
    }
}

