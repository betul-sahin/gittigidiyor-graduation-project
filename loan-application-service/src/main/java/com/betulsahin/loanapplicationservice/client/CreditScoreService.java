package com.betulsahin.loanapplicationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "credit-score-service")
public interface CreditScoreService {

    @GetMapping("/{identificationNumber}")
    int getCreditScoreByIdentificationNumber(@PathVariable String identificationNumber);
}
