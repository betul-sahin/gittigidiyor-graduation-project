package com.betulsahin.loanapplicationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDtoOutput {
    private Long id;
    private double creditAmount;
    private int creditLimitMultiplier;
    private String creditResult;
    private Long customerId;
}
