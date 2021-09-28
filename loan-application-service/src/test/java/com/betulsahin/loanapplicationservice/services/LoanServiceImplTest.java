package com.betulsahin.loanapplicationservice.services;

import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.exceptions.CustomerNotFoundException;
import com.betulsahin.loanapplicationservice.exceptions.IdentificationNumberNotValidException;
import com.betulsahin.loanapplicationservice.exceptions.LoanNotFoundException;
import com.betulsahin.loanapplicationservice.mappers.LoanMapper;
import com.betulsahin.loanapplicationservice.models.Customer;
import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.repositories.CustomerRepository;
import com.betulsahin.loanapplicationservice.repositories.LoanRepository;
import com.betulsahin.loanapplicationservice.services.abstractions.SmsSender;
import com.betulsahin.loanapplicationservice.services.validators.IdentificationNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceImplTest {
    @Mock
    LoanRepository mockLoanRepository;

    @Mock
    CustomerRepository mockCustomerRepository;

    @Mock
    IdentificationNumberValidator mockIdentificationNumberValidator;

    @Mock
    LoanMapper mockLoanMapper;

    @Mock
    SmsSender mockSmsSender;

    @InjectMocks
    LoanServiceImpl underTest;

    @Test
    void create_itShouldCreateNewLoan(){
        // given
        Loan expected = new Loan();
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");

        LoanDtoInput request = new LoanDtoInput();
        int score = 500;

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).thenReturn(true);

        // the customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.of(customer));

        // mocking save method
        when(mockLoanRepository.save(any())).thenReturn(expected);

        // when
        Loan actual = underTest.create(request, score).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void create_itShoulThrowException_whenIdentificationNumberNotValid(){
        // given
        LoanDtoInput request = new LoanDtoInput();
        int score = 500;

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).thenReturn(false);

        // when
        Executable executable = () -> underTest.create(request, score).get();

        // then
        assertThrows(IdentificationNumberNotValidException.class, executable);
    }

    @Test
    void create_itShouldThrowException_whenCustomerNotFound(){
        // given
        Loan expected = new Loan();

        LoanDtoInput request = new LoanDtoInput();
        int score = 500;

        // valid identification number
        when(mockIdentificationNumberValidator.test(any())).thenReturn(true);

        // the customer with this identificationNumber
        when(mockCustomerRepository.findByIdentificationNumber(any())).
                thenReturn(Optional.empty());

        // when
        Executable executable = () -> underTest.create(request, score).get();

        // then
        assertThrows(CustomerNotFoundException.class, executable);
    }

    @Test
    void getAll_itShouldReturnLoanDtoList(){
        // given
        Loan loan = new Loan();
        List<Loan> loanList = Arrays.asList(loan);

        LoanDtoOutput response = new LoanDtoOutput();

        // mocking mapper
        when(mockLoanMapper.mapToDto(any())).thenReturn(response);

        // mocking find all method
        when(mockLoanRepository.findAll()).thenReturn(loanList);

        // when
        List<LoanDtoOutput> actual = underTest.getAll();

        // then
        assertEquals(loanList.size(), actual.size());
    }

    @Test
    void getById_itShouldReturnLoanDto(){
        // given
        Loan loan = new Loan();
        loan.setId(1L);

        LoanDtoOutput response = new LoanDtoOutput();

        // mocking mapper
        when(mockLoanMapper.mapToDto(any())).thenReturn(response);

        // mocking find by id method
        when(mockLoanRepository.findById(anyLong())).
                thenReturn(Optional.of(loan));

        // when
        LoanDtoOutput actual = underTest.getById(1L);

        // then
        assertEquals(response, actual);
    }

    @Test
    void getById_itShouldThrowNotFound_whenLoanIdNotFound(){
        // given
        when(mockLoanRepository.findById(anyLong())).
                thenReturn(Optional.empty());

        // when
        Executable executable = () -> underTest.getById(1L);

        // then
        assertThrows(LoanNotFoundException.class, executable);
    }

    @Test
    void deleteById_itShouldReturnStatusNotFound_whenLoanIdNotExist(){
        // given
        when(mockLoanRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Executable executable = () -> underTest.deleteById(1L);

        // then
        assertThrows(LoanNotFoundException.class, executable);
    }
}
