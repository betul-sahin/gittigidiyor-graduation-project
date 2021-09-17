package com.loanapplicationsystem.backend.controllers;

import com.loanapplicationsystem.backend.dtos.LoanDtoInput;
import com.loanapplicationsystem.backend.dtos.LoanDtoOutput;
import com.loanapplicationsystem.backend.models.Loan;
import com.loanapplicationsystem.backend.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private LoanService loanService;

    public ResponseEntity<Loan> create(LoanDtoInput request){
        Optional<Loan> loanOptional = loanService.create(request);

        if(!loanOptional.isPresent()){
            return new ResponseEntity<Loan>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Loan>(
                loanOptional.get(),
                HttpStatus.OK);
    }
}
