package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.responseDTO.AllPharmacySummaryResponseDTO;
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
@CrossOrigin(origins = "http://localhost:5173")
public class BranchSummaryController {

    @Autowired
    private BranchSummaryService branchSummaryService;

    /**
     * gti checkRetrieves all branches along with their sales summary.
     *
     * @return ResponseEntity containing StandardResponse with status 201 (SUCCESS)
     * and list of PharmacyBranchResponseDTO
     */
    @GetMapping("/sales-summary")
    public ResponseEntity<StandardResponse> getAllBranchesWithSales() {
        List<PharmacyBranchResponseDTO> allBranchesWithSales = branchSummaryService.getAllBranchesWithSales();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        201,
                        "SUCCESS",
                        allBranchesWithSales
                ),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves sales summary of a specific branch by its ID.
     *
     * @param branchId The ID of the branch
     * @return ResponseEntity containing StandardResponse with status 201 (SUCCESS) and PharmacyBranchResponseDTO
     */
    @GetMapping(path = "/sales-summary/{id}")
    public ResponseEntity<StandardResponse> getBranchSalesById(
            @PathVariable(value = "id") long branchId
    ) {
        PharmacyBranchResponseDTO pharmacyBranchResponseDTO = branchSummaryService
                .getBranchSalesById(branchId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        201,
                        "SUCCESS",
                        pharmacyBranchResponseDTO
                ),
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
    public ResponseEntity<StandardResponse> getPharmacyDataByDate(
            @RequestParam("date")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<PharmacyBranchResponseDTO> pharmacyData = branchSummaryService.getPharmacyDataByDate(date);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        201,
                        "SUCCESS",
                        pharmacyData
                ),
                HttpStatus.OK
        );
    }

    /**
     * Endpoint for retrieving pharmacy data within a specified time period.
     *
     * @param startDate The start date of the time period.
     * @param endDate   The end date of the time period.
     * @return ResponseEntity containing a list of
     * PharmacyBranchResponseDTOs with pharmacy data within
     * the specified time period.
     */
    @GetMapping("/pharmacy-summary-by-period")
    public ResponseEntity<StandardResponse> getPharmacyDataByTimePeriod(
            @RequestParam("startDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<PharmacyBranchResponseDTO> pharmacyData = branchSummaryService
                .getPharmacyDataByTimePeriod(startDate, endDate);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        201,
                        "SUCCESS",
                        pharmacyData
                ),
                HttpStatus.OK
        );
    }

    /**
     * Endpoint to retrieve a summary of sales for the given month and year.
     *
     * @param month The month for which to retrieve the summary (1-12).
     * @param year  The year for which to retrieve the summary.
     * @return ResponseEntity containing a list of PharmacyBranchResponseDTO with the summary of sales for the given month.
     */
    @GetMapping("/monthly-summary")
    public ResponseEntity<StandardResponse> getMonthlySummary(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        List<PharmacyBranchResponseDTO> monthlySummary = branchSummaryService
                .getMonthlySummary(month, year);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        201,
                        "SUCCESS",
                        monthlySummary
                ),
                HttpStatus.OK
        );
    }

    /**
     * Endpoint to retrieve a summary of sales for the given year.
     *
     * @param year The year for which to retrieve the summary.
     * @return ResponseEntity containing a list of PharmacyBranchResponseDTO with the summary of sales for the given year.
     */
    @GetMapping("/yearly-summary")
    public ResponseEntity<StandardResponse> getYearlySummary(
            @RequestParam("year") int year) {
        List<PharmacyBranchResponseDTO> yearlySummary = branchSummaryService
                .getYearlySummary(year);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        201,
                        "SUCCESS",
                        yearlySummary
                ),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves total details of all pharmacy branches.
     *
     * @return ResponseEntity containing StandardResponse with status 200 (OK) and AllPharmacySummaryResponseDTO
     */
    @GetMapping("/all-branches-summary")
    public ResponseEntity getAllPharmacySummary() {
        // Call service method to retrieve total details of all pharmacy branches
        AllPharmacySummaryResponseDTO summary = branchSummaryService.getAllPharmacySummary();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "OK",
                        summary
                ),
                HttpStatus.OK
        );
    }
}