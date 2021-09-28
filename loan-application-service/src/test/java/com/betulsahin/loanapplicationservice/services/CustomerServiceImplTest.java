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
import com.betulsahin.loanapplicationservice.services.validators.IdentificationNumberValidator;
import com.betulsahin.loanapplicationservice.services.validators.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository mockCustomerRepository;

    @Mock
    CustomerMapper mockCustomerMapper;

    @Mock
    IdentificationNumberValidator mockIdentificationNumberValidator;

    @Mock
    PhoneNumberValidator mockPhoneNumberValidator;

    @InjectMocks
    CustomerServiceImpl underTest;

    @Test
    void create_itShoulCreateNewCustomer(){
        // given
        Customer expected = new Customer();
        expected.setIdentificationNumber("12345678900");
        expected.setPhoneNumber("5554443322");

        CustomerDtoInput request = new CustomerDtoInput();

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(true);

        // no customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.empty());

        // valid phone number
        when(mockPhoneNumberValidator.test(any())).thenReturn(true);

        // mocking save method
        when(mockCustomerRepository.save(any())).thenReturn(expected);

        // when
        Customer actual = underTest.create(request).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getIdentificationNumber(), actual.getIdentificationNumber()),
                () -> assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber())
        );
    }

    @Test
    void create_itShoulThrowException_whenIdentificationNumberNotValid(){
        // given
        CustomerDtoInput request = new CustomerDtoInput();

        // NOT valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(false);

        // when
        Executable executable = () -> underTest.create(request).get();

        // then
        assertThrows(IdentificationNumberNotValidException.class, executable);
    }

    @Test
    void create_itShouldThrowException_whenCustomerIsAlreadyExist(){
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        customer.setPhoneNumber("5554443322");

        CustomerDtoInput request = new CustomerDtoInput();

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(true);

        // no customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.of(customer));

        // when
        Executable executable = () -> underTest.create(request).get();

        // then
        assertThrows(CustomerIsAlreadyExistException.class, executable);
    }

    @Test
    void create_itShouldThrowException_whenPhoneNumberNotValid(){
        // given
        CustomerDtoInput request = new CustomerDtoInput();

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(true);

        // no customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.empty());

        // NOT valid phone number
        when(mockPhoneNumberValidator.test(any())).thenReturn(false);

        // when
        Executable executable = () -> underTest.create(request);

        // then
        assertThrows(PhoneNumberNotValidException.class, executable);
    }

    @Test
    void getAll_itShouldReturnCustomerDtoList(){
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        customer.setPhoneNumber("5554443322");
        List<Customer> customerList = Collections.singletonList(customer);

        CustomerDtoOutput response = new CustomerDtoOutput();
        response.setIdentificationNumber(customer.getIdentificationNumber());
        response.setPhoneNumber(customer.getPhoneNumber());

        // mocking mapper
        when(mockCustomerMapper.mapToDto(any())).thenReturn(response);

        // mocking findAll method
        when(mockCustomerRepository.findAll()).thenReturn(customerList);

        // when
        List<CustomerDtoOutput> actual = underTest.getAll();

        // then
        assertEquals(customer.getIdentificationNumber(), actual.get(0).getIdentificationNumber());
        assertEquals(customer.getPhoneNumber(), actual.get(0).getPhoneNumber());
    }

    @Test
    void getById_itShouldReturnCustomerDto(){
        // given
        Customer customer = new Customer();
       // customer.setIdentificationNumber("12345678900");
        //customer.setPhoneNumber("5554443322");

        CustomerDtoOutput response = new CustomerDtoOutput();
        //response.setIdentificationNumber(customer.getIdentificationNumber());
        //response.setPhoneNumber(customer.getPhoneNumber());

        when(mockCustomerMapper.mapToDto(any())).thenReturn(response);

        when(mockCustomerRepository.findById(anyLong())).
                thenReturn(Optional.of(customer));

        // when
        CustomerDtoOutput actual = underTest.getById(1L);

        // then
        assertEquals(response, actual);
    }

    @Test
    void getById_itShouldThrowNotFound_whenCustomerIdNotFound(){
        // given
        when(mockCustomerRepository.findById(anyLong())).
                thenReturn(Optional.empty());

        // when
        Executable executable = () -> underTest.getById(1L);

        // then
        assertThrows(CustomerNotFoundException.class, executable);
    }

    @Test
    void update_itShoulUpdateCurrentCustomer(){
        // given
        Customer expected = new Customer();
        expected.setId(1L);
        expected.setIdentificationNumber("12345678900");
        expected.setPhoneNumber("5554443322");

        CustomerDtoInput request = new CustomerDtoInput();

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(true);

        // the customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.of(expected));

        // valid phone number
        when(mockPhoneNumberValidator.test(any())).thenReturn(true);

        // mocking mapper
        when(mockCustomerMapper.map(any())).thenReturn(expected);

        // mocking save method
        when(mockCustomerRepository.save(any())).thenReturn(expected);

        // when
        Customer actual = underTest.update(request).get();

        // then
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getIdentificationNumber(), actual.getIdentificationNumber()),
                () -> assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber())
        );
    }

    @Test
    void update_itShoulThrowException_whenIdentificationNumberNotValid(){
        // given
        CustomerDtoInput request = new CustomerDtoInput();

        // NOT valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(false);

        // when
        Executable executable = () -> underTest.update(request).get();

        // then
        assertThrows(IdentificationNumberNotValidException.class, executable);
    }

    @Test
    void update_itShouldThrowException_whenCustomerNotFound(){
        // given
        CustomerDtoInput request = new CustomerDtoInput();

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(true);

        // NOT FOUND customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.empty());

        // when
        Executable executable = () -> underTest.update(request).get();

        // then
        assertThrows(CustomerNotFoundException.class, executable);
    }

    @Test
    void update_itShouldThrowException_whenPhoneNumberNotValid(){
        // given
        Customer expected = new Customer();
        CustomerDtoInput request = new CustomerDtoInput();

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).
                thenReturn(true);

        // the customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.of(expected));

        // NOT valid phone number
        when(mockPhoneNumberValidator.test(any())).thenReturn(false);

        // when
        Executable executable = () -> underTest.update(request).get();

        // then
        assertThrows(PhoneNumberNotValidException.class, executable);
    }

    @Test
    void deleteById_itShouldReturnStatusNotFound_whenCustomerIdNotExist(){
        // given
        when(mockCustomerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Executable executable = () -> underTest.deleteById(1L);

        // then
        assertThrows(CustomerNotFoundException.class, executable);
    }
}