package com.khabalita.springBoot.controller;

import com.khabalita.springBoot.dto.BeneficiaryDto;
import com.khabalita.springBoot.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/beneficiaries")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping("/createBeneficiary")
    public ResponseEntity<?> createBeneficiary(@RequestBody BeneficiaryDto beneficiaryDto) throws Exception{
        beneficiaryService.newBeneficiary(beneficiaryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public ResponseEntity<?> getBenericiary(@PathVariable Long id) throws Exception{
        BeneficiaryDto beneficiaryDto = beneficiaryService.findBeneficiaryById(id);
        if(beneficiaryDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(beneficiaryDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @GetMapping("/listBeneficiaries")
    @ResponseBody
    public ResponseEntity<?> getAllBeneficiaries() throws Exception{
        List<BeneficiaryDto> beneficiaryDtoList = beneficiaryService.listAllBeneficiaries();
        return ResponseEntity.status(HttpStatus.OK).body(beneficiaryDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBeneficiary(@PathVariable Long id, @RequestBody BeneficiaryDto beneficiaryDto) throws Exception{
        BeneficiaryDto updatedBeneficiary = beneficiaryService.updateBeneficiary(id, beneficiaryDto);
        if(updatedBeneficiary != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedBeneficiary);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Beneficiary not found}");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBeneficiary(@PathVariable Long id) throws Exception{
        boolean deleted = beneficiaryService.deleteBeneficiary(id);
        if(deleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("/Beneficiary not found");
        }
    }
}
