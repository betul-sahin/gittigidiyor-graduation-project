package com.loanapplicationsystem.backend.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component
@SessionScope
public class ClientRequestInfo {
    private String clientIpAdress;
    private String clientUrl;
    private String sessionActivityId;
}
