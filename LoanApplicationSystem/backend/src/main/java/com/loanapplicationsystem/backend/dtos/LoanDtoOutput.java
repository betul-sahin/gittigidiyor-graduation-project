package com.loanapplicationsystem.backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanDtoOutput {
    private long id;
    private double creditAmount;
    private int creditLimitMultiplier;
    private String creditResult;
    private int customerId;
}
