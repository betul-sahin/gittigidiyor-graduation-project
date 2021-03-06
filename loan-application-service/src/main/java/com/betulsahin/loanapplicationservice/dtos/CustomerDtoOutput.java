package com.betulsahin.loanapplicationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoOutput {
    private Long id;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private double monthlyIncome;
    private String phoneNumber;
}
