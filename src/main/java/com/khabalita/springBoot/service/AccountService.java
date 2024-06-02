package com.khabalita.springBoot.service;

import com.khabalita.springBoot.dto.AccountDto;
import com.khabalita.springBoot.entities.Account;
import com.khabalita.springBoot.entities.Transaction;
import com.khabalita.springBoot.entities.User;
import com.khabalita.springBoot.mapper.AccountMapper;
import com.khabalita.springBoot.mapper.UserMapper;
import com.khabalita.springBoot.repository.AccountRepository;
import com.khabalita.springBoot.repository.TransactionRepository;
import com.khabalita.springBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserMapper userMapper;



    @Transactional
    private Account newAccount(AccountDto accountDto) throws Exception{
        try{
            Account account = accountMapper.AccountDtoToAccount(accountDto);
            if(account.getUser() != null){
                User savedUser = userRepository.save(account.getUser());
                account.setUser(savedUser);
            }
            if (account.getTransaction() != null && !account.getTransaction().isEmpty()) {
                List<Transaction> savedTransactionList = account.getTransaction().stream()
                        .map(transactionRepository::save)
                        .collect(Collectors.toList());
                account.setTransaction(savedTransactionList);
            }
            Account savedAccount = accountRepository.save(account);
            return savedAccount;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Transactional
    public List<AccountDto> listAllAccounts() throws Exception{
        try{
            List<Account> accountList = accountRepository.findAll();
            List<AccountDto> accountDtoList = new ArrayList<>();
            for(Account account : accountList){
                accountDtoList.add(accountMapper.accountToAccountDto(account));
            }
            return accountDtoList;
        } catch (Exception ex) {
            throw new Exception (ex.getMessage());
        }
    }

    @Transactional
    public AccountDto updateAccount(Long id, AccountDto accountDto) throws Exception {
        try{
            Account existingAccount = accountRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de cuenta no encontrado" + id));
            existingAccount.setType(accountDto.getType());
            existingAccount.setAccountBalance(accountDto.getAccountBalance());
            //Si el usuario es distinto de null, lo setea desde el mapper
            if (accountDto.getUserDto() != null) {
                User user = userMapper.userDtoToUser(accountDto.getUserDto());
                existingAccount.setUser(user);
            }
            //Si la lista de transacciones es distinta de null y no esta vacia, la setea desde el mapper
            if (accountDto.getTransactionDtoList() != null && !accountDto.getTransactionDtoList().isEmpty()) {
                List<Transaction> transactions = accountDto.getTransactionDtoList().stream().map(transactionDto -> {
                    Transaction transaction = new Transaction();
                    transaction.setAmount(transactionDto.getAmount());
                    transaction.setDate(transactionDto.getDate());
                    transaction.setState(transactionDto.getState());
                    return transaction;
                }).collect(Collectors.toList());
                existingAccount.setTransaction(transactions);
            }

            //Guarda el libro actualizado
            Account updatedAccount = accountRepository.save(existingAccount);

            //Devuelve el libro actualizado como un DTO
            return accountMapper.accountToAccountDto(updatedAccount);
        }catch (Exception ex){
            throw new Exception ("No se pudo actualizar la cuenta" + ex.getMessage());
        }
    }

    public boolean deleteAccount(Long id) throws Exception{
        try{
            if(accountRepository.existsById(id)){
                accountRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("ID not found" + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public AccountDto findAccountById(Long id) throws Exception{
        try{
            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de cuenta no encontrado" + id));
            return accountMapper.accountToAccountDto(account);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
