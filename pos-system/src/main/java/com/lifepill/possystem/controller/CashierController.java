package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerPasswordResetDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerRecentPinUpdateDTO;
import com.lifepill.possystem.service.EmployerService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing cashier-related operations.
 */
@RestController
@RequestMapping("lifepill/v1/cashier")
@AllArgsConstructor
public class CashierController {

    private EmployerService employerService;

    /**
     * Updates the password of a cashier.
     *
     * @param cashierPasswordResetDTO DTO containing details necessary for resetting the password.
     * @return A message indicating the success or failure of the password update operation.
     */
    @PutMapping("/updatePassword")
    @Transactional
    public String updateEmployerPassword(@RequestBody EmployerPasswordResetDTO cashierPasswordResetDTO) {
        return employerService.updateEmployerPassword(cashierPasswordResetDTO);
    }

    /**
     * Updates the recent PIN of a cashier.
     *
     * @param cashierRecentPinUpdateDTO DTO containing details necessary for updating the recent PIN.
     * @return A message indicating the success or failure of the recent PIN update operation.
     */
    @PutMapping("/updateRecentPin")
    @Transactional
    public String updateRecentPin(@RequestBody EmployerRecentPinUpdateDTO cashierRecentPinUpdateDTO) {
        return employerService.updateRecentPin(cashierRecentPinUpdateDTO);
    }
}
