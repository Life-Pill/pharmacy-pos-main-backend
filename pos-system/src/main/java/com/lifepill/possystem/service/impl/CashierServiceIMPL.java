package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.entity.Cashier;
import com.lifepill.possystem.repo.CashierRepo;
import com.lifepill.possystem.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashierServiceIMPL implements CashierService {

    @Autowired
    private CashierRepo  cashierRepo;

    @Override
    public String saveCashier(CashierDTO cashierDTO){
        Cashier cashier = new Cashier(
                cashierDTO.getCashierId(),
                cashierDTO.getCashierName(),
                cashierDTO.getCashierEmail(),
                cashierDTO.getCashierPhone(),
                cashierDTO.getCashierAddress(),
                cashierDTO.getCashierSalary(),
                cashierDTO.getCashierNic(),
                cashierDTO.isActive()
                );
        cashierRepo.save(cashier);
        return "Saved";
    }
}
