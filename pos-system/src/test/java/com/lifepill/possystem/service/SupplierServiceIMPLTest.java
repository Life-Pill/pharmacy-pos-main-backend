/*
package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.entity.Supplier;
import com.lifepill.possystem.entity.SupplierCompany;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.supplierRepository.SupplierCompanyRepository;
import com.lifepill.possystem.repo.supplierRepository.SupplierRepository;
import com.lifepill.possystem.service.impl.SupplierServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

*/
/**
 * The type Supplier service impl test.
 *//*

class SupplierServiceIMPLTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierCompanyRepository supplierCompanyRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SupplierServiceIMPL supplierService;

    private List<Supplier> suppliers;
    private List<SupplierDTO> supplierDTOs;

    */
/**
     * Sets up.
     *//*

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        suppliers = new ArrayList<>();
        supplierDTOs = new ArrayList<>();
        setupMockData();
    }

    private void setupMockData() {
        // Setup mock Supplier entities
        Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(1L);
        suppliers.add(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setSupplierId(2L);
        suppliers.add(supplier2);

        // Setup mock SupplierDTO objects
        SupplierDTO supplierDTO1 = new SupplierDTO();
        supplierDTO1.setSupplierId(1L);
        supplierDTOs.add(supplierDTO1);

        SupplierDTO supplierDTO2 = new SupplierDTO();
        supplierDTO2.setSupplierId(2L);
        supplierDTOs.add(supplierDTO2);
    }

    */
/**
     * Test get all suppliers successful.
     *//*

    @Test
    void testGetAllSuppliersSuccessful() {
        when(supplierRepository.findAll()).thenReturn(suppliers);
        when(modelMapper.map(any(Supplier.class), eq(SupplierDTO.class))).thenReturn(supplierDTOs.get(0), supplierDTOs.get(1));

        List<SupplierDTO> result = supplierService.getAllSuppliers();

        assertEquals(2, result.size());
        verify(supplierRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(Supplier.class), eq(SupplierDTO.class));
    }

    */
/**
     * Test get all suppliers not found.
     *//*

    @Test
    void testGetAllSuppliersNotFound() {
        when(supplierRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> supplierService.getAllSuppliers());
        verify(supplierRepository, times(1)).findAll();
    }

    */
/**
     * Test save supplier successful.
     *//*

    @Test
    void testSaveSupplierSuccessful() {
        SupplierDTO supplierDTO = new SupplierDTO();
        Supplier supplier = new Supplier();
        SupplierCompany supplierCompany = new SupplierCompany();
        supplierCompany.setCompanyId(1L);

        when(supplierRepository.existsById(anyLong())).thenReturn(false);
        when(supplierRepository.existsAllBySupplierEmail(anyString())).thenReturn(false);
        when(supplierCompanyRepository.findById(anyLong())).thenReturn(Optional.of(supplierCompany));
        when(modelMapper.map(supplierDTO, Supplier.class)).thenReturn(supplier);
        when(modelMapper.map(supplier, SupplierDTO.class)).thenReturn(supplierDTO);

        SupplierDTO result = supplierService.saveSupplier(supplierDTO);

        assertNotNull(result);
        verify(supplierRepository, times(1)).save(any(Supplier.class));
        verify(modelMapper, times(1)).map(supplierDTO, Supplier.class);
        verify(modelMapper, times(1)).map(supplier, SupplierDTO.class);
    }

    */
/**
     * Test save supplier duplicate id.
     *//*

    @Test
    void testSaveSupplierDuplicateId() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(1L);

        when(supplierRepository.existsById(anyLong())).thenReturn(true);

        assertThrows(EntityDuplicationException.class, () -> supplierService.saveSupplier(supplierDTO));
        verify(supplierRepository, times(1)).existsById(anyLong());
        verify(supplierRepository, never()).existsAllBySupplierEmail(anyString());
        verify(supplierCompanyRepository, never()).findById(anyLong());
    }

    */
