package com.khabalita.springBoot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@Builder
public class Transaction extends Base{
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private Date date;
    @Column(name = "state")
    private String state;
}
