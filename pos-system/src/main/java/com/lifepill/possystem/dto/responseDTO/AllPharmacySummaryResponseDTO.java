package com.lifepill.possystem.dto.responseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllPharmacySummaryResponseDTO {
    private Double totalSales;
    private Integer totalOrders;
    private Integer totalEmployees;
    private Integer totalBranches;
}
