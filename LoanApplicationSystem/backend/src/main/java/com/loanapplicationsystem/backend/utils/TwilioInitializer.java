package com.loanapplicationsystem.backend.utils;

import com.loanapplicationsystem.backend.config.TwilioConfiguration;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {
    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration){
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken());
    }
}
