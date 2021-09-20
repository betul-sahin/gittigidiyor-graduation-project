package com.loanapplicationsystem.backend.controllers;

import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.dtos.LoanDtoOutput;
import com.loanapplicationsystem.backend.models.Loan;
import com.loanapplicationsystem.backend.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> create(@RequestBody LoanDtoInput request){
        Optional<Loan> loanOptional = loanService.create(request);

        if(!loanOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                loanOptional.get(),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LoanDtoOutput>> getAll(){
        final List<LoanDtoOutput> loanDtoOutputList = loanService.findAll();

        return new ResponseEntity<>(
                loanDtoOutputList,
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LoanDtoOutput> getById(@PathVariable long id){
        final LoanDtoOutput loanDtoOutput = loanService.findById(id);

        return new ResponseEntity<>(
                loanDtoOutput,
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Loan> update(@RequestBody LoanDtoInput request){
        Optional<Loan> loanOptional = loanService.update(request);

        if(!loanOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                loanOptional.get(),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable long id){
        loanService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

