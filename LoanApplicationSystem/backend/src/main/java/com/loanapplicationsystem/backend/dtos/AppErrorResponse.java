package com.loanapplicationsystem.backend.dtos;

import com.loanapplicationsystem.backend.models.abstractions.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}