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

    @GetMapping(path = "/get-by-id",params = "id")
    public CashierDTO getCashierById(@RequestParam(value = "id") int cashierId) {
        CashierDTO cashierDTO = cashierService.getCashierById(cashierId);
        return cashierDTO;
    }

    @DeleteMapping(path = "/delete-cashier/{id}")
    public String deleteCashier(@PathVariable(value = "id") int cashierId){
        String deleted = cashierService.deleteCashier(cashierId);
        return deleted;
    }
}