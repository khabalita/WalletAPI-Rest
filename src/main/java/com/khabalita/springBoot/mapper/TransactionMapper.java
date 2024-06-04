package com.khabalita.springBoot.mapper;

import com.khabalita.springBoot.dto.TransactionDto;
import com.khabalita.springBoot.entities.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Transaction transactionDtoToTrasaction(TransactionDto transactionDto){
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        return transaction;
    }

    public TransactionDto transactionToTransactionDto(Transaction transaction){
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        return transactionDto;
    }
}
