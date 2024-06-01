package com.khabalita.springBoot.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "accounts")
@Data
public class Account extends Base{
    @Column(name = "type")
    private String type;
    @Column(name ="AccBalance")
    private String accountBalance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    private List<Transaction> transaction;
}
