package com.betulsahin.creditscoreservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class CreditScore {
    @Id
    private String id;
    private Integer lastDigit;
    private int score;

    public CreditScore(Integer lastDigit, int score) {
        this.lastDigit = lastDigit;
        this.score = score;
    }
}
