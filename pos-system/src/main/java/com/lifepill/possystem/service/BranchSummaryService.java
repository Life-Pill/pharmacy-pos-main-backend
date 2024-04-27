package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;

import java.util.Date;
import java.util.List;

public interface BranchSummaryService {
    List<PharmacyBranchResponseDTO> getAllBranchesWithSales();
    PharmacyBranchResponseDTO getBranchSalesById(long branchId);

    /**
     * Retrieves all pharmacy data for a selected date.
     *
     * @param date The selected date.
     * @return List of PharmacyBranchResponseDTO containing pharmacy data for the selected date.
     */
    List<PharmacyBranchResponseDTO> getPharmacyDataByDate(Date date);
}
