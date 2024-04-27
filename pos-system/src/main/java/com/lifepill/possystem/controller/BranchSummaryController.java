package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.service.BranchSummaryService;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Controller class to handle branch summary related endpoints.
 */
@RestController
@RequestMapping("lifepill/v1/branch-summary")
public class BranchSummaryController {

    @Autowired
    private BranchSummaryService branchSummaryService;

    /**
     * Retrieves all branches along with their sales summary.
     *
     * @return ResponseEntity containing StandardResponse with status 201 (SUCCESS)
     * and list of PharmacyBranchResponseDTO
     */
    @GetMapping("/sales-summary")
    public ResponseEntity<StandardResponse> getAllBranchesWithSales() {
        List<PharmacyBranchResponseDTO> allBranchesWithSales = branchSummaryService.getAllBranchesWithSales();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", allBranchesWithSales),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves sales summary of a specific branch by its ID.
     *
     * @param branchId The ID of the branch
     * @return ResponseEntity containing StandardResponse with status 201 (SUCCESS) and PharmacyBranchResponseDTO
     */
    @GetMapping(path ="/sales-summary/{id}")
    public ResponseEntity<StandardResponse> getBranchSalesById(@PathVariable(value = "id") long branchId){

        PharmacyBranchResponseDTO pharmacyBranchResponseDTO = branchSummaryService.getBranchSalesById(branchId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", pharmacyBranchResponseDTO),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves all pharmacy data for a selected date.
     *
     * @param date The selected date.
     * @return List of PharmacyBranchResponseDTO containing pharmacy data for the selected date.
     */
    @GetMapping("/pharmacy-summary-by-date/")
    public  ResponseEntity<StandardResponse> getPharmacyDataByDate(
            @RequestParam("date")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<PharmacyBranchResponseDTO> pharmacyData = branchSummaryService.getPharmacyDataByDate(date);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", pharmacyData),
                HttpStatus.OK
        );
    }
}
