package com.khabalita.springBoot.mapper;

import com.khabalita.springBoot.dto.BeneficiaryDto;
import com.khabalita.springBoot.entities.Beneficiary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Beneficiary beneficiaryDtoToBeneficiary(BeneficiaryDto beneficiaryDto){
        Beneficiary beneficiary = modelMapper.map(beneficiaryDto, Beneficiary.class);
        return beneficiary;
    }

    public BeneficiaryDto beneficiaryToBeneficiaryDto(Beneficiary beneficiary){
        BeneficiaryDto beneficiaryDto = modelMapper.map(beneficiary, BeneficiaryDto.class);
        return beneficiaryDto;
    }
}
