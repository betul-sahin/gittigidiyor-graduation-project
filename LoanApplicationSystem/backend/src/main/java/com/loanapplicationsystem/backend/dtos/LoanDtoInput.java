package com.loanapplicationsystem.backend.dtos;

import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
public class LoanDtoInput {
    private long id;
    private double creditAmount;
    private int creditLimitMultiplier;
    private int customerId;
}
