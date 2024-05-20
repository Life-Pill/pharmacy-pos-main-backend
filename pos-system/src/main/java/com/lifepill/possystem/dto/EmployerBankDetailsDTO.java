package com.lifepill.possystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerBankDetailsDTO {

    private long employerBankDetailsId;
    private String bankName;
    private String bankBranchName;
    private String bankAccountNumber;
    private String employerDescription;
    private double monthlyPayment;
    private Boolean monthlyPaymentStatus;
    private long employerId;
//    private Set<Employer> employers;
}
