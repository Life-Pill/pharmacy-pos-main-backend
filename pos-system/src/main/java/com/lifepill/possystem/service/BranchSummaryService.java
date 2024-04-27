package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;

import java.util.List;

public interface BranchSummaryService {
    List<PharmacyBranchResponseDTO> getAllBranchesWithSales();
}
