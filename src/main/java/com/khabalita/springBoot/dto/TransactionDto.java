package com.khabalita.springBoot.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDto {
    private BigDecimal amount;
    private Date date;
    private String state;
}
