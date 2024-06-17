package com.lifepill.possystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifepill.possystem.dto.SupplierCompanyDTO;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.service.SupplierCompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Supplier company controller test.
 */
class SupplierCompanyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SupplierCompanyService supplierCompanyService;

    @InjectMocks
    private SupplierCompanyController supplierCompanyController;

    private ObjectMapper objectMapper;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(supplierCompanyController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Test get all supplier companies.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetAllSupplierCompanies() throws Exception {
        List<SupplierCompanyDTO> companies = new ArrayList<>();
        companies.add(new SupplierCompanyDTO());
        companies.add(new SupplierCompanyDTO());

        when(supplierCompanyService.getAllSupplierCompanies()).thenReturn(companies);

        mockMvc.perform(get("/lifepill/v1/supplierCompanies/getAll-Supplier-Companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(supplierCompanyService, times(1)).getAllSupplierCompanies();
    }

    /**
     * Test save supplier company.
     *
     * @throws Exception the exception
     */
    @Test
    void testSaveSupplierCompany() throws Exception {
        SupplierCompanyDTO supplierCompanyDTO = new SupplierCompanyDTO();
        SupplierCompanyDTO savedDTO = new SupplierCompanyDTO();

        when(supplierCompanyService.saveSupplierCompany(any(SupplierCompanyDTO.class))).thenReturn(savedDTO);

        mockMvc.perform(post("/lifepill/v1/supplierCompanies/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplierCompanyDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty());

        verify(supplierCompanyService, times(1)).saveSupplierCompany(any(SupplierCompanyDTO.class));
    }

    /**
     * Test update supplier company by id.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateSupplierCompanyById() throws Exception {
        long id = 1L;
        SupplierCompanyDTO updatedDTO = new SupplierCompanyDTO();
        SupplierCompanyDTO returnedDTO = new SupplierCompanyDTO();

        when(supplierCompanyService.updateSupplierCompanyById(eq(id), any(SupplierCompanyDTO.class))).thenReturn(returnedDTO);

        mockMvc.perform(put("/lifepill/v1/supplierCompanies/update-supplier-company/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

        verify(supplierCompanyService, times(1)).updateSupplierCompanyById(eq(id), any(SupplierCompanyDTO.class));
    }

    /**
     * Test delete supplier company by id.
     *
     * @throws Exception the exception
     */
//    @Test
//    void testDeleteSupplierCompanyById() throws Exception {
//        long id = 1L;
//
//        mockMvc.perform(delete("/lifepill/v1/supplierCompanies/delete-supplier-company/{id}", id))
//                .andExpect(status().isNoContent());
//
//        verify(supplierCompanyService, times(1)).deleteSupplierCompanyById(id);
//    }

    /**
     * Test get supplier company by id.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetSupplierCompanyById() throws Exception {
        long id = 1L;
        SupplierCompanyDTO companyDTO = new SupplierCompanyDTO();

        when(supplierCompanyService.getSupplierCompanyById(id)).thenReturn(companyDTO);

        mockMvc.perform(get("/lifepill/v1/supplierCompanies/get-supplier-company/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

        verify(supplierCompanyService, times(1)).getSupplierCompanyById(id);
    }


    /**
     * Test get all supplier companies empty list.
     *
     * @throws Exception the exception
     */
    @Test
    void testGetAllSupplierCompaniesEmptyList() throws Exception {
        when(supplierCompanyService.getAllSupplierCompanies()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/lifepill/v1/supplierCompanies/getAll-Supplier-Companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(supplierCompanyService, times(1)).getAllSupplierCompanies();
    }


    /**
     * Test update supplier company by id not found.
     *
     * @throws Exception the exception
     */
    @Test
    void testUpdateSupplierCompanyByIdNotFound() throws Exception {
        long id = 1L;
        SupplierCompanyDTO updatedDTO = new SupplierCompanyDTO();

        when(supplierCompanyService.updateSupplierCompanyById(eq(id), any(SupplierCompanyDTO.class)))
                .thenThrow(new NotFoundException("Supplier Company not found with id: " + id));

        mockMvc.perform(put("/lifepill/v1/supplierCompanies/update-supplier-company/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isNotFound());

        verify(supplierCompanyService, times(1)).updateSupplierCompanyById(eq(id), any(SupplierCompanyDTO.class));
    }

    /**
     * Test delete supplier company by id not found.
     *
     * @throws Exception the exception
     */
    @Test
    void testDeleteSupplierCompanyByIdNotFound() throws Exception {
        long id = 1L;

        doThrow(new NotFoundException("Supplier Company not found with id: " + id))
                .when(supplierCompanyService).deleteSupplierCompanyById(id);

        mockMvc.perform(delete("/lifepill/v1/supplierCompanies/delete-supplier-company/{id}", id))
                .andExpect(status().isNotFound());

        verify(supplierCompanyService, times(1)).deleteSupplierCompanyById(id);
    }
}