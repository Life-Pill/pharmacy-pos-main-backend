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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BranchSummaryController class.
 */
public class BranchSummaryControllerTest {

    @Mock
    private BranchSummaryService branchSummaryService;

    @InjectMocks
    private BranchSummaryController branchSummaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
}
