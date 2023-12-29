package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.CashierPasswordResetDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.CashierRecentPinUpdateDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.CashierUpdateAccountDetailsDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.CashierUpdateDTO;

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
}
