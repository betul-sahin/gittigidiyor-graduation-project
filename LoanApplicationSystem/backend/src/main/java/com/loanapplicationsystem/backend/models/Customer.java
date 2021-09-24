package com.loanapplicationsystem.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loanapplicationsystem.backend.models.abstractions.Person;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Customer extends Person {
    private String identificationNumber;
    private double monthlyIncome;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Loan> loans = new HashSet<>();
}
