package com.khabalita.springBoot.service;

import com.khabalita.springBoot.dto.AccountDto;
import com.khabalita.springBoot.dto.UserDto;
import com.khabalita.springBoot.entities.Account;
import com.khabalita.springBoot.entities.Transaction;
import com.khabalita.springBoot.entities.User;
import com.khabalita.springBoot.mapper.AccountMapper;
import com.khabalita.springBoot.mapper.BeneficiaryMapper;
import com.khabalita.springBoot.mapper.TransactionMapper;
import com.khabalita.springBoot.mapper.UserMapper;
import com.khabalita.springBoot.repository.AccountRepository;
import com.khabalita.springBoot.repository.BeneficiaryRepository;
import com.khabalita.springBoot.repository.TransactionRepository;
import com.khabalita.springBoot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @Mock
    private BeneficiaryMapper beneficiaryMapper;

    private Account account;
    private AccountDto accountDto;
    private User user;
    private UserDto userDto;


    @BeforeEach
    void setAccount() {
        user = User.builder()
                .id(1L)
                .name("nico")
                .email("nico@gmail.com")
                .password("12345")
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .name("nico")
                .email("nico@gmail.com")
                .password("12345")
                .build();

        account = Account.builder()
                .id(1L)
                .type("cuenta corriente")
                .accountBalance("a favor")
                .user(user)
                .build();

        accountDto = AccountDto.builder()
                .id(1L)
                .type("cuenta corriente")
                .accountBalance("a favor")
                .userDto(userDto)
                .build();
    }

    @Test
    void testNewAccount() throws Exception {
        when(accountMapper.AccountDtoToAccount(any(AccountDto.class))).thenReturn(account);
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(account.getUser()).thenReturn(user);

        Account result = accountService.newAccount(accountDto);

        assertNotNull(result);
        assertEquals("cuenta corriente", result.getType());
        assertEquals("a favor", result.getAccountBalance());
        assertEquals("nico", result.getUser().getName());
        assertEquals("nico@gmail.com", result.getUser().getEmail());
        assertEquals("12345", result.getUser().getPassword());
    }


}
