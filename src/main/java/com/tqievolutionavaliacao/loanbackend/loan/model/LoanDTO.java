package com.tqievolutionavaliacao.loanbackend.loan.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class LoanDTO {

    private Long id;
    @NotNull
    private Double amount;
    @NotNull
    private Integer term;
    @NotNull
    private LocalDate firstPaymentDate;


}
