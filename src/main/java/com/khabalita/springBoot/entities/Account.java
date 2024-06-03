package com.khabalita.springBoot.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@Builder
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
