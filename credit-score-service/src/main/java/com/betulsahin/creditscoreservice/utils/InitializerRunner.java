package com.betulsahin.creditscoreservice.utils;

import com.betulsahin.creditscoreservice.models.CreditScore;
import com.betulsahin.creditscoreservice.repositories.CreditScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitializerRunner implements CommandLineRunner {

    @Autowired
    private CreditScoreRepository creditScoreRepository;

    @Override
    public void run(String... args) throws Exception {

        creditScoreRepository.deleteAll();

        List<CreditScore> creditScoreList = Arrays.asList(
                new CreditScore(2, 550),
                new CreditScore(4, 1000),
                new CreditScore(6, 400),
                new CreditScore(8, 900),
                new CreditScore(0, 2000));

        creditScoreRepository.saveAll(creditScoreList);
    }
}
