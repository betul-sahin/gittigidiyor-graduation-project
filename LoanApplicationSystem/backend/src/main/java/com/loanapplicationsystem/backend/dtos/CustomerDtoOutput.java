package com.loanapplicationsystem.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoOutput {
    private long id;
    private String identificationNumber;
    private String firstName;
    private String LastName;
    private double monthlyIncome;
    private int phoneNumber;
    private String creditResult;
}
