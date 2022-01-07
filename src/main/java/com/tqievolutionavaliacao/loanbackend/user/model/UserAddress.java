package com.tqievolutionavaliacao.loanbackend.user.model;

import com.tqievolutionavaliacao.loanbackend.user.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_address")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String state;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotNull
    private int number;
    @NotBlank
    private String cep;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserAddress(String state, int number, String city, String street, String cep) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.cep = cep;
        this.number = number;
    }

    public UserAddress(String state, String city, String street, int number, String cep, User user) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
        this.cep = cep;
        this.user = user;
    }
}
