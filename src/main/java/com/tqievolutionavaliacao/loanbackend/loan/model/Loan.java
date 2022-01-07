package com.tqievolutionavaliacao.loanbackend.loan.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Double amount;
    @NotNull
    private Integer term;
    private LocalDate firstPaymentDate;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private String username;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;
    private Double income;

    public Loan(Double amount, Integer term) {
        this.amount = amount;
        this.term = term;
    }
}
