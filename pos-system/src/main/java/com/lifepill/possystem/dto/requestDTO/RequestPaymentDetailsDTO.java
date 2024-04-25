package com.lifepill.possystem.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestPaymentDetailsDTO {

    private String paymentMethod;
    private double paymentAmount;
    private Date paymentDate;
    private String paymentNotes;
    private double paymentDiscount;
    private double payedAmount;
}
