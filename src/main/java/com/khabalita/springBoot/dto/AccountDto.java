package com.khabalita.springBoot.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountDto {
    private String type;
    private String accountBalance;
    private UserDto userDto;
    private List<TransactionDto> transactionDtoList;
}
