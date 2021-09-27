package com.betulsahin.loanapplicationservice.models;

import com.betulsahin.loanapplicationservice.models.abstractions.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Customer extends AbstractBaseEntity {
    private String firstName;
    private String LastName;
    private String phoneNumber;
    private String identificationNumber;
    private double monthlyIncome;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();
}
