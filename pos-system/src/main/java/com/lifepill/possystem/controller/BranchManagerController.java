package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.EmployerWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerAllDetailsUpdateDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerUpdateAccountDetailsDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerUpdateBankAccountDTO;
import com.lifepill.possystem.service.BranchService;
import com.lifepill.possystem.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("lifepill/v1/branch-manager")
public class BranchManagerController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private EmployerService employerService;


    // need to change only look for branch manager's branch employers only
    @GetMapping("/employer/by-branch-manager/{branchId}")
    public ResponseEntity<List<EmployerDTO>> getAllCashiersByBranchId(@PathVariable int branchId) {
        List<EmployerDTO> employerDTOS = employerService.getAllEmployerByBranchId(branchId);
        return new ResponseEntity<>(employerDTOS, HttpStatus.OK);
    }

}
