package com.loanapplicationsystem.backend.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDtoInput {
    private Long id;

    @ApiModelProperty(example = "4")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int creditLimitMultiplier;

    private Long customerId;
}
