/*
package com.lifepill.possystem.cucumber.supplier;

import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.service.SupplierService;
import com.lifepill.possystem.service.impl.SupplierServiceIMPL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.entity.Supplier;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.supplierRepository.SupplierRepository;
import com.lifepill.possystem.service.SupplierService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.entity.Supplier;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.supplierRepository.SupplierRepository;
import com.lifepill.possystem.service.SupplierService;
import com.lifepill.possystem.service.impl.SupplierServiceIMPL;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SupplierStepDefinitions {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierServiceIMPL supplierServiceIMPL;

    private List<Supplier> suppliers;
    private List<SupplierDTO> supplierDTOs;
    private SupplierDTO savedSupplierDTO;
    private SupplierDTO updatedSupplierDTO;
    private Exception exception;

    @Given("there are suppliers in the system")
    public void thereAreSuppliersInTheSystem() {
        suppliers = new ArrayList<>();
        Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(1L);
        suppliers.add(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setSupplierId(2L);
        suppliers.add(supplier2);

        when(supplierRepository.findAll()).thenReturn(suppliers);
    }

    @When("the user requests to get all suppliers")
    public void theUserRequestsToGetAllSuppliers() {
        try {
            supplierDTOs = supplierServiceIMPL.getAllSuppliers();
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the system should return a list of suppliers")
    public void theSystemShouldReturnAListOfSuppliers() {
        assertNotNull(supplierDTOs);
        assertEquals(2, supplierDTOs.size());
    }

    @Given("there is a supplier with ID {long}")
    public void thereIsASupplierWithID(long supplierId) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
    }

    @When("the user requests to get the supplier with ID {long}")
    public void theUserRequestsToGetTheSupplierWithID(long supplierId) {
        try {
            savedSupplierDTO = supplierServiceIMPL.getSupplierById(supplierId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the system should return the supplier with ID {long}")
    public void theSystemShouldReturnTheSupplierWithID(long supplierId) {
        assertNotNull(savedSupplierDTO);
        assertEquals(supplierId, savedSupplierDTO.getSupplierId());
    }

    @Given("there is no supplier with email {string}")
    public void thereIsNoSupplierWithEmail(String email) {
        when(supplierRepository.existsAllBySupplierEmail(email)).thenReturn(false);
    }

    @When("the user requests to save a new supplier with email {string}")
    public void theUserRequestsToSaveANewSupplierWithEmail(String email) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierEmail(email);
        try {
            savedSupplierDTO = supplierServiceIMPL.saveSupplier(supplierDTO);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the system should save the new supplier")
    public void theSystemShouldSaveTheNewSupplier() {
        assertNotNull(savedSupplierDTO);
    }

    @When("the user requests to update the supplier with ID {long}")
    public void theUserRequestsToUpdateTheSupplierWithID(long supplierId) {
        SupplierDTO updatedSupplierDTO = new SupplierDTO();
        updatedSupplierDTO.setSupplierId(supplierId);
        try {
            this.updatedSupplierDTO = supplierServiceIMPL.updateSupplierById(supplierId, updatedSupplierDTO);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the system should update the supplier with ID {long}")
    public void theSystemShouldUpdateTheSupplierWithID(long supplierId) {
        assertNotNull(updatedSupplierDTO);
        assertEquals(supplierId, updatedSupplierDTO.getSupplierId());
    }

    @When("the user requests to delete the supplier with ID {long}")
    public void theUserRequestsToDeleteTheSupplierWithID(long supplierId) {
        try {
            supplierServiceIMPL.deleteSupplierById(supplierId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the system should delete the supplier with ID {long}")
    public void theSystemShouldDeleteTheSupplierWithID(long supplierId) {
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Given("there are no suppliers in the system")
    public void thereAreNoSuppliersInTheSystem() {
        when(supplierRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Given("there is a supplier with email {string}")
    public void thereIsASupplierWithEmail(String email) {
        when(supplierRepository.existsAllBySupplierEmail(email)).thenReturn(true);
    }

    @Given("there is a supplier with ID {long} and email {string}")
    public void thereIsASupplierWithIDAndEmail(long supplierId, String email) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        supplier.setSupplierEmail(email);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierRepository.existsAllBySupplierEmail(email)).thenReturn(true);
    }

    @Given("there is a supplier with ID {long} and no email")
    public void thereIsASupplierWithIDAndNoEmail(long supplierId) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierRepository.existsAllBySupplierEmail(anyString())).thenReturn(false);
    }

    @Given("there is a supplier with ID {long} and email {string} and no email")
    public void thereIsASupplierWithIDAndEmailAndNoEmail(long supplierId, String email) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        supplier.setSupplierEmail(email);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierRepository.existsAllBySupplierEmail(anyString())).thenReturn(false);
    }

}*/
