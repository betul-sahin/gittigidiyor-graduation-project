package com.loanapplicationsystem.backend.models;

import com.loanapplicationsystem.backend.models.abstractions.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionLog extends AbstractBaseEntity {
    private String exceptionType;
    private String exceptionMessage;
    private LocalDate throwedDate;
}
