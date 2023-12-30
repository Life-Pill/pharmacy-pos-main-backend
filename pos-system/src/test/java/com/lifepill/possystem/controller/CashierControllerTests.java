package com.lifepill.possystem.controller;//package com.lifepill.possystem.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lifepill.possystem.controller.CashierController;
//import com.lifepill.possystem.dto.CashierDTO;
//import com.lifepill.possystem.dto.requestDTO.CashierUpdate.CashierUpdateDTO;
//import com.lifepill.possystem.service.CashierService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(CashierController.class)
//public class CashierControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private CashierService cashierService;
//
//    @InjectMocks
//    private CashierController cashierController;
//
//
//    @Test
//    public void testGetAllCashiers() throws Exception {
//        List<CashierDTO> cashierDTOList = Arrays.asList(createSampleCashierDTO(), createSampleCashierDTO());
//
//        when(cashierService.getAllCashiers()).thenReturn(cashierDTOList);
//
//        mockMvc.perform(get("/lifepill/v1/cashier/get-all-cashiers"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data[0].cashierId").value(cashierDTOList.get(0).getCashierId()))
//                .andExpect(jsonPath("$.data[1].cashierId").value(cashierDTOList.get(1).getCashierId()));
//    }
//
//    @Test
//    public void testGetCashierById() throws Exception {
//        int cashierId = 1;
//        CashierDTO cashierDTO = createSampleCashierDTO();
//
//        when(cashierService.getCashierById(cashierId)).thenReturn(cashierDTO);
//
//        mockMvc.perform(get("/lifepill/v1/cashier/get-by-id?id=1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.cashierId").value(cashierDTO.getCashierId()))
//                .andExpect(jsonPath("$.cashierNicName").value(cashierDTO.getCashierNicName()));
//    }
//
//    @Test
//    public void testUpdateCashier() throws Exception {
//        CashierUpdateDTO cashierUpdateDTO = createSampleCashierUpdateDTO();
//
//        when(cashierService.updateCashier(any(CashierUpdateDTO.class))).thenReturn("updated");
//
//        mockMvc.perform(put("/lifepill/v1/cashier/update")
//                        .content(asJsonString(cashierUpdateDTO))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string("updated"));
//    }
//
//    @Test
//    public void testDeleteCashier() throws Exception {
//        int cashierId = 1;
//
//        when(cashierService.deleteCashier(cashierId)).thenReturn("deleted");
//
//        mockMvc.perform(delete("/lifepill/v1/cashier/delete-cashier/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("deleted"));
//    }
//
//    // Utility method to convert object to JSON string
//    private static String asJsonString(Object object) {
//        try {
//            return new ObjectMapper().writeValueAsString(object);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // Utility method to create a sample CashierDTO for testing
//    private CashierDTO createSampleCashierDTO() {
//        // Populate with sample data as needed
//        return new CashierDTO();
//    }
//
//    // Utility method to create a sample CashierUpdateDTO for testing
//    private CashierUpdateDTO createSampleCashierUpdateDTO() {
//        // Populate with sample data as needed
//        return new CashierUpdateDTO();
//    }
//}
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

    @Test
    public void testUpdateCashierBankAccountDetails() {
        // Mock data for the DTO
        CashierUpdateBankAccountDTO dto = new CashierUpdateBankAccountDTO();
        dto.setCashierId(1);
        dto.setBankName("Test Bank");
        dto.setBankBranchName("Test Branch");
        dto.setBankAccountNumber("123456789");
        dto.setCashierDescription("Test Description");

        // Mock response message
        String successMessage = "Successfully Update cashier account details";

        // Mock the service method to return the success message
        when(cashierService.updateCashierBankAccountDetails(any())).thenReturn(successMessage);

        // Call the controller method
        String responseEntity = cashierController.updateCashierBankAccountDetails(dto);

        // Verify that the service method was called with the correct argument
        verify(cashierService).updateCashierBankAccountDetails(dto);

        // Verify the response entity
        assertEquals(successMessage, responseEntity);

    }
}