/**
     * Test save supplier duplicate email.
     *//*

    @Test
    void testSaveSupplierDuplicateEmail() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierEmail("test@example.com");

        when(supplierRepository.existsById(anyLong())).thenReturn(false);
        when(supplierRepository.existsAllBySupplierEmail(anyString())).thenReturn(true);

        assertThrows(EntityDuplicationException.class, () -> supplierService.saveSupplier(supplierDTO));
        verify(supplierRepository, times(1)).existsById(anyLong());
        verify(supplierRepository, times(1)).existsAllBySupplierEmail(anyString());
        verify(supplierCompanyRepository, never()).findById(anyLong());
    }

    */
/**
     * Test save supplier company not found.
     *//*

    @Test
    void testSaveSupplierCompanyNotFound() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setCompanyId(1L);

        when(supplierRepository.existsById(anyLong())).thenReturn(false);
        when(supplierRepository.existsAllBySupplierEmail(anyString())).thenReturn(false);
        when(supplierCompanyRepository.findById(anyLong())).thenReturn(Optional.empty());

       assertThrows(NotFoundException.class, () -> supplierService.saveSupplier(supplierDTO));
    }

    */
/**
     * Test update supplier by id successful.
     *//*

    @Test
    void testUpdateSupplierByIdSuccessful() {
        long supplierId = 1L;
        Supplier existingSupplier = suppliers.get(0);
        SupplierDTO updatedSupplierDTO = new SupplierDTO();

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(existingSupplier));
        when(supplierRepository.save(any(Supplier.class))).thenReturn(existingSupplier);
        when(modelMapper.map(existingSupplier, SupplierDTO.class)).thenReturn(updatedSupplierDTO);

        SupplierDTO result = supplierService.updateSupplierById(supplierId, updatedSupplierDTO);

        assertNotNull(result);
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(supplierRepository, times(1)).save(existingSupplier);
        verify(modelMapper, times(1)).map(existingSupplier, SupplierDTO.class);
    }

    */
/**
     * Test update supplier by id not found.
     *//*

    @Test
    void testUpdateSupplierByIdNotFound() {
        long supplierId = 1L;
        SupplierDTO updatedSupplierDTO = new SupplierDTO();

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.updateSupplierById(supplierId, updatedSupplierDTO));
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(supplierRepository, never()).save(any(Supplier.class));
        verify(modelMapper, never()).map(any(Supplier.class), eq(SupplierDTO.class));
    }

    */
/**
     * Test delete supplier by id successful.
     *//*

    @Test
    void testDeleteSupplierByIdSuccessful() {
        long supplierId = 1L;
        Supplier existingSupplier = suppliers.get(0);

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(existingSupplier));

        supplierService.deleteSupplierById(supplierId);

        verify(supplierRepository, times(1)).findById(supplierId);
    }

    */
/**
     * Test delete supplier by id not found.
     *//*

    @Test
    void testDeleteSupplierByIdNotFound() {
        long supplierId = 1L;

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.deleteSupplierById(supplierId));
        verify(supplierRepository, times(1)).findById(supplierId);
    }

    */
/**
     * Test get supplier by id successful.
     *//*

    @Test
    void testGetSupplierByIdSuccessful() {
        long supplierId = 1L;
        Supplier existingSupplier = suppliers.get(0);
        SupplierDTO supplierDTO = supplierDTOs.get(0);

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(existingSupplier));
        when(modelMapper.map(existingSupplier, SupplierDTO.class)).thenReturn(supplierDTO);

        SupplierDTO result = supplierService.getSupplierById(supplierId);

        assertNotNull(result);
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(modelMapper, times(1)).map(existingSupplier, SupplierDTO.class);
    }

    */
/**
     * Test get supplier by id not found.
     *//*

    @Test
    void testGetSupplierByIdNotFound() {
        long supplierId = 1L;

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.getSupplierById(supplierId));
        verify(supplierRepository, times(1)).findById(supplierId);
        verify(modelMapper, never()).map(any(Supplier.class), eq(SupplierDTO.class));
    }

}
*/
