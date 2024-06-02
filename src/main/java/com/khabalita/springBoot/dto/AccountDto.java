package com.khabalita.springBoot.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private String type;
    private String accountBalance;
    private UserDto userDto;
    private List<TransactionDto> transactionDtoList;
}
