package com.betulsahin.loanapplicationservice.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDtoInput {
    private Long id;

    @ApiModelProperty(example = "4")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int creditLimitMultiplier;

    private String identificationNumber;
}
