package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdateDTO;

public interface CashierService {

    public String saveCashier(CashierDTO cashierDTO);
    String updateCashier(CashierUpdateDTO cashierUpdateDTO);
    CashierDTO getCashierById(int cashierId);
    String deleteCashier(int cashierId);
}
