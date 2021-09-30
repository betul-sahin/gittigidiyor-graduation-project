package com.betulsahin.loanapplicationservice.dtos.response;

import com.betulsahin.loanapplicationservice.models.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    private double creditAmount;
    @Enumerated(EnumType.STRING)
    private CreditResult creditResult;
}
