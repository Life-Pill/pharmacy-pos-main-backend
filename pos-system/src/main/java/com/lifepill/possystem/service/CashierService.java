package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.CashierWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.*;

import java.util.List;

public interface CashierService {

    public String saveCashier(CashierDTO cashierDTO);
    public String saveCashierWithoutImage(CashierWithoutImageDTO cashierWithoutImageDTO);
    String updateCashier(CashierUpdateDTO cashierUpdateDTO);
    CashierDTO getCashierById(long cashierId);
    String deleteCashier(long cashierId);
    List<CashierDTO> getAllCashiers();
    List<CashierDTO> getAllCashiersByActiveState(boolean activeState);

    String updateCashierAccountDetails(CashierUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO);

    String updateCashierPassword(CashierPasswordResetDTO cashierPasswordResetDTO);

    String updateRecentPin(CashierRecentPinUpdateDTO cashierRecentPinUpdateDTO);

    String updateCashierBankAccountDetails(CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO);

    List<CashierUpdateBankAccountDTO> getAllCashiersBankDetails();

    CashierDTO getCashierByIdWithImage(long cashierId);

    byte[] getImageData(long cashierId);
}
