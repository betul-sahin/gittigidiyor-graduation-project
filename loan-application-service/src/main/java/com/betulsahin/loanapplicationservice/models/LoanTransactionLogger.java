package com.betulsahin.loanapplicationservice.models;

import com.betulsahin.loanapplicationservice.models.abstractions.AbstractBaseEntity;
import com.betulsahin.loanapplicationservice.models.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoanTransactionLogger extends AbstractBaseEntity {
    private Long customerId;
    private double creditAmount;
    @Enumerated(EnumType.STRING)
    private CreditResult creditResult;
    private LocalDate requestDate;
    private String clientIpAdress;
    private String clientUrl;
    private String sessionActivityId;
}
