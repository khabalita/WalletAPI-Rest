package com.khabalita.springBoot.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Data
@Builder
public class User extends Base {
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "totalBalance")
    private BigDecimal totalBalance;
}
