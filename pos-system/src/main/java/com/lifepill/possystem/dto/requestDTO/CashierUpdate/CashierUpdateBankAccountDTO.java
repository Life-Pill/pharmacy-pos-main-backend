package com.lifepill.possystem.dto.requestDTO.CashierUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierUpdateBankAccountDTO {
    private long cashierBankDetailsId;
    private String bankName;
    private String bankBranchName;
    private String bankAccountNumber;
    private String cashierDescription;
    private double monthlyPayment;
    private boolean monthlyPaymentStatus;
    private long cashierId;
}
