package com.betulsahin.loanapplicationservice.services.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IdentificationNumberValidatorTest {

    private IdentificationNumberValidator underTest;

    @BeforeEach
    void setUp(){
        underTest = new IdentificationNumberValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "12345678978, true",
            "12345678975, false",
            "12345678909001, false",
            "123456, false"
    })
    void test_itShouldValidateIdentificatiınNumber(String identificationNumber, boolean expected){
        // given
        // when
        boolean actual = underTest.test(identificationNumber);

        // then
        assertEquals(expected, actual);
    }
}