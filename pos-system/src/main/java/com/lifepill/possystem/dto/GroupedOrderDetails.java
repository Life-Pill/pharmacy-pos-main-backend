package com.lifepill.possystem.dto;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestPaymentDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupedOrderDetails {
    private List<RequestOrderDetailsSaveDTO> orderDetails;
    private RequestPaymentDetailsDTO paymentDetails;
    private int orderCount;
}