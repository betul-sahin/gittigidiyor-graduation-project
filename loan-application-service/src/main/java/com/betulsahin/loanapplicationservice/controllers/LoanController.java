package com.betulsahin.loanapplicationservice.controllers;

import com.betulsahin.loanapplicationservice.dtos.LoanDtoInput;
import com.betulsahin.loanapplicationservice.dtos.LoanDtoOutput;
import com.betulsahin.loanapplicationservice.dtos.response.LoanResponse;
import com.betulsahin.loanapplicationservice.exceptions.LoanNotFoundException;
import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.models.LoanTransactionLogger;
import com.betulsahin.loanapplicationservice.services.abstractions.LoanService;
import com.betulsahin.loanapplicationservice.client.CreditScoreService;
import com.betulsahin.loanapplicationservice.services.abstractions.LoanTransactionLoggerService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
@CrossOrigin
public class LoanController {
    private final LoanService loanService;
    private final LoanTransactionLoggerService loanTransactionLoggerService;

    @PostMapping
    public ResponseEntity<LoanResponse> create(@Valid @RequestBody LoanDtoInput request) {

        Optional<Loan> loanOptional = loanService.create(request);

        if (!loanOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // save transactions of loan
        loanTransactionLoggerService.saveLoanTransaction(loanOptional.get());

        LoanResponse response = new LoanResponse();
        response.setCreditResult(loanOptional.get().getCreditResult());
        response.setCreditAmount(loanOptional.get().getCreditAmount());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanDtoOutput>> getAll() {
        final List<LoanDtoOutput> loanDtoOutputList = loanService.getAll();

        return new ResponseEntity<>(loanDtoOutputList, HttpStatus.OK);
    }

    @GetMapping("/get-loan-transactions-by-date")
    public ResponseEntity<Page<List<LoanTransactionLogger>>> getAllTransactionsWithDate(
            @ApiParam(value = "Transaction query for loan applications by date", example = "19/09/2021")
            @RequestParam String transactionDate,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        return new ResponseEntity<>(
                loanTransactionLoggerService.getAllTransactionsWithDate(transactionDate, pageNumber, pageSize, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/get-loan-transactions-by-customer-id")
    public ResponseEntity<Page<List<LoanTransactionLogger>>> getAllTransactionsWithCustomerId(
            @ApiParam(value = "Transaction query for loan applications by customer id", example = "111")
            @RequestParam Long customerId,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        return new ResponseEntity<>(
                loanTransactionLoggerService.getAllByCustomerId(customerId, pageNumber, pageSize, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/get-loan-transactions-by-credit-result")
    public ResponseEntity<Page<List<LoanTransactionLogger>>> getAllTransactionsWithCreditResult(
            @ApiParam(value = "Transaction query for loan applications by credit result", example = "APPROVAL")
            @RequestParam String creditResult,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10) Pageable pageable){

        return new ResponseEntity<>(
                loanTransactionLoggerService.getAllByCreditResult(creditResult, pageNumber, pageSize, pageable),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LoanDtoOutput> getById(@PathVariable long id) {
        final LoanDtoOutput loanDtoOutput = loanService.getById(id);
        if(loanDtoOutput == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(loanDtoOutput, HttpStatus.OK);
    }

    @GetMapping("/identification-number/{identificationNumber}")
    public ResponseEntity<LoanDtoOutput> getByIdentificationNumber(String identificationNumber){
        final LoanDtoOutput loanDtoOutput = loanService.getByIdentificationNumber(identificationNumber);
        if(loanDtoOutput == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(loanDtoOutput, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try{
            loanService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(LoanNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
