package com.loanapplicationsystem.backend.controllers;

import com.loanapplicationsystem.backend.dtos.CustomerDtoInput;
import com.loanapplicationsystem.backend.dtos.CustomerDtoOutput;
import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private CustomerService customService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomerDtoInput request){

        Optional<Customer> customerOptional = customService.create(request);

        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerOptional.get(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDtoOutput>> getAll(){
        final List<CustomerDtoOutput> customerDtoOutputList = customService.findAll();

        return new ResponseEntity<>(customerDtoOutputList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDtoOutput> getById(@PathVariable long id){
        final CustomerDtoOutput customerDtoOutput = customService.findById(id);

        return new ResponseEntity<>(customerDtoOutput, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody CustomerDtoInput request){
        Optional<Customer> customerOptional = customService.update(request);

        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable long id){
        customService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
