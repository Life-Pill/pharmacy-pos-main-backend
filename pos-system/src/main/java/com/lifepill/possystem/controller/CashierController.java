package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerPasswordResetDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerRecentPinUpdateDTO;
import com.lifepill.possystem.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lifepill/v1/cashier")
public class CashierController {

    @Autowired
    private EmployerService employerService;
/**/
    @PutMapping("/updatePassword")
    @Transactional
    public String updateEmployerPassword(@RequestBody EmployerPasswordResetDTO cashierPasswordResetDTO) {
        String message = employerService.updateEmployerPassword(cashierPasswordResetDTO);
        return message;
    }

    @PutMapping("/updateRecentPin")
    @Transactional
    public String updateRecentPin(@RequestBody EmployerRecentPinUpdateDTO cashierRecentPinUpdateDTO) {
        String message = employerService.updateRecentPin(cashierRecentPinUpdateDTO);
        return message;
    }
}
