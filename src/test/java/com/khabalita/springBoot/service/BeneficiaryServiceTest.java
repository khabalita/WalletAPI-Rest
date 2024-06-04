package com.khabalita.springBoot.service;

import com.khabalita.springBoot.dto.BeneficiaryDto;
import com.khabalita.springBoot.entities.Beneficiary;
import com.khabalita.springBoot.mapper.BeneficiaryMapper;
import com.khabalita.springBoot.repository.BeneficiaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryServiceTest {

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @Mock
    private BeneficiaryMapper beneficiartMapper;

    @InjectMocks
    private BeneficiaryService beneficiaryService;

    private Beneficiary beneficiary;
    private BeneficiaryDto beneficiaryDto;

    @BeforeEach
    void setBeneficiaries() {
        beneficiary = Beneficiary.builder()
                .id(1L)
                .name("nicolay")
                .address("7607")
                .informationContact("info de contacto")
                .build();

        beneficiaryDto = BeneficiaryDto.builder()
                .id(1L)
                .name("nicolay")
                .address("7607")
                .informationContact("info de contacto")
                .build();
    }

    @Test
    void testNewBeneficiary() throws Exception {
        when(beneficiartMapper.beneficiaryDtoToBeneficiary(any(BeneficiaryDto.class))).thenReturn(beneficiary);
        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(beneficiary);

        Beneficiary result = beneficiaryService.newBeneficiary(beneficiaryDto);

        assertNotNull(result);
        assertEquals("nicolay", result.getName());
        assertEquals("7607", result.getAddress());
        assertEquals("info de contacto", result.getInformationContact());
    }

    @Test
    void testListAllBeneficiaries() throws Exception {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        beneficiaries.add(beneficiary);
        when(beneficiaryRepository.findAll()).thenReturn(beneficiaries);
        when(beneficiartMapper.beneficiaryToBeneficiaryDto(any(Beneficiary.class))).thenReturn(beneficiaryDto);

        List<BeneficiaryDto> result = beneficiaryService.listAllBeneficiaries();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("nicolay", result.get(0).getName());
        assertEquals("7607", result.get(0).getAddress());
        assertEquals("info de contacto", result.get(0).getInformationContact());
    }

    @Test
    void testUpdateBeneficiary() throws Exception {
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));
        when(beneficiartMapper.beneficiaryToBeneficiaryDto(any(Beneficiary.class))).thenReturn(beneficiaryDto);

        BeneficiaryDto result = beneficiaryService.updateBeneficiary(1L, beneficiaryDto);

        assertNotNull(result);
        assertEquals("nicolay", result.getName());
        assertEquals("7607", result.getAddress());
        assertEquals("info de contacto", result.getInformationContact());
    }

    @Test
    void testDeletBeneficiary() throws Exception {
        when(beneficiaryRepository.existsById(1L)).thenReturn(true);

        boolean result = beneficiaryService.deleteBeneficiary(1L);

        assertTrue(result);
    }

    @Test
    void testFindBeneficaryById() throws Exception {
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));
        when(beneficiartMapper.beneficiaryToBeneficiaryDto(any(Beneficiary.class))).thenReturn(beneficiaryDto);

        BeneficiaryDto result = beneficiaryService.findBeneficiaryById(1L);

        assertNotNull(result);
        assertEquals("nicolay", result.getName());
        assertEquals("7607", result.getAddress());
        assertEquals("info de contacto", result.getInformationContact());
    }
}
