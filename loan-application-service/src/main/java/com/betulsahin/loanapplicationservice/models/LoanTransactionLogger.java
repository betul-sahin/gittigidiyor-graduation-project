package com.betulsahin.loanapplicationservice.models;

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
