package com.lifepill.possystem.repo.supplierRepository;

import com.lifepill.possystem.entity.SupplierCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Supplier company repository.
 */
public interface SupplierCompanyRepository extends JpaRepository<SupplierCompany, Long> {
}