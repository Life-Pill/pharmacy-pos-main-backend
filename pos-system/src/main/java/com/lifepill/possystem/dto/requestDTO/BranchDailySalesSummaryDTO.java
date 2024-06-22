package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.dto.responseDTO.DailySalesSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDailySalesSummaryDTO {
    private long branchId;
    private List<DailySalesSummaryDTO> dailySalesSummary;

}
