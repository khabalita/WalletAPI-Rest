package com.khabalita.springBoot.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TransactionDto {
    private BigDecimal amount;
    private Date date;
    private String state;
}
