package com.betulsahin.loanapplicationservice.controllers;

import com.betulsahin.loanapplicationservice.dtos.CustomerDtoInput;
import com.betulsahin.loanapplicationservice.dtos.CustomerDtoOutput;
import com.betulsahin.loanapplicationservice.exceptions.CustomerIsAlreadyExistException;
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
    void itShouldCreateSuccessfully(){
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
    void itShouldReturnStatusBadRequest_whenCustomerIsNotCreated(){
        // given
        when(mockCustomerService.create(any())).thenReturn(Optional.empty());

        // when
        CustomerDtoInput request = new CustomerDtoInput();
        ResponseEntity<Customer> response = underTest.create(request);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void itShouldReturnCustomerDtoList(){
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
    void itShouldReturnCustomerDto_whenCustomerIdExist(){
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
    void testGetById_itShouldReturnStatusNotFound_whenCustomerIdNotExist(){
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
    void itShouldUpdateSuccessfully(){
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        customer.setFirstName("Ahmet");
        customer.setLastName("Yılmaz");
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
    void itShouldReturnStatusBadRequest_whenCustomerIsNotUpdated(){
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
    void itShouldDeleteSuccesfully(){
        // given
        // when
        ResponseEntity<Void> response = underTest.deleteById(1L);

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteById_itShouldReturnStatusNotFound_whenCustomerIdNotExist(){
        // given
        Mockito.doThrow(CustomerIsAlreadyExistException.class).
                when(mockCustomerService).deleteById(anyLong());

        // when
        ResponseEntity<Void> response = underTest.deleteById(1L);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}