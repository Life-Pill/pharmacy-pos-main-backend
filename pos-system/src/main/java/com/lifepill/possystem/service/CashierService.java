package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.*;

import java.util.List;

public interface CashierService {

    public String saveCashier(CashierDTO cashierDTO);
    String updateCashier(CashierUpdateDTO cashierUpdateDTO);
    CashierDTO getCashierById(int cashierId);
    String deleteCashier(int cashierId);
    List<CashierDTO> getAllCashiers();
    List<CashierDTO> getAllCashiersByActiveState(boolean activeState);

    String updateCashierAccountDetails(CashierUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO);

    String updateCashierPassword(CashierPasswordResetDTO cashierPasswordResetDTO);

    String updateRecentPin(CashierRecentPinUpdateDTO cashierRecentPinUpdateDTO);

    String updateCashierBankAccountDetails(CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO);

    List<CashierUpdateBankAccountDTO> getAllCashiersBankDetails();

    CashierDTO getCashierByIdWithImage(int cashierId);

    byte[] getImageData(int cashierId);
}
