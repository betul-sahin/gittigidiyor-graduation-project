package com.loanapplicationsystem.backend.dtos;

import com.loanapplicationsystem.backend.models.Customer;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class LoanDtoInput {
    private long id;

    @NotEmpty
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double creditAmount;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int creditLimitMultiplier;

    @NotEmpty
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private long customerId;
}
