package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.service.SupplierService;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * The type Supplier controller test.
 */
class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    private List<SupplierDTO> supplierDTOs;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supplierDTOs = new ArrayList<>();
        setupMockData();
    }

    private void setupMockData() {
        SupplierDTO supplierDTO1 = new SupplierDTO();
        supplierDTO1.setSupplierId(1L);
        supplierDTOs.add(supplierDTO1);

        SupplierDTO supplierDTO2 = new SupplierDTO();
        supplierDTO2.setSupplierId(2L);
        supplierDTOs.add(supplierDTO2);
    }

    /**
     * Test get all suppliers successful.
     */
    @Test
    void testGetAllSuppliersSuccessful() {
        when(supplierService.getAllSuppliers()).thenReturn(supplierDTOs);

        ResponseEntity<List<SupplierDTO>> response = supplierController.getAllSuppliers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(supplierService, times(1)).getAllSuppliers();
    }

    /**
     * Test save supplier successful.
     */
    @Test
    void testSaveSupplierSuccessful() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(1L);

        when(supplierService.saveSupplier(supplierDTO)).thenReturn(supplierDTO);

        ResponseEntity<SupplierDTO> response = supplierController.saveSupplier(supplierDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(supplierDTO, response.getBody());
        verify(supplierService, times(1)).saveSupplier(supplierDTO);
    }

    /**
     * Test get all suppliers not found.
     */
    @Test
    void testGetAllSuppliersNotFound() {
        when(supplierService.getAllSuppliers()).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> supplierController.getAllSuppliers());
        verify(supplierService, times(1)).getAllSuppliers();
    }


    /**
     * Test save supplier duplicate exception.
     */
    @Test
    void testSaveSupplierDuplicateException() {
        SupplierDTO supplierDTO = new SupplierDTO();

        when(supplierService.saveSupplier(supplierDTO)).thenThrow(EntityDuplicationException.class);

        assertThrows(EntityDuplicationException.class, () -> supplierController.saveSupplier(supplierDTO));
        verify(supplierService, times(1)).saveSupplier(supplierDTO);
    }

    /**
     * Test update supplier by id successful.
     */
    @Test
    void testUpdateSupplierByIdSuccessful() {
        long supplierId = 1L;
        SupplierDTO updatedSupplierDTO = new SupplierDTO();
        updatedSupplierDTO.setSupplierId(supplierId);

        when(supplierService.updateSupplierById(supplierId, updatedSupplierDTO)).thenReturn(updatedSupplierDTO);

        ResponseEntity<SupplierDTO> response = supplierController.updateSupplierById(supplierId, updatedSupplierDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSupplierDTO, response.getBody());
        verify(supplierService, times(1)).updateSupplierById(supplierId, updatedSupplierDTO);
    }

    /**
     * Test update supplier by id not found.
     */
    @Test
    void testUpdateSupplierByIdNotFound() {
        long supplierId = 1L;
        SupplierDTO updatedSupplierDTO = new SupplierDTO();

        when(supplierService.updateSupplierById(supplierId, updatedSupplierDTO)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> supplierController.updateSupplierById(supplierId, updatedSupplierDTO));
        verify(supplierService, times(1)).updateSupplierById(supplierId, updatedSupplierDTO);
    }

    /**
     * Test get supplier by id successful.
     */
    @Test
    void testGetSupplierByIdSuccessful() {
        long supplierId = 1L;
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplierId);

        when(supplierService.getSupplierById(supplierId)).thenReturn(supplierDTO);

        ResponseEntity<SupplierDTO> response = supplierController.getSupplierById(supplierId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(supplierDTO, response.getBody());
        verify(supplierService, times(1)).getSupplierById(supplierId);
    }

    /**
     * Test get supplier by id not found.
     */
    @Test
    void testGetSupplierByIdNotFound() {
        long supplierId = 1L;

        when(supplierService.getSupplierById(supplierId)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> supplierController.getSupplierById(supplierId));
        verify(supplierService, times(1)).getSupplierById(supplierId);
    }

    /**
     * Test delete supplier by id successful.
     */
    @Test
    void testDeleteSupplierByIdSuccessful() {
        long supplierId = 1L;

        ResponseEntity<Void> response = supplierController.deleteSupplierById(supplierId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(supplierService, times(1)).deleteSupplierById(supplierId);
    }

    /**
     * Test delete supplier by id not found.
     */
    @Test
    void testDeleteSupplierByIdNotFound() {
        long supplierId = 1L;

        doThrow(NotFoundException.class).when(supplierService).deleteSupplierById(supplierId);

        assertThrows(NotFoundException.class, () -> supplierController.deleteSupplierById(supplierId));
        verify(supplierService, times(1)).deleteSupplierById(supplierId);
    }

}