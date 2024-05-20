package com.lifepill.possystem.dto;

import com.lifepill.possystem.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    private Set<Employer> employers;
}
