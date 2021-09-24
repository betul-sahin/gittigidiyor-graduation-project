package com.loanapplicationsystem.backend.controllers;

import com.loanapplicationsystem.backend.dtos.CustomerDtoInput;
import com.loanapplicationsystem.backend.dtos.CustomerDtoOutput;
import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.services.CustomerServiceImpl;
import com.loanapplicationsystem.backend.services.abstractions.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody CustomerDtoInput request){

        Optional<Customer> customerOptional = customerService.create(request);

        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerOptional.get(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDtoOutput>> getAll(){
        final List<CustomerDtoOutput> customerDtoOutputList = customerService.findAll();

        return new ResponseEntity<>(customerDtoOutputList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDtoOutput> getById(@PathVariable long id){
        final CustomerDtoOutput customerDtoOutput = customerService.findById(id);

        return new ResponseEntity<>(customerDtoOutput, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@Valid@RequestBody CustomerDtoInput request){
        Optional<Customer> customerOptional = customerService.update(request);

        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
