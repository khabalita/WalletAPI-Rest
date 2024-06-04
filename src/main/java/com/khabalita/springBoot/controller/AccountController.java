package com.khabalita.springBoot.controller;

import com.khabalita.springBoot.dto.AccountDto;
import com.khabalita.springBoot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/createAccount")
    public ResponseEntity<?> createBeneficiary(@RequestBody AccountDto accountDto) throws Exception{
        accountService.newAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public ResponseEntity<?> getAccount(@PathVariable Long id) throws Exception{
        AccountDto accountDto = accountService.findAccountById(id);
        if(accountDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(accountDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @GetMapping("/listAccounts")
    @ResponseBody
    public ResponseEntity<?> getAllAccounts() throws Exception{
        List<AccountDto> accountDtoList = accountService.listAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accountDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto) throws Exception{
        AccountDto updatedAccount = accountService.updateAccount(id, accountDto);
        if(updatedAccount != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedAccount);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Account not found}");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) throws Exception{
        boolean deleted = accountService.deleteAccount(id);
        if(deleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("/Account not found");
        }
    }
}
