package com.khabalita.springBoot.service;

import com.khabalita.springBoot.dto.TransactionDto;
import com.khabalita.springBoot.entities.Transaction;
import com.khabalita.springBoot.mapper.TransactionMapper;
import com.khabalita.springBoot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Transactional
    public Transaction newTransaction(TransactionDto transactionDto) throws Exception{
        try{
            Transaction transaction = transactionMapper.transactionDtoToTrasaction(transactionDto);
            Transaction savedTransaction = transactionRepository.save(transaction);
            return savedTransaction;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Transactional
    public List<TransactionDto> listAllTransaction() throws Exception{
        try{
            List<Transaction> transactionList = transactionRepository.findAll();
            List<TransactionDto> transactionDtoList = new ArrayList<>();
            for(Transaction transaction: transactionList){
                transactionDtoList.add(transactionMapper.transactionToTransactionDto(transaction));
            }
            return transactionDtoList;
        } catch (Exception e) {
            throw new Exception (e.getMessage());
        }
    }

    @Transactional
    public TransactionDto updateTransaction(Long id, TransactionDto transcationDto) throws Exception {
        try{
            Transaction existingTransaction = transactionRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de transaccion no encontrado" + id));
            existingTransaction.setAmount(transcationDto.getAmount());
            existingTransaction.setDate(transcationDto.getDate());
            existingTransaction.setAmount(transcationDto.getAmount());
            return transactionMapper.transactionToTransactionDto(existingTransaction);
        }catch(Exception e){
            throw new Exception ("No se pudo actualizar la transaccion" + e.getMessage());
        }
    }

    @Transactional
    public boolean deleteTransaction(Long id) throws Exception{
        try{
            if(transactionRepository.existsById(id)){
                transactionRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("ID not found" + id);
            }
        }catch (Exception e){
            throw new Exception ("No se pudo eliminar la transaccion" + e.getMessage());
        }
    }

    @Transactional
    public TransactionDto findTransactionById(Long id) throws Exception{
        try{
            Transaction transaction = transactionRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de transaccion no encontrado" + id));
            return transactionMapper.transactionToTransactionDto(transaction);
        }catch (Exception e){
            throw new Exception("No se pudo traer la transaccion" + e.getMessage());
        }
    }

}
