package com.betulsahin.loanapplicationservice.services.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PhoneNumberValidatorTest {
    private PhoneNumberValidator underTest;

    @BeforeEach
    void setUp(){
        underTest = new PhoneNumberValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "+905554443322, true",
            "5554443322, false",
            "+905554443322123, false"
    })
    void test_itShouldValidatePhoneNumber(String phoneNumber, boolean expected){
        // given
        // when
        boolean actual = underTest.test(phoneNumber);

        // then
        assertEquals(expected, actual);
    }
}