package com.khabalita.springBoot.service;

import com.khabalita.springBoot.dto.TransactionDto;
import com.khabalita.springBoot.entities.Transaction;
import com.khabalita.springBoot.mapper.TransactionMapper;
import com.khabalita.springBoot.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;
    private TransactionDto transactionDto;

    //Formateo de fecha para usar en el test
    String dateString = "2024-06-04T15:15:00-03:00";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    @BeforeEach
    void setTransaction() throws ParseException {
        transaction = Transaction.builder()
                .id(1L)
                .state("realizada")
                .date(formatter.parse(dateString))
                .amount(BigDecimal.valueOf(150.000))
                .build();

        transactionDto = TransactionDto.builder()
                .id(1L)
                .state("realizada")
                .date(formatter.parse(dateString))
                .amount(BigDecimal.valueOf(150.000))
                .build();
    }

    @Test
    void testNewTransaction() throws Exception {
        when(transactionMapper.transactionDtoToTrasaction(any(TransactionDto.class))).thenReturn(transaction);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.newTransaction(transactionDto);

        assertNotNull(result);
        assertEquals("realizada", result.getState());
        assertEquals(transaction.getDate(), result.getDate());
        assertEquals(150.000, result.getAmount().doubleValue(), 0.001);
    }

    @Test
    void testListAllTransactions() throws  Exception  {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);
        when(transactionMapper.transactionToTransactionDto(any(Transaction.class))).thenReturn(transactionDto);

        List<TransactionDto> result = transactionService.listAllTransaction();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("realizada", result.get(0).getState());
        assertEquals(transactionDto.getDate(), result.get(0).getDate());
        assertEquals(150.000, result.get(0).getAmount().doubleValue(), 0.001);

    }

    @Test
    void testUpdateTransaction() throws Exception {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionMapper.transactionToTransactionDto(any(Transaction.class))).thenReturn(transactionDto);

        TransactionDto result = transactionService.updateTransaction(1L, transactionDto);

        assertNotNull(result);
        assertEquals("realizada", result.getState());
        assertEquals(transaction.getDate(), result.getDate());
        assertEquals(150.000, result.getAmount().doubleValue(), 0.001);
    }

    @Test
    void testDeleteTransaction() throws Exception {
        when(transactionRepository.existsById(1L)).thenReturn(true);

        boolean result = transactionService.deleteTransaction(1L);

        assertTrue(result);
    }

    @Test
    void testFindTransactionById() throws Exception {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionMapper.transactionToTransactionDto(any(Transaction.class))).thenReturn(transactionDto);

        TransactionDto result = transactionService.findTransactionById(1L);

        assertNotNull(result);
        assertEquals("realizada", result.getState());
        assertEquals(transaction.getDate(), result.getDate());
        assertEquals(150.000, result.getAmount().doubleValue(), 0.001);
    }
}
