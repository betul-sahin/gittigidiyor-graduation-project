package com.loanapplicationsystem.backend.services;

import com.loanapplicationsystem.backend.config.TwilioConfiguration;
import com.loanapplicationsystem.backend.dtos.SmsRequest;
import com.loanapplicationsystem.backend.services.abstractions.SmsSender;
import com.loanapplicationsystem.backend.services.validators.PhoneNumberValidator;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
