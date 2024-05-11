package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerPasswordResetDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerRecentPinUpdateDTO;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.service.EmployerService;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


    @Test
    public void testUpdateEmployerPassword_Success() {
        // Arrange
        EmployerPasswordResetDTO resetDTO = new EmployerPasswordResetDTO();
        resetDTO.setEmployerId(1L);
        resetDTO.setEmployerPassword("newPassword");

        Mockito.when(employerService.updateEmployerPassword(Mockito.any(EmployerPasswordResetDTO.class)))
                .thenReturn("Password updated successfully");

        // Act
        String result = cashierController.updateEmployerPassword(resetDTO);

        // Assert
        Assert.assertEquals("Password updated successfully", result);
    }

    @Test
    public void testUpdateRecentPin_Success() {
        // Arrange
        EmployerRecentPinUpdateDTO pinUpdateDTO = new EmployerRecentPinUpdateDTO();
        pinUpdateDTO.setEmployerId(1L);
        pinUpdateDTO.setPin(1234);

        Mockito.when(employerService.updateRecentPin(Mockito.any(EmployerRecentPinUpdateDTO.class)))
                .thenReturn("Recent PIN updated successfully");

        // Act
        String result = cashierController.updateRecentPin(pinUpdateDTO);

        // Assert
        Assert.assertEquals("Recent PIN updated successfully", result);
    }

    @Test
    public void testUpdateEmployerPassword_Failure() {
        // Arrange
        EmployerPasswordResetDTO resetDTO = new EmployerPasswordResetDTO();
        resetDTO.setEmployerId(1L);
        resetDTO.setEmployerPassword("newPassword");

        Mockito.when(employerService.updateEmployerPassword(Mockito.any(EmployerPasswordResetDTO.class)))
                .thenReturn("Failed to update password");

        // Act
        String result = cashierController.updateEmployerPassword(resetDTO);

        // Assert
        Assert.assertEquals("Failed to update password", result);
    }


    @Test
    public void testUpdateRecentPin_ValidDTO() {
        // Test for valid DTO during recent PIN update

        // Arrange
        EmployerRecentPinUpdateDTO pinUpdateDTO = new EmployerRecentPinUpdateDTO();
        pinUpdateDTO.setEmployerId(1L);
        pinUpdateDTO.setPin(1234);

        Mockito.when(employerService.updateRecentPin(Mockito.any(EmployerRecentPinUpdateDTO.class)))
                .thenReturn("Recent PIN updated successfully");

        // Act
        String result = cashierController.updateRecentPin(pinUpdateDTO);

        // Assert
        Assert.assertEquals("Recent PIN updated successfully", result);

    }

    @Test
    public void testUpdateEmployerPassword_EmptyMessage() {
        // Test for empty message after password update

        // Arrange
        EmployerPasswordResetDTO resetDTO = new EmployerPasswordResetDTO();
        resetDTO.setEmployerId(1L);
        resetDTO.setEmployerPassword("newPassword");

        Mockito.when(employerService.updateEmployerPassword(Mockito.any(EmployerPasswordResetDTO.class)))
                .thenReturn("");

        // Act
        String result = cashierController.updateEmployerPassword(resetDTO);

        // Assert
        Assert.assertEquals("", result);

    }

    @Test
    public void testUpdateRecentPin_EmptyMessage() {
        // Test for empty message after recent PIN update

        // Arrange
        EmployerRecentPinUpdateDTO pinUpdateDTO = new EmployerRecentPinUpdateDTO();
        pinUpdateDTO.setEmployerId(1L);
        pinUpdateDTO.setPin(1234);

            Mockito.when(employerService.updateRecentPin(Mockito.any(EmployerRecentPinUpdateDTO.class)))
                    .thenReturn("");

            // Act
            String result = cashierController.updateRecentPin(pinUpdateDTO);

            // Assert
            Assert.assertEquals("", result);
    }

    @Test
    public void testUpdateEmployerPassword_NonEmptyMessage() {
        // Test for non-empty message after password update

        // Arrange
        EmployerPasswordResetDTO resetDTO = new EmployerPasswordResetDTO();
        resetDTO.setEmployerId(1L);
        resetDTO.setEmployerPassword("newPassword");

        Mockito.when(employerService.updateEmployerPassword(Mockito.any(EmployerPasswordResetDTO.class)))
                .thenReturn("Password updated successfully");

        // Act
        String result = cashierController.updateEmployerPassword(resetDTO);

        // Assert
        Assert.assertEquals("Password updated successfully", result);

    }

    @Test
    public void testUpdateRecentPin_NonEmptyMessage() {
        // Test for non-empty message after recent PIN update

        // Arrange
        EmployerRecentPinUpdateDTO pinUpdateDTO = new EmployerRecentPinUpdateDTO();
        pinUpdateDTO.setEmployerId(1L);
        pinUpdateDTO.setPin(1234);

        Mockito.when(employerService.updateRecentPin(Mockito.any(EmployerRecentPinUpdateDTO.class)))
                .thenReturn("Recent PIN updated successfully");

        // Act
        String result = cashierController.updateRecentPin(pinUpdateDTO);

        // Assert
        Assert.assertEquals("Recent PIN updated successfully", result);

    }

    @Test
    public void testUpdateRecentPin_Failure_InvalidEmployerId() {
        // Test failure with invalid employer ID during recent PIN update

        // Arrange
        EmployerRecentPinUpdateDTO pinUpdateDTO = new EmployerRecentPinUpdateDTO();
        pinUpdateDTO.setEmployerId(1L);
        pinUpdateDTO.setPin(1234);

        Mockito.when(employerService.updateRecentPin(Mockito.any(EmployerRecentPinUpdateDTO.class)))
                .thenThrow(new NotFoundException("Employer not found"));

        // Act and Assert
        assertThrows(NotFoundException.class, () -> cashierController.updateRecentPin(pinUpdateDTO));

    }

    @Test
    public void testUpdateEmployerPassword_Failure_InvalidEmployerId() {
        // Test failure with invalid employer ID during password update

        // Arrange
        EmployerPasswordResetDTO resetDTO = new EmployerPasswordResetDTO();
        resetDTO.setEmployerId(1L);
        resetDTO.setEmployerPassword("newPassword");

        Mockito.when(employerService.updateEmployerPassword(Mockito.any(EmployerPasswordResetDTO.class))
        ).thenThrow(new NotFoundException("Employer not found"));

        // Act and Assert
        assertThrows(NotFoundException.class, () -> cashierController.updateEmployerPassword(resetDTO));
    }

    @Test
    public void testUpdateEmployerPassword_InvalidEmployerId() {
        // Test for invalid employer ID during password update

        // Arrange
        EmployerPasswordResetDTO resetDTO = new EmployerPasswordResetDTO();
        resetDTO.setEmployerId(1L);
        resetDTO.setEmployerPassword("newPassword");

        Mockito.when(employerService.updateEmployerPassword(Mockito.any(EmployerPasswordResetDTO.class))
        ).thenThrow(new NotFoundException("Employer not found"));

        // Act and Assert
        assertThrows(NotFoundException.class, () -> cashierController.updateEmployerPassword(resetDTO));
    }

    @Test
    public void testUpdateRecentPin_InvalidEmployerId() {
        // Test for invalid employer ID during recent PIN update

        // Arrange
        EmployerRecentPinUpdateDTO pinUpdateDTO = new EmployerRecentPinUpdateDTO();
        pinUpdateDTO.setEmployerId(1L);
        pinUpdateDTO.setPin(1234);

        Mockito.when(employerService.updateRecentPin(Mockito.any(EmployerRecentPinUpdateDTO.class))
        ).thenThrow(new NotFoundException("Employer not found"));

        // Act and Assert
        assertThrows(NotFoundException.class, () -> cashierController.updateRecentPin(pinUpdateDTO));
    }
}
