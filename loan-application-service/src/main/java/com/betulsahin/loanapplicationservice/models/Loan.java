package com.betulsahin.loanapplicationservice.models;

import com.betulsahin.loanapplicationservice.models.abstractions.AbstractBaseEntity;
import com.betulsahin.loanapplicationservice.models.enums.CreditResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

// TODO score-service in pushlanmasi
// TODO frontend
// TODO +90.. regex
// TODO LoanValidator fix
// TODO update method of loan
// TODO update method of customer
// TODO swagger test -> hersey duzgun calisiyor mu
// TODO servis testleri
// TODO controller testleri
// TODO repository testleri
