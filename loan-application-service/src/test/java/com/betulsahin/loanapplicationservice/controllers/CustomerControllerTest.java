package com.betulsahin.loanapplicationservice.controllers;

import com.betulsahin.loanapplicationservice.dtos.CustomerDtoInput;
import com.betulsahin.loanapplicationservice.dtos.CustomerDtoOutput;
import com.betulsahin.loanapplicationservice.exceptions.CustomerIsAlreadyExistException;
import com.betulsahin.loanapplicationservice.exceptions.CustomerNotFoundException;
import com.betulsahin.loanapplicationservice.models.Customer;
import com.betulsahin.loanapplicationservice.services.abstractions.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService mockCustomerService;

    @InjectMocks
    CustomerController underTest;

    @Test
    void create_itShouldCreateSuccessfully(){
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        Optional<Customer> expected = Optional.of(customer);

        when(mockCustomerService.create(any())).thenReturn(expected);

        // when
        CustomerDtoInput request = new CustomerDtoInput();
        ResponseEntity<Customer> response = underTest.create(request);
        Customer actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(customer.getIdentificationNumber(), actual.getIdentificationNumber())
        );
    }

    @Test
    void create_itShouldReturnStatusBadRequest(){
        // given
        when(mockCustomerService.create(any())).thenReturn(Optional.empty());

        // when
        CustomerDtoInput request = new CustomerDtoInput();
        ResponseEntity<Customer> response = underTest.create(request);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAll_itShouldReturnCustomerDtoList(){
        // given
        CustomerDtoOutput customerDtoOutput = new CustomerDtoOutput();
        List<CustomerDtoOutput> expected = Arrays.asList(customerDtoOutput);

        when(mockCustomerService.getAll()).thenReturn(expected);

        // when
        ResponseEntity<List<CustomerDtoOutput>> response = underTest.getAll();
        List<CustomerDtoOutput> actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.size(), actual.size())
        );
    }

    @Test
    void getById_itShouldReturnCustomerDto(){
        // given
        CustomerDtoOutput expected = new CustomerDtoOutput();
        expected.setId(1L);

        when(mockCustomerService.getById(any())).thenReturn(expected);

        // when
        ResponseEntity<CustomerDtoOutput> response = underTest.getById(1);
        CustomerDtoOutput actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.getId(), actual.getId())
        );
    }

    @Test
    void getById_itShouldReturnStatusNotFound_whenCustomerIdNotExist(){
        // given
        when(mockCustomerService.getById(anyLong())).thenReturn(null);

        // when
        ResponseEntity<CustomerDtoOutput> response = underTest.getById(1L);
        CustomerDtoOutput actual = response.getBody();

        // then
        assertNull(actual);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void update_itShouldUpdateSuccessfully(){
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        customer.setFirstName("Ahmet");
        customer.setLastName("Y??lmaz");
        Optional<Customer> expected = Optional.of(customer);

        when(mockCustomerService.update(any())).thenReturn(expected);

        // when
        CustomerDtoInput request = new CustomerDtoInput();
        ResponseEntity<Customer> response = underTest.update(request);
        Customer actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(customer.getFirstName(), actual.getFirstName()),
                () -> assertEquals(customer.getLastName(), actual.getLastName())
        );
    }

    @Test
    void update_itShouldReturnStatusBadRequest(){
        // given
        when(mockCustomerService.update(any())).thenReturn(Optional.empty());

        // when
        CustomerDtoInput request = new CustomerDtoInput();
        ResponseEntity<Customer> response = underTest.update(request);
        Customer actual = response.getBody();

        // then
        assertNull(actual);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteById_itShouldDeleteSuccesfully(){
        // given
        // when
        ResponseEntity<Void> response = underTest.deleteById(1L);

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_itShouldReturnStatusNotFound_whenCustomerIdNotExist(){
        // given
        Mockito.doThrow(CustomerNotFoundException.class).
                when(mockCustomerService).deleteById(anyLong());

        // when
        ResponseEntity<Void> response = underTest.deleteById(1L);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}