package com.khabalita.springBoot.controller;

import com.khabalita.springBoot.dto.TransactionDto;
import com.khabalita.springBoot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/createTransaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) throws Exception{
        transactionService.newTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public ResponseEntity<?> getTransaction(@PathVariable Long id) throws Exception{
        TransactionDto transactionDto = transactionService.findTransactionById(id);
        if(transactionDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @GetMapping("/listTransactions")
    @ResponseBody
    public ResponseEntity<?> getAllTransactions() throws Exception{
        List<TransactionDto> transactionDtoList = transactionService.listAllTransaction();
        return ResponseEntity.status(HttpStatus.OK).body(transactionDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) throws Exception{
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, transactionDto);
        if(updatedTransaction != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedTransaction);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Transaction not found}");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) throws Exception{
        boolean deleted = transactionService.deleteTransaction(id);
        if(deleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("/Transaction not found");
        }
    }
}
