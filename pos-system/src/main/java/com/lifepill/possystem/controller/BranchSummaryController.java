package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.service.BranchSummaryService;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lifepill/v1/branch-summary")
public class BranchSummaryController {

    @Autowired
    private BranchSummaryService branchSummaryService;

    @GetMapping("/sales-summary")
    public ResponseEntity<StandardResponse> getAllBranchesWithSales() {
        List<PharmacyBranchResponseDTO> allBranchesWithSales = branchSummaryService.getAllBranchesWithSales();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", allBranchesWithSales),
                HttpStatus.OK
        );
    }

    @GetMapping(path ="/sales-summary/{id}")
    public ResponseEntity<StandardResponse> getBranchSalesById(@PathVariable(value = "id") long branchId){

        PharmacyBranchResponseDTO pharmacyBranchResponseDTO = branchSummaryService.getBranchSalesById(branchId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", pharmacyBranchResponseDTO),
                HttpStatus.OK
        );
    }

}
