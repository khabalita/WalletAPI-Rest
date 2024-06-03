package com.khabalita.springBoot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SessionDto {
    private LocalDate startSession;
    private LocalDate endSession;
}
