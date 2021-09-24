package com.loanapplicationsystem.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loanapplicationsystem.backend.models.abstractions.AbstractBaseEntity;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Loan extends AbstractBaseEntity {
    private double creditAmount;
    private int creditLimitMultiplier = 4;
    @Enumerated(EnumType.STRING)
    private CreditResult creditResult;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}