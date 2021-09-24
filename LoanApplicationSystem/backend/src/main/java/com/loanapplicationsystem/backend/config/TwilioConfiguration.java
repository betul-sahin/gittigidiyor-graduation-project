package com.loanapplicationsystem.backend.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties("twillo")
public class TwilioConfiguration {
    private String accountSid;
    private String authToken;
    private String trialNumber;
}
