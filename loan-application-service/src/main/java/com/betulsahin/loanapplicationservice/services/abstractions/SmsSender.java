package com.betulsahin.loanapplicationservice.services.abstractions;

import com.betulsahin.loanapplicationservice.dtos.request.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
