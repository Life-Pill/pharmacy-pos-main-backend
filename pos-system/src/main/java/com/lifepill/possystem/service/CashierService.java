package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdateDTO;

import java.util.List;

public interface CashierService {

    public String saveCashier(CashierDTO cashierDTO);
    String updateCashier(CashierUpdateDTO cashierUpdateDTO);
    CashierDTO getCashierById(int cashierId);
    String deleteCashier(int cashierId);
    List<CashierDTO> getAllCashiers();
    List<CashierDTO> getAllCashiersByActiveState(boolean activeState);
}
