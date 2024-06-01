package com.khabalita.springBoot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "sessions")
@Data
public class Session extends Base{
    @Column(name = "startSession")
    private LocalDate startSession;
    @Column(name = "endSession")
    private LocalDate endSession;
}