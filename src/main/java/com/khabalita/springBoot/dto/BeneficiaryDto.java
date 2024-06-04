package com.khabalita.springBoot.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficiaryDto {
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String informationContact;
}
