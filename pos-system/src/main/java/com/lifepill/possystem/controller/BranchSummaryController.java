package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.service.BranchSummaryService;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/sales-summary/{branchId}")
    public ResponseEntity<StandardResponse> getBranchSalesById(long branchId){

        int branchIdAsInt = (int) branchId;
        PharmacyBranchResponseDTO pharmacyBranchResponseDTO = branchSummaryService.getBranchSalesById(branchIdAsInt);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", pharmacyBranchResponseDTO),
                HttpStatus.OK
        );
    }

}
