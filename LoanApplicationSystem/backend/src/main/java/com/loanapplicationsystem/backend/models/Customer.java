package com.loanapplicationsystem.backend.models;

import com.loanapplicationsystem.backend.models.abstractions.AbstractBaseEntity;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@Entity
public class Customer extends AbstractBaseEntity {
    private String identificationNumber;
    private String firstName;
    private String LastName;
    private double monthlyIncome;
    private int phoneNumber;
    @Enumerated(EnumType.STRING)
    private CreditResult creditResult;
}
