package com.betulsahin.loanapplicationservice.models;

import com.betulsahin.loanapplicationservice.models.abstractions.AbstractBaseEntity;
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

// TODO servis testleri
// TODO controller testleri
// TODO repository testleri
