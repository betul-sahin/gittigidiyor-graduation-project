package com.loanapplicationsystem.backend.services.abstractions;

import com.loanapplicationsystem.backend.dtos.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
