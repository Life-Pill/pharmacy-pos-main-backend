package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.SupplierCompanyDTO;
import com.lifepill.possystem.entity.SupplierCompany;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.supplierRepository.SupplierCompanyRepository;
import com.lifepill.possystem.service.impl.SupplierCompanyServiceIMPL;
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

class SupplierCompanyServiceIMPLTest {

    @Mock
    private SupplierCompanyRepository supplierCompanyRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SupplierCompanyServiceIMPL supplierCompanyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSupplierCompanies() {
        List<SupplierCompany> companies = new ArrayList<>();
        companies.add(new SupplierCompany());
        companies.add(new SupplierCompany());

        List<SupplierCompanyDTO> dtos = new ArrayList<>();
        dtos.add(new SupplierCompanyDTO());
        dtos.add(new SupplierCompanyDTO());

        when(supplierCompanyRepository.findAll()).thenReturn(companies);
        when(modelMapper.map(any(SupplierCompany.class), eq(SupplierCompanyDTO.class))).thenReturn(dtos.get(0), dtos.get(1));

        List<SupplierCompanyDTO> result = supplierCompanyService.getAllSupplierCompanies();

        assertEquals(2, result.size());
        verify(supplierCompanyRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(SupplierCompany.class), eq(SupplierCompanyDTO.class));
    }

    @Test
    void testSaveSupplierCompany() {
        SupplierCompanyDTO dto = new SupplierCompanyDTO();
        SupplierCompany company = new SupplierCompany();
        SupplierCompany savedCompany = new SupplierCompany();

        when(modelMapper.map(dto, SupplierCompany.class)).thenReturn(company);
        when(supplierCompanyRepository.save(company)).thenReturn(savedCompany);
        when(modelMapper.map(savedCompany, SupplierCompanyDTO.class)).thenReturn(dto);

        SupplierCompanyDTO result = supplierCompanyService.saveSupplierCompany(dto);

        assertEquals(dto, result);
        verify(modelMapper, times(1)).map(dto, SupplierCompany.class);
        verify(supplierCompanyRepository, times(1)).save(company);
        verify(modelMapper, times(1)).map(savedCompany, SupplierCompanyDTO.class);
    }

    @Test
    void testUpdateSupplierCompanyById() {
        long id = 1L;
        SupplierCompany existingCompany = new SupplierCompany();
        SupplierCompanyDTO updatedDto = new SupplierCompanyDTO();
        SupplierCompany updatedCompany = new SupplierCompany();
        SupplierCompanyDTO expectedDto = new SupplierCompanyDTO();

        when(supplierCompanyRepository.findById(id)).thenReturn(Optional.of(existingCompany));
        when(modelMapper.map(updatedDto, SupplierCompany.class)).thenReturn(updatedCompany);
        when(supplierCompanyRepository.save(updatedCompany)).thenReturn(updatedCompany);
        when(modelMapper.map(updatedCompany, SupplierCompanyDTO.class)).thenReturn(expectedDto);

        SupplierCompanyDTO result = supplierCompanyService.updateSupplierCompanyById(id, updatedDto);

        verify(supplierCompanyRepository, times(1)).findById(id);


    }

    @Test
    void testDeleteSupplierCompanyById() {
        long id = 1L;
        SupplierCompany company = new SupplierCompany();

        when(supplierCompanyRepository.findById(id)).thenReturn(Optional.of(company));

        supplierCompanyService.deleteSupplierCompanyById(id);

        verify(supplierCompanyRepository, times(1)).findById(id);
        verify(supplierCompanyRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetSupplierCompanyById() {
        long id = 1L;
        SupplierCompany company = new SupplierCompany();
        SupplierCompanyDTO expectedDto = new SupplierCompanyDTO();

        when(supplierCompanyRepository.findById(id)).thenReturn(Optional.of(company));
        when(modelMapper.map(company, SupplierCompanyDTO.class)).thenReturn(expectedDto);

        SupplierCompanyDTO result = supplierCompanyService.getSupplierCompanyById(id);

        assertEquals(expectedDto, result);
        verify(supplierCompanyRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(company, SupplierCompanyDTO.class);
    }

    @Test
    void testGetSupplierCompanyByIdNotFound() {
        long id = 1L;

        when(supplierCompanyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierCompanyService.getSupplierCompanyById(id));
        verify(supplierCompanyRepository, times(1)).findById(id);
        verifyNoInteractions(modelMapper);
    }

}