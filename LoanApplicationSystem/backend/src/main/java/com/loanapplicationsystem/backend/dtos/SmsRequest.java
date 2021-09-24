package com.loanapplicationsystem.backend.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class SmsRequest {
    @NotBlank
    private final String phoneNumber;

    @NotBlank
    private final String message;
}
