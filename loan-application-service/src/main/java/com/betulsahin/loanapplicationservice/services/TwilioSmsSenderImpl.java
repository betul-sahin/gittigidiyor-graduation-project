package com.betulsahin.loanapplicationservice.services;

import com.betulsahin.loanapplicationservice.configurations.TwilioConfiguration;
import com.betulsahin.loanapplicationservice.dtos.request.SmsRequest;
import com.betulsahin.loanapplicationservice.services.abstractions.SmsSender;
import com.betulsahin.loanapplicationservice.services.validators.PhoneNumberValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service("twillo")
@RequiredArgsConstructor
public class TwilioSmsSenderImpl implements SmsSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSenderImpl.class);

    private final TwilioConfiguration twilioConfiguration;
    private final PhoneNumberValidator phoneNumberValidator;

    /**
     * Sends a sms request.
     *
     * @param request
     */
    @Override
    public void sendSms(SmsRequest request) {
        // If phone number is valid it throws exception
        phoneNumberValidator.test(request.getPhoneNumber());

        PhoneNumber to = new PhoneNumber(request.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = request.getMessage();
        MessageCreator creator = Message.creator(to, from, message);
        creator.create();

        LOGGER.info("Send sms {}", request);
    }
}
