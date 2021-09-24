package com.loanapplicationsystem.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionLogDto {
    private Long id;
    private String exceptionType;
    private String exceptionMessage;
    private LocalDate throwedDate;
}
