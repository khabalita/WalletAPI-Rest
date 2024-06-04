package com.khabalita.springBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDto {
    private long id;
    private LocalDate startSession;
    private LocalDate endSession;
}
