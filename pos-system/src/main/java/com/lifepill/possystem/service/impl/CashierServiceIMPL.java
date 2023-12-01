package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.service.CashierService;
import org.springframework.stereotype.Service;

@Service
public class CashierServiceIMPL implements CashierService {

    @Override
    public String saveCashier(CashierDTO cashierDTO){
        System.out.println("service customer"+ cashierDTO);
        return null;
    }
}
