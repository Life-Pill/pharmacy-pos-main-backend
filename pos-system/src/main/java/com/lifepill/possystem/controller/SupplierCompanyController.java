package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.SupplierCompanyDTO;
import com.lifepill.possystem.service.SupplierCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplierCompanies")
public class SupplierCompanyController {

    @Autowired
    private SupplierCompanyService supplierCompanyService;

    @GetMapping(path = "/getAll-Supplier-Companies")
    public ResponseEntity<List<SupplierCompanyDTO>> getAllSupplierCompanies() {
        List<SupplierCompanyDTO> companies = supplierCompanyService.getAllSupplierCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<SupplierCompanyDTO> saveSupplierCompany(@RequestBody SupplierCompanyDTO supplierCompanyDTO) {
        SupplierCompanyDTO savedCompany = supplierCompanyService.saveSupplierCompany(supplierCompanyDTO);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update-supplier-company/{id}")
    public ResponseEntity<SupplierCompanyDTO> updateSupplierCompanyById(@PathVariable("id") long id, @RequestBody SupplierCompanyDTO updatedCompanyDTO) {
        SupplierCompanyDTO updatedCompany = supplierCompanyService.updateSupplierCompanyById(id, updatedCompanyDTO);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-supplier-company/{id}")
    public ResponseEntity<Void> deleteSupplierCompanyById(@PathVariable("id") long id) {
        supplierCompanyService.deleteSupplierCompanyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}