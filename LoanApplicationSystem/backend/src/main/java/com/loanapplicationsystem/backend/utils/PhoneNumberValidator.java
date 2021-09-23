package com.loanapplicationsystem.backend.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneNumberValidator implements Predicate<String> {

    @Override
    public boolean test(String phoneNumber) {
        String regex = "^\\d{10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.find();
    }
}
