package com.lifepill.possystem.controller;//package com.lifepill.possystem.controller;

import com.lifepill.possystem.controller.CashierController;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.CashierUpdateBankAccountDTO;
import com.lifepill.possystem.service.CashierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CashierControllerTests {

    @Mock
    private CashierService cashierService;

    @InjectMocks
    private CashierController cashierController;

//    @Test
//    public void testUpdateCashierBankAccountDetails() {
//        // Mock data for the DTO
//        CashierUpdateBankAccountDTO dto = new CashierUpdateBankAccountDTO();
//        dto.setCashierId(1);
//        dto.setBankName("Test Bank");
//        dto.setBankBranchName("Test Branch");
//        dto.setBankAccountNumber("123456789");
//        dto.setCashierDescription("Test Description");
//
//        // Mock response message
//        String successMessage = "Successfully Update cashier account details";
//
//        // Mock the service method to return the success message
//        when(cashierService.updateCashierBankAccountDetails(any())).thenReturn(successMessage);
//
//        // Call the controller method
//        String responseEntity = cashierController.updateCashierBankAccountDetails(dto);
//
//        // Verify that the service method was called with the correct argument
//        verify(cashierService).updateCashierBankAccountDetails(dto);
//
//        // Verify the response entity
//        assertEquals(successMessage, responseEntity);
//
//    }
}
