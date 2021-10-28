package com.betulsahin.loanapplicationservice.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDtoOutput {
    private Long id;
    private double creditAmount;
    private int creditLimitMultiplier;
    private String creditResult;
    private String identificationNumber;
}
