package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The type Request order save dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private long employerId;
    private long orderId;
    private long branchId;
    private Date orderDate;
    private Double total;
    private List<RequestOrderDetailsSaveDTO> orderDetails;
    private RequestPaymentDetailsDTO paymentDetails;

}