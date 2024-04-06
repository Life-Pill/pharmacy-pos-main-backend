package com.lifepill.possystem.dto.requestDTO.EmployerUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerUpdateBankAccountDTO {
    private long employerBankDetailsId;
    private String bankName;
    private String bankBranchName;
    private String bankAccountNumber;
    private String employerDescription;
    private double monthlyPayment;
    private boolean monthlyPaymentStatus;
    private long employerId;
}
