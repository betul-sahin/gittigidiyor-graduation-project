package com.betulsahin.loanapplicationservice.models;

import com.betulsahin.loanapplicationservice.models.abstractions.AbstractBaseEntity;
import com.betulsahin.loanapplicationservice.models.enums.CreditResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @ManyToOne(fetch = FetchType.LAZY) //cascade = { CascadeType.MERGE, CascadeType.REMOVE}
    private Customer customer;
}

// TODO frontend
// TODO : anotasyonlarınhatalrının yakalanamsı
// TODO : aws
