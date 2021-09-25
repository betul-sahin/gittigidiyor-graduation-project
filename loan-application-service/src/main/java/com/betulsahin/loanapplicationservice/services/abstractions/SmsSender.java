package com.betulsahin.loanapplicationservice.services.abstractions;

import com.betulsahin.loanapplicationservice.dtos.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
