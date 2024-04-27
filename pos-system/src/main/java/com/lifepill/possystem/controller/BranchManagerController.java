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

/**
 * Controller class for managing branch-related operations by branch managers.
 */
@RestController
@RequestMapping("lifepill/v1/branch-manager")
public class BranchManagerController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private EmployerService employerService;

    /**
     * Retrieves all cashiers (employers) associated with a specific branch managed by the branch manager.
     *
     * @param branchId The ID of the branch for which cashiers are to be retrieved.
     * @return A ResponseEntity containing a list of EmployerDTO objects representing the cashiers.
     */
    @GetMapping("/employer/by-branch-manager/{branchId}")
    public ResponseEntity<List<EmployerDTO>> getAllCashiersByBranchId(@PathVariable int branchId) {
        List<EmployerDTO> employerDTOS = employerService.getAllEmployerByBranchId(branchId);
        return new ResponseEntity<>(employerDTOS, HttpStatus.OK);
    }
}
