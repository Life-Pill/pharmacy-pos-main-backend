package com.lifepill.possystem.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSMSDTO {
    private long employerId;
    private long orderId;
    private long branchId;
    private Date orderDate;
    private String customerPhoneNumber;
    private Double total; // TODO Change total to orderTotal
    private List<RequestOrderDetailsSaveDTO> orderDetails;
    private RequestPaymentDetailsDTO paymentDetails;
}
