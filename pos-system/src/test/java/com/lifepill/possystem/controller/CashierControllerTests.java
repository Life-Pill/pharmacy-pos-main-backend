package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerPasswordResetDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerRecentPinUpdateDTO;
import com.lifepill.possystem.service.EmployerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CashierControllerTests {

    @InjectMocks
    private CashierController cashierController;

    @Mock
    private EmployerService employerService;

    @Test
    public void testUpdateEmployerPassword() {
        // Mock data
        EmployerPasswordResetDTO passwordResetDTO = new EmployerPasswordResetDTO();
        passwordResetDTO.setEmployerId(1L);
        passwordResetDTO.setEmployerPassword("newPassword");

        // Mock service behavior
        Mockito.when(employerService.updateEmployerPassword(passwordResetDTO)).thenReturn("Password updated successfully");

        // Call the controller method
        String response = cashierController.updateEmployerPassword(passwordResetDTO);

        // Verify the response
        assertEquals("Password updated successfully", response);
    }

    @Test
    public void testUpdateRecentPin() {
        // Mock data
        EmployerRecentPinUpdateDTO pinUpdateDTO = new EmployerRecentPinUpdateDTO();
        pinUpdateDTO.setEmployerId(1L);
        pinUpdateDTO.setPin(1234);

        // Mock service behavior
        Mockito.when(employerService.updateRecentPin(pinUpdateDTO)).thenReturn("Recent PIN updated successfully");

        // Call the controller method
        String response = cashierController.updateRecentPin(pinUpdateDTO);

        // Verify the response
        assertEquals("Recent PIN updated successfully", response);
    }

}
