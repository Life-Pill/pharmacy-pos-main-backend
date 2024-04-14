package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lifepill/v1/supplier")
@CrossOrigin
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(path = "/get-all-suppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<SupplierDTO> saveSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO savedSupplier = supplierService.saveSupplier(supplierDTO);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @PutMapping(path ="/update-supplier/{id}")
    public ResponseEntity<SupplierDTO> updateSupplierById(@PathVariable("id") long id, @RequestBody SupplierDTO updatedSupplierDTO) {
        SupplierDTO updatedSupplier = supplierService.updateSupplierById(id, updatedSupplierDTO);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    @GetMapping(path ="get-supplier/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable("id") long id) {
        SupplierDTO supplierDTO = supplierService.getSupplierById(id);
        return new ResponseEntity<>(supplierDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-supplier/{id}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable("id") long id) {
        supplierService.deleteSupplierById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
