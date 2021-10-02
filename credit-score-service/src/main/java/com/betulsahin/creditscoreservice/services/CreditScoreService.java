package com.betulsahin.creditscoreservice.services;

import com.betulsahin.creditscoreservice.dtos.CreditScoreResponse;
import com.betulsahin.creditscoreservice.exceptions.CreditScoreNotFoundException;
import com.betulsahin.creditscoreservice.exceptions.IdentificationNumberNotValidException;
import com.betulsahin.creditscoreservice.models.CreditScore;
import com.betulsahin.creditscoreservice.repositories.CreditScoreRepository;
import com.betulsahin.creditscoreservice.utils.AppErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * The CreditScoreService class that simply get credit score of customer.
 *
 * @author  Betül Şahin
 * @version 1.0
 * @since   2021-10-01
 */
@Service
@RequiredArgsConstructor
public class CreditScoreService {
    private static final int IDENTIFICATION_NUMBER_LAST_DIGIT_INDEX = 10;

    private final CreditScoreRepository creditScoreRepository;
    private final IdentificationNumberValidator identificationNumberValidator;

    /**
     * Gets credit score point of customer.
     *
     * @param identificationNumber of the customer
     * @return credit score point of the customer
     */
    @Transactional(readOnly = true)
    public int getCreditScoreByIdentificationNumber(String identificationNumber) {

        // Is the identification number valid ?
        boolean isValidIdentificationNumber = identificationNumberValidator.test(identificationNumber);
        if(!isValidIdentificationNumber){
            throw new IdentificationNumberNotValidException(AppErrorMessages.IDENTIFICATION_NUMBER_NOT_VALID);
        }

        // Is there a credit score for last digit of the identification number ?
        int lastDigitOfIdentificationNumber = Integer.valueOf(identificationNumber.substring(IDENTIFICATION_NUMBER_LAST_DIGIT_INDEX));
        Optional<CreditScore> creditScoreOptional = creditScoreRepository.findByLastDigit(lastDigitOfIdentificationNumber);
        if(!creditScoreOptional.isPresent()){
            throw new CreditScoreNotFoundException(AppErrorMessages.CREDIT_SCORE_NOT_FOUND);
        }

        int score = this.generateCreditScore(creditScoreOptional.get(), lastDigitOfIdentificationNumber);

        return score;
    }

    /**
     * Generates credit score point of customer using with last digit of identification number.
     *
     * @param creditScore the credit score point
     * @param lastDigitOfIdentificationNumber the last digit of identification number
     * @return credit score point of the customer
     */
    private int generateCreditScore(CreditScore creditScore, int lastDigitOfIdentificationNumber){
        int score = 0;
        if(creditScore.getLastDigit().equals(lastDigitOfIdentificationNumber)){
            score = creditScore.getScore();
        }

        return score;
    }
}
