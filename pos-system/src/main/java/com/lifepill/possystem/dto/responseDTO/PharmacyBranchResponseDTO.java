package com.lifepill.possystem.dto.responseDTO;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestPaymentDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * The type Pharmacy branch response dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PharmacyBranchResponseDTO {

    private Double sales;
    private Integer orders;
    private String manager;
    private BranchDTO branchDTO;
}