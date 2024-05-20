package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.service.BranchSummaryService;
import com.lifepill.possystem.util.StandardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BranchSummaryController class.
 */
public class BranchSummaryControllerTest {

    @Mock
    private BranchSummaryService branchSummaryService;

    @InjectMocks
    private BranchSummaryController branchSummaryController;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(branchSummaryController).build();
    }

    /**
     * Test method for getAllBranchesWithSales().
     */
    @Test
    void getAllBranchesWithSales() {
        // Mock data
        List<PharmacyBranchResponseDTO> mockResponseDTOList = new ArrayList<>();
        PharmacyBranchResponseDTO mockResponseDTO = new PharmacyBranchResponseDTO();
        mockResponseDTO.setSales(100.0);
        mockResponseDTO.setOrders(5);
        mockResponseDTO.setManager("John Doe");
        mockResponseDTOList.add(mockResponseDTO);

        // Mock service method
        when(branchSummaryService.getAllBranchesWithSales()).thenReturn(mockResponseDTOList);

        // Call controller method
        ResponseEntity<StandardResponse> responseEntity = branchSummaryController.getAllBranchesWithSales();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((List <PharmacyBranchResponseDTO>) responseEntity
                .getBody().getData()).size());
        assertEquals("SUCCESS", responseEntity
                .getBody().getMessage());
        assertEquals(1, (
                (List <PharmacyBranchResponseDTO>) responseEntity.getBody().getData()).size()
        );

        PharmacyBranchResponseDTO responseData = (
                (List<PharmacyBranchResponseDTO>) responseEntity.getBody().getData()).get(0);
        assertEquals(100.0, responseData.getSales());
        assertEquals(5, responseData.getOrders());
        assertEquals("John Doe", responseData.getManager());

        // Verify service method call
        verify(branchSummaryService, times(1)).getAllBranchesWithSales();
    }

    /**
     * Test method for getBranchSalesById() with valid branch ID.
     */
    @Test
    void getBranchSalesById_ValidBranchId_ReturnsResponseEntityWithStatusOK() {
        // Arrange
        long branchId = 1L;
        PharmacyBranchResponseDTO responseDTO = new PharmacyBranchResponseDTO();
        responseDTO.setSales(100.0);

        when(branchSummaryService.getBranchSalesById(branchId)).thenReturn(responseDTO);

        // Act
        ResponseEntity<StandardResponse> responseEntity = branchSummaryController.getBranchSalesById(branchId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(201, responseEntity.getBody().getCode());
        assertEquals("SUCCESS", responseEntity.getBody().getMessage());
    }
}