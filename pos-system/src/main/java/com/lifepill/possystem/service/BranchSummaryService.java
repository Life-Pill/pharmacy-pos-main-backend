package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.requestDTO.BranchDailySalesSummaryDTO;
import com.lifepill.possystem.dto.responseDTO.AllPharmacySummaryResponseDTO;
import com.lifepill.possystem.dto.responseDTO.DailySalesSummaryDTO;
import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;

import java.util.Date;
import java.util.List;

/**
 * The interface Branch summary service.
 */
public interface BranchSummaryService {
    /**
     * Gets all branches with sales.
     *
     * @return the all branches with sales
     */
    List<PharmacyBranchResponseDTO> getAllBranchesWithSales();

    /**
     * Gets branch sales by id.
     *
     * @param branchId the branch id
     * @return the branch sales by id
     */
    PharmacyBranchResponseDTO getBranchSalesById(long branchId);

    /**
     * Retrieves all pharmacy data for a selected date.
     *
     * @param date The selected date.
     * @return List of PharmacyBranchResponseDTO containing pharmacy data for the selected date.
     */
    List<PharmacyBranchResponseDTO> getPharmacyDataByDate(Date date);

    /**
     * Retrieves pharmacy data within the given time period.
     *
     * @param startDate The start date of the time period.
     * @param endDate   The end date of the time period.
     * @return List of PharmacyBranchResponseDTO containing pharmacy data within the time period.
     */
    List<PharmacyBranchResponseDTO> getPharmacyDataByTimePeriod(Date startDate, Date endDate);

    /**
     * Retrieves monthly sales data.
     *
     * @return List of PharmacyBranchResponseDTO containing monthly sales data.
     */
    List<PharmacyBranchResponseDTO> getMonthlySummary(int month, int year);

    /**
     * Retrieves a summary of sales for the given year.
     *
     * @param year The year for which to retrieve the summary.
     * @return A list of PharmacyBranchResponseDTO containing the summary of sales for the given year.
     */
    List<PharmacyBranchResponseDTO> getYearlySummary(int year);
    /**
     * Retrieves total details of all pharmacy branches.
     *
     * @return AllPharmacySummaryResponseDTO containing total sales, total orders, total employees, and total branches.
     */
    AllPharmacySummaryResponseDTO getAllPharmacySummary();

    /**
     * Retrieves daily sales summary for a specific branch.
     *
     * This method is responsible for fetching the daily sales summary for a specific branch.
     * The summary includes the date, number of orders, and total sales for each day.
     *
     * @param branchId The ID of the branch for which the daily sales summary is to be fetched.
     * @return A list of DailySalesSummaryDTO objects, each representing the sales summary for a specific day.
     */
    List<DailySalesSummaryDTO> getDailySalesSummary(long branchId);


    List<BranchDailySalesSummaryDTO> getAllDailySalesSummary();
}
