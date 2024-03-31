package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.SupplierCompanyDTO;

import java.util.List;

public interface SupplierCompanyService {
    List<SupplierCompanyDTO> getAllSupplierCompanies();

    SupplierCompanyDTO saveSupplierCompany(SupplierCompanyDTO supplierCompanyDTO);

    SupplierCompanyDTO updateSupplierCompanyById(long id, SupplierCompanyDTO updatedCompanyDTO);

    void deleteSupplierCompanyById(long id);

    SupplierCompanyDTO getSupplierCompanyById(long id);
}
