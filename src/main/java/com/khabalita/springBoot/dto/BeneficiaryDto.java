package com.khabalita.springBoot.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeneficiaryDto {
    private String name;
    private String address;
    private String informationContact;
}
