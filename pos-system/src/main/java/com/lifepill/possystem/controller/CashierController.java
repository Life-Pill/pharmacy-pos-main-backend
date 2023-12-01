package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdateDTO;
import com.lifepill.possystem.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lifepill/v1/cashier")
@CrossOrigin
public class CashierController {

    @Autowired
    private CashierService cashierService;
    @PostMapping("/save")
    public String saveCustomer(@RequestBody CashierDTO cashierDTO){

        cashierService.saveCashier(cashierDTO);
        return "saved";
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CashierUpdateDTO cashierUpdateDTO){
        String message = cashierService.updateCashier(cashierUpdateDTO);
        return message;
    }
}