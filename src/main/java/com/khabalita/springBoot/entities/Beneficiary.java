package com.khabalita.springBoot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "beneficiaries")
@Data
public class Beneficiary extends Base{
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String Address;
    @Column(name = "infoContact")
    private String informationContact;
}
