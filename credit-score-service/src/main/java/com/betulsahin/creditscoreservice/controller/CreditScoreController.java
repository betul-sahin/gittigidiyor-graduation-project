package com.betulsahin.creditscoreservice.controller;

import com.betulsahin.creditscoreservice.dtos.CreditScoreResponse;
import com.betulsahin.creditscoreservice.services.CreditScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credit-scores")
@RequiredArgsConstructor
public class CreditScoreController {
    private final CreditScoreService creditScoreService;

    @GetMapping("/{identificationNumber}")
    public int getCreditScoreByIdentificationNumber(@PathVariable String identificationNumber){
        int score = creditScoreService.getCreditScoreByIdentificationNumber(identificationNumber);

        return score;
    }
}
