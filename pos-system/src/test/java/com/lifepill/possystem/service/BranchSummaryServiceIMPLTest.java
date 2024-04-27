package com.lifepill.possystem.service;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BranchSummaryServiceIMPL class.
 */
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
}