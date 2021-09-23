package com.loanapplicationsystem.backend.models;

import com.loanapplicationsystem.backend.models.abstractions.AbstractBaseEntity;
import com.loanapplicationsystem.backend.models.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
