package com.khabalita.springBoot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SessionDto {
    private LocalDate startSession;
    private LocalDate endSession;
}
