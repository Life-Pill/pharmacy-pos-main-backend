package com.lifepill.possystem.repo.supplierRepository;

import com.lifepill.possystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
