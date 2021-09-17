package com.loanapplicationsystem.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoInput {
    private long id;
    private int identificationNumber;
    private String firstName;
    private String LastName;
    private double monthlyIncome;
    private int phoneNumber;
}
