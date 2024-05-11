package com.lifepill.possystem.cucumber.supplier;

import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.service.SupplierService;
import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SupplierStepDefinitions {

    @Autowired
    private SupplierService supplierService;

    private ResponseEntity<List<SupplierDTO>> getAllSuppliersResponse;
    private ResponseEntity<SupplierDTO> saveSupplierResponse;
    private ResponseEntity<SupplierDTO> updateSupplierResponse;
    private ResponseEntity<SupplierDTO> getSupplierByIdResponse;
    private ResponseEntity<Void> deleteSupplierByIdResponse;

    @Given("^the API is running$")
    public void theAPIIsRunning() {

    }

    @When("^I request all suppliers$")
    public void iRequestAllSuppliers() {
        getAllSuppliersResponse = (ResponseEntity<List<SupplierDTO>>) supplierService.getAllSuppliers();
    }

    @Then("^the response status code should be (\\d+)$")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, getAllSuppliersResponse.getStatusCode().value());
    }

    @And("^the response should contain a list of suppliers$")
    public void theResponseShouldContainAListOfSuppliers() {
        assertNotNull(getAllSuppliersResponse.getBody());
    }

    // Implement other step definitions similarly for other scenarios
}
