package com.khabalita.springBoot.dto;


import lombok.Data;


@Data
public class UserDto {
    //Clase DTO que no devuelve contrasenia ni saldo
    private String name;
    private String email;
}
