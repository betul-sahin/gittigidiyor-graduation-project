package com.loanapplicationsystem.backend.controllers;

import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.dtos.LoanDtoOutput;
import com.loanapplicationsystem.backend.models.Loan;
import com.loanapplicationsystem.backend.models.LoanTransactionLogger;
import com.loanapplicationsystem.backend.services.LoanService;
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

    @PostMapping
    public ResponseEntity<Loan> create(@Valid @RequestBody LoanDtoInput request) {
        Optional<Loan> loanOptional = loanService.create(request);

        if (!loanOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                loanOptional.get(),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LoanDtoOutput>> getAll() {
        final List<LoanDtoOutput> loanDtoOutputList = loanService.findAll();

        return new ResponseEntity<>(
                loanDtoOutputList,
                HttpStatus.OK);
    }

    @GetMapping("/get-loan-transactions-by-date")
    public ResponseEntity<Page<List<LoanTransactionLogger>>> getAllTransactionsWithDate(
            @ApiParam(value = "Transaction query for loan applications by date", example = "19/09/2021")
            @RequestParam String transactionDate,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        return new ResponseEntity<>(
                loanService.getAllTransactionsWithDate(transactionDate, pageNumber, pageSize, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/get-loan-transactions-by-customer-id")
    public ResponseEntity<Page<List<LoanTransactionLogger>>> getAllTransactionsWithCustomerId(
            @ApiParam(value = "Transaction query for loan applications by customer id", example = "111L")
            @RequestParam long customerId,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        return new ResponseEntity<>(
                loanService.getAllByCustomerId(customerId, pageNumber, pageSize, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/get-loan-transactions-by-credit-result")
    public ResponseEntity<Page<List<LoanTransactionLogger>>> getAllTransactionsWithCreditResult(
            @ApiParam(value = "Transaction query for loan applications by credit result", example = "111L")
            @RequestParam String creditResult,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10) Pageable pageable){

        return new ResponseEntity<>(
                loanService.getAllByCreditResult(creditResult, pageNumber, pageSize, pageable),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LoanDtoOutput> getById(@PathVariable long id) {
        final LoanDtoOutput loanDtoOutput = loanService.findById(id);

        return new ResponseEntity<>(
                loanDtoOutput,
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Loan> update(@Valid @RequestBody LoanDtoInput request) {
        Optional<Loan> loanOptional = loanService.update(request);

        if (!loanOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                loanOptional.get(),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

