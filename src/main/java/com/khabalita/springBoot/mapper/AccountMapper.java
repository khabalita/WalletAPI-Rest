package com.khabalita.springBoot.mapper;

import com.khabalita.springBoot.dto.AccountDto;
import com.khabalita.springBoot.dto.TransactionDto;
import com.khabalita.springBoot.dto.UserDto;
import com.khabalita.springBoot.entities.Account;
import com.khabalita.springBoot.entities.Transaction;
import com.khabalita.springBoot.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Account AccountDtoToAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        if (accountDto.getUserDto() != null) {
            User user = modelMapper.map(accountDto.getUserDto(), User.class);
            account.setUser(user);
        }
        if (accountDto.getTransactionDtoList() != null) {
            List<Transaction> transactions = accountDto.getTransactionDtoList().stream()
                    .map(transactionDto -> modelMapper.map(transactionDto, Transaction.class))
                    .collect(Collectors.toList());
            account.setTransaction(transactions);
        }
        return account;
    }

    public AccountDto accountToAccountDto(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        if (account.getUser() != null) {
            UserDto userDto = modelMapper.map(account.getUser(), UserDto.class);
            accountDto.setUserDto(userDto);
        }
        if (account.getTransaction() != null) {
            List<TransactionDto> transactionDtoList = account.getTransaction().stream()
                    .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                    .collect(Collectors.toList());
            accountDto.setTransactionDtoList(transactionDtoList);
        }
        return accountDto;
    }
}
