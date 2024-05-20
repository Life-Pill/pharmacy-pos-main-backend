package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.service.EmployerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the BranchManagerController class.
 */
@RunWith(MockitoJUnitRunner.class)
public class BranchManagerControllerTest {

    @InjectMocks
    private BranchManagerController branchManagerController;

    @Mock
    private EmployerService employerService;

    /**
     * Test for getting all cashiers by branch ID when there are cashiers.
     */
    @Test
    public void testGetAllCashiersByBranchId() {
        // Mock data
        int branchId = 1;
        List<EmployerDTO> mockEmployers = Arrays.asList(
                new EmployerDTO(),
                new EmployerDTO()
        );

        // Mock employerService.getAllEmployerByBranchId to return mockEmployers
        Mockito.when(employerService.getAllEmployerByBranchId(branchId)).thenReturn(mockEmployers);

        // Call the controller method
        ResponseEntity<List<EmployerDTO>> responseEntity = branchManagerController.getAllCashiersByBranchId(branchId);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockEmployers, responseEntity.getBody());
    }

    /**
     * Test for getting all cashiers by branch ID when there are no cashiers.
     */
    @Test
    public void testGetAllCashiersByBranchIdEmpty() {
        // Mock data
        int branchId = 1;
        List<EmployerDTO> mockEmployers = Arrays.asList();

        // Mock employerService.getAllEmployerByBranchId to return mockEmployers
        Mockito.when(employerService.getAllEmployerByBranchId(branchId)).thenReturn(mockEmployers);

        // Call the controller method
        ResponseEntity<List<EmployerDTO>> responseEntity = branchManagerController.getAllCashiersByBranchId(branchId);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockEmployers, responseEntity.getBody());
    }

    /**
     * Test for getting all cashiers by branch ID when the result is null.
     */
    @Test
    public void testGetAllCashiersByBranchIdNull() {
        // Mock data
        int branchId = 1;

        // Mock employerService.getAllEmployerByBranchId to return null
        Mockito.when(employerService.getAllEmployerByBranchId(branchId)).thenReturn(null);

        // Call the controller method
        ResponseEntity<List<EmployerDTO>> responseEntity = branchManagerController.getAllCashiersByBranchId(branchId);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}
