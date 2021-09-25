package com.betulsahin.creditscoreservice.repositories;

import com.betulsahin.creditscoreservice.models.CreditScore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CreditScoreRepository extends MongoRepository<CreditScore, String> {
    Optional<CreditScore> findByLastDigit(int lastDigit);
}
