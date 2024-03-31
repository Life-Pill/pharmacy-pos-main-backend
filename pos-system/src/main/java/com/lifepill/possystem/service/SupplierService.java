package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    List<SupplierDTO> getAllSuppliers();

    SupplierDTO saveSupplier(SupplierDTO supplierDTO);

    SupplierDTO updateSupplierById(long id, SupplierDTO updatedSupplierDTO);

    void deleteSupplierById(long id);

    SupplierDTO getSupplierById(long id);
}
