package com.lifepill.possystem.dto.responseDTO;

import com.lifepill.possystem.dto.GroupedOrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private long employerId;
    private long branchId;
    private Date orderDate;
    private Double total;
    private GroupedOrderDetails groupedOrderDetails;
}