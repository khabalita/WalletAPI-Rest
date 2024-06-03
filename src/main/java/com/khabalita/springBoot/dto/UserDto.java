package com.khabalita.springBoot.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {
    //Clase DTO que no devuelve contrasenia ni saldo
    private String name;
    private String email;
}
