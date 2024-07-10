/*
package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.responseDTO.AllPharmacySummaryResponseDTO;
import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.repo.orderRepository.OrderRepository;
import com.lifepill.possystem.service.impl.BranchSummaryServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

*/
/**
 * Unit tests for BranchSummaryServiceIMPL class.
 *//*

public class BranchSummaryServiceIMPLTest {
    @Mock
    private BranchRepository branchRepository;

    @Mock
    private EmployerRepository employerRepository;
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private BranchSummaryServiceIMPL branchSummaryService;

    */
/**
     * Sets up.
     *//*

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    */
/**
     * Test method for getAllBranchesWithSales().
     *//*

    @Test
    void getAllBranchesWithSales() {
        // Mock data
        List<Order> mockOrders = new ArrayList<>();
        Order mockOrder1 = new Order();
        mockOrder1.setBranchId(3L);
        mockOrder1.setTotal(100.0);
        mockOrders.add(mockOrder1);

        // Mocking repository calls
        when(orderRepository.findAll()).thenReturn(mockOrders);

        // Mocking manager details
        when(employerRepository.findByBranch_BranchIdAndRole(3L, Role.MANAGER))
                .thenReturn(Employer.builder()
                        .employerId(1L)
                        .employerFirstName("Mihiranga")
                        .employerLastName("Jayasooriya")
                        .employerEmail("pmj@gmail.com")
                        .role(Role.MANAGER)
                        .build());

        when(employerRepository.findByBranch_BranchIdAndRole(1L, Role.MANAGER))
                .thenReturn(null);

        // Mocking branch details
        Branch mockBranch = new Branch();
        mockBranch.setBranchId(3);
        mockBranch.setBranchName("Branch C");

        when(branchRepository.existsById(3L)).thenReturn(true);
        when(branchRepository.getReferenceById(3L)).thenReturn(mockBranch);

        // Test method
        List<PharmacyBranchResponseDTO> result = branchSummaryService.getAllBranchesWithSales();

        // Assertions
        assertEquals(1, result.size());
        assertEquals(100.0, result.get(0).getSales());
        assertEquals(1, result.get(0).getOrders());
        assertEquals("Mihiranga", result.get(0).getManager());

        // Verify repository method calls
        verify(orderRepository, times(1))
                .findAll();
        verify(employerRepository, times(1))
                .findByBranch_BranchIdAndRole(anyLong(), eq(Role.MANAGER));
        verify(branchRepository, times(1))
                .existsById(anyLong());
        verify(branchRepository, times(1))
                .getReferenceById(anyLong());
    }

    */
/**
     * Test method for getBranchSalesById() with valid branch ID.
     *//*

    @Test
    void getBranchSalesById_ValidBranchId_ReturnsPharmacyBranchResponseDTO() {
        // Arrange
        long branchId = 1L;
        Branch branch = new Branch();
        branch.setBranchId(branchId);
        branch.setBranchName("Test Branch");

        Employer manager = new Employer();
        manager.setEmployerFirstName("John");
        manager.setRole(Role.MANAGER);

        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchId(branchId);
        branchDTO.setBranchName("Test Branch");

        Order order = new Order();
        order.setBranchId(branchId);
        order.setTotal(100.0);

        when(branchRepository.existsById(branchId)).thenReturn(true);
        when(branchRepository.getReferenceById(branchId)).thenReturn(branch);
        when(employerRepository.findByBranch_BranchIdAndRole(branchId, Role.MANAGER)).thenReturn(manager);
        when(orderRepository.findByBranchId(branchId)).thenReturn(Collections.singletonList(order));

        // Act
        PharmacyBranchResponseDTO responseDTO = branchSummaryService.getBranchSalesById(branchId);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(100.0, responseDTO.getSales());
    }

    */
/**
     * Test get all pharmacy summary.
     *//*

    @Test
    void testGetAllPharmacySummary() {
        // Mock repository responses
        when(orderRepository.getTotalSales()).thenReturn(1000.0);
        when(orderRepository.count()).thenReturn(50L);
        when(employerRepository.count()).thenReturn(20L);
        when(branchRepository.count()).thenReturn(10L);

        // Call service method
        AllPharmacySummaryResponseDTO responseDTO = branchSummaryService.getAllPharmacySummary();

        // Assert results
        assertEquals(1000.0, responseDTO.getTotalSales());
        assertEquals(50, responseDTO.getTotalOrders());
        assertEquals(20, responseDTO.getTotalEmployees());
        assertEquals(10, responseDTO.getTotalBranches());
    }
}*/
