package com.betulsahin.loanapplicationservice.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("twillo")
@RequiredArgsConstructor
public class TwilioSmsSenderImpl implements SmsSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSenderImpl.class);

    private final TwilioConfiguration twilioConfiguration;
    private final PhoneNumberValidator phoneNumberValidator;

    @Override
    public void sendSms(SmsRequest request) {
        // If phone number is valid it throws exception
        // phoneNumberValidator.test(request.getPhoneNumber());

        PhoneNumber to = new PhoneNumber(request.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = request.getMessage();
        MessageCreator creator = Message.creator(to, from, message);
        creator.create();

        LOGGER.info("Send sms {}", request);
    }
}
