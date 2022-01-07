package com.tqievolutionavaliacao.loanbackend.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    @NotBlank
    private final String name;
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
    @NotBlank
    private final String cpf;
    @NotBlank
    private final String rg;
    @NotNull
    private final Double income;
    @NotBlank
    private final String state;
    @NotBlank
    private final String city;
    @NotBlank
    private final String street;
    @NotNull
    private final Integer number;
    @NotBlank
    private final String cep;


}
