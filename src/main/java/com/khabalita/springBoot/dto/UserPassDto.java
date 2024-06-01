package com.khabalita.springBoot.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserPassDto {
    //DTO que no devuelve ni contrasenia ni el saldo o balance
    private String name;
    private String password;
    private String email;
    private BigDecimal totalBalance;
}
