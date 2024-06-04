package com.khabalita.springBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private long id;
    private String type;
    private String accountBalance;
    private UserDto userDto;
    private List<TransactionDto> transactionDtoList;
}
