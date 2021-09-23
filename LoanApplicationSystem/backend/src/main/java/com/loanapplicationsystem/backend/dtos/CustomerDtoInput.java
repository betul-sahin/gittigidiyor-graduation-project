package com.loanapplicationsystem.backend.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoInput {
    private long id;

    @NotEmpty
    private String identificationNumber;

    @ApiModelProperty(example = "Aybike Güliz Enzel Yağmur Eflinnisa")
    @NotEmpty
    @Size(max=50, message = "Your first name cannot be greater than 50 characters")
    private String firstName;

    @ApiModelProperty(example = "Nebioğulları")
    @NotEmpty
    @Size(max=50, message = "Your last name cannot be greater than 50 characters")
    private String LastName;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double monthlyIncome;

    @NotEmpty
    private String phoneNumber;
}