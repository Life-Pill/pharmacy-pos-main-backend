package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdateDTO;
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

    @Override
    public String updateCashier(CashierUpdateDTO cashierUpdateDTO) {
        if (cashierRepo.existsById(cashierUpdateDTO.getCashierId())){
            Cashier cashier = cashierRepo.getReferenceById(cashierUpdateDTO.getCashierId());

            cashier.setCashierName(cashierUpdateDTO.getCashierName());
            cashier.setCashierEmail(cashierUpdateDTO.getCashierEmail());
            cashier.setCashierPhone(cashierUpdateDTO.getCashierPhone());
            cashier.setCashierSalary(cashierUpdateDTO.getCashierSalary());

            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "UPDATED CUSTOMER";
        }else {
            throw new RuntimeException("no data found for that id");
        }

    }


}
