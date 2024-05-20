package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.SupplierCompanyDTO;
import com.lifepill.possystem.service.SupplierCompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing supplier company-related endpoints.
 */
@RestController
@RequestMapping("/lifepill/v1/supplierCompanies")
@AllArgsConstructor
public class SupplierCompanyController {

    private SupplierCompanyService supplierCompanyService;

    /**
     * Retrieves all supplier companies.
     *
     * @return ResponseEntity containing a list of all supplier companies.
     */
    @GetMapping(path = "/getAll-Supplier-Companies")
    public ResponseEntity<List<SupplierCompanyDTO>> getAllSupplierCompanies() {
        List<SupplierCompanyDTO> companies = supplierCompanyService.getAllSupplierCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    /**
     * Saves a new supplier company.
     *
     * @param supplierCompanyDTO The SupplierCompanyDTO object containing information about the new supplier company.
     * @return ResponseEntity containing the saved supplier company.
     */
    @PostMapping(path = "/save")
    public ResponseEntity<SupplierCompanyDTO> saveSupplierCompany(
            @RequestBody SupplierCompanyDTO supplierCompanyDTO
    ) {
        SupplierCompanyDTO savedCompany = supplierCompanyService
                .saveSupplierCompany(supplierCompanyDTO);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    /**
     * Updates a supplier company by its ID.
     *
     * @param id               The ID of the supplier company to update.
     * @param updatedCompanyDTO The updated supplier company DTO.
     * @return ResponseEntity containing the updated supplier company DTO and HTTP status.
     */
    @PutMapping(path = "/update-supplier-company/{id}")
    public ResponseEntity<SupplierCompanyDTO> updateSupplierCompanyById(
            @PathVariable("id") long id, @RequestBody SupplierCompanyDTO updatedCompanyDTO
    ) {
        SupplierCompanyDTO updatedCompany =
                supplierCompanyService.updateSupplierCompanyById(id, updatedCompanyDTO);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    /**
     * Deletes a supplier company by its ID.
     *
     * @param id The ID of the supplier company to delete.
     * @return ResponseEntity with HTTP status indicating the success of the deletion.
     */
    @DeleteMapping(path = "/delete-supplier-company/{id}")
    public ResponseEntity<Void> deleteSupplierCompanyById(@PathVariable("id") long id) {
        supplierCompanyService.deleteSupplierCompanyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a supplier company by its ID.
     *
     * @param id The ID of the supplier company to retrieve.
     * @return ResponseEntity containing the retrieved supplier company DTO.
     */
    @GetMapping(path ="/get-supplier-company/{id}")
    public ResponseEntity<SupplierCompanyDTO> getSupplierCompanyById(@PathVariable("id") long id) {
        SupplierCompanyDTO companyDTO = supplierCompanyService.getSupplierCompanyById(id);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }
}