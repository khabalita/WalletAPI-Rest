package com.khabalita.springBoot.service;

import com.khabalita.springBoot.dto.AccountDto;
import com.khabalita.springBoot.dto.BeneficiaryDto;
import com.khabalita.springBoot.entities.Account;
import com.khabalita.springBoot.entities.Beneficiary;
import com.khabalita.springBoot.mapper.BeneficiaryMapper;
import com.khabalita.springBoot.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeneficiaryService {
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private BeneficiaryMapper beneficiaryMapper;

    @Transactional
    public Beneficiary newBeneficiary(BeneficiaryDto beneficiaryDto) throws Exception{
        try{
            Beneficiary beneficiary = beneficiaryMapper.beneficiaryDtoToBeneficiary(beneficiaryDto);
            Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
            return savedBeneficiary;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Transactional
    public List<BeneficiaryDto> listAllBeneficiaries() throws Exception{
        try{
            List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
            List<BeneficiaryDto> beneficiaryDtoList = new ArrayList<>();
            for(Beneficiary beneficiary: beneficiaryList){
                beneficiaryDtoList.add(beneficiaryMapper.beneficiaryToBeneficiaryDto(beneficiary));
            }
            return beneficiaryDtoList;
        } catch (Exception ex){
            throw new Exception (ex.getMessage());
        }
    }

    @Transactional
    public BeneficiaryDto updateBeneficiary(Long id, BeneficiaryDto beneficiaryDto) throws Exception {
        try{
            Beneficiary existingBeneficiary = beneficiaryRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de beneficiario no encontrado" + id));
            existingBeneficiary.setName(beneficiaryDto.getName());
            existingBeneficiary.setAddress(beneficiaryDto.getAddress());
            existingBeneficiary.setInformationContact(beneficiaryDto.getInformationContact());
            return beneficiaryMapper.beneficiaryToBeneficiaryDto(existingBeneficiary);
        }catch(Exception ex){
            throw new Exception ("No se pudo actualizar el beneficiario" + ex.getMessage());
        }
    }

    @Transactional
    public boolean deleteBeneficiary(Long id) throws Exception{
        try{
            if(beneficiaryRepository.existsById(id)){
                beneficiaryRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("ID not found" + id);
            }
        }catch (Exception ex){
            throw new Exception ("No se pudo eliminar el beneficiario" + ex.getMessage());
        }
    }
    @Transactional
    public BeneficiaryDto findBeneficiaryById(Long id) throws Exception{
        try{
            Beneficiary beneficiary = beneficiaryRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID de beneficiario no encontrado" + id));
            return beneficiaryMapper.beneficiaryToBeneficiaryDto(beneficiary);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
