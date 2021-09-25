package com.loanapplicationsystem.backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "credit-score-service", url = "http://localhost:8082/api/v1/credit-scores/")
public interface CreditScoreService {

    @GetMapping("/{identificationNumber}")
    int getCreditScoreByIdentificationNumber(@PathVariable String identificationNumber);
}
