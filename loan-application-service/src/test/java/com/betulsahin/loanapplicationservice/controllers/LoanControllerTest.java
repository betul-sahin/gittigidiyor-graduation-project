package com.betulsahin.loanapplicationservice.controllers;

import com.betulsahin.loanapplicationservice.client.CreditScoreService;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.dtos.response.LoanResponse;
import com.betulsahin.loanapplicationservice.exceptions.LoanNotFoundException;
import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.services.abstractions.LoanService;
import com.betulsahin.loanapplicationservice.services.abstractions.LoanTransactionLoggerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @Mock
    LoanService mockLoanService;

    @Mock
    CreditScoreService mockCreditScoreService;

    @Mock
    LoanTransactionLoggerService mockLoanTransactionLoggerService;

    @InjectMocks
    LoanController underTest;

    @Test
    void create_itShouldCreateSuccessfully(){
        // given
        Loan loan = new Loan();
        int score = 500;

        LoanDtoInput request = new LoanDtoInput();
        LoanResponse loanResponse = new LoanResponse();

        when(mockCreditScoreService.getCreditScoreByIdentificationNumber(any())).
                thenReturn(score);

        when(mockLoanService.create(any(), anyInt())).thenReturn(Optional.of(loan));

        doNothing().when(mockLoanTransactionLoggerService).saveLoanTransaction(any());

        // when
        ResponseEntity<LoanResponse> response = underTest.create(request);
        LoanResponse actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode())
        );
    }

    @Test
    void create_itShouldReturnStatusBadRequest(){
        // given
        int score = 500;

        LoanDtoInput request = new LoanDtoInput();

        when(mockCreditScoreService.getCreditScoreByIdentificationNumber(any())).
                thenReturn(score);

        when(mockLoanService.create(any(), anyInt())).thenReturn(Optional.empty());

        // when
        ResponseEntity<LoanResponse> response = underTest.create(request);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAll_itShouldReturnLoanDtoList(){
        // given
        LoanDtoOutput loanDtoOutput = new LoanDtoOutput();
        List<LoanDtoOutput> expected = Arrays.asList(loanDtoOutput);

        when(mockLoanService.getAll()).thenReturn(expected);

        // when
        ResponseEntity<List<LoanDtoOutput>> response = underTest.getAll();
        List<LoanDtoOutput> actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.size(), actual.size())
        );
    }

    @Test
    void getById_itShouldReturnLoanDto(){
        // given
        LoanDtoOutput expected = new LoanDtoOutput();
        expected.setId(1L);

        when(mockLoanService.getById(anyLong())).thenReturn(expected);

        // when
        ResponseEntity<LoanDtoOutput> response = underTest.getById(1L);
        LoanDtoOutput actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.getId(), actual.getId())
        );
    }

    @Test
    void getById_itShouldReturnStatusNotFound_whenLoanIdNotExist(){
        // given
        when(mockLoanService.getById(anyLong())).thenReturn(null);

        // when
        ResponseEntity<LoanDtoOutput> response = underTest.getById(1L);
        LoanDtoOutput actual = response.getBody();

        // then
        assertNull(actual);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
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
    void deleteById_itShouldReturnStatusNotFound_whenLoanIdNotExist(){
        // given
        doThrow(LoanNotFoundException.class).
                when(mockLoanService).deleteById(anyLong());

        // when
        ResponseEntity<Void> response = underTest.deleteById(1L);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}