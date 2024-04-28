package com.lifepill.possystem.repo.supplierRepository;

import com.lifepill.possystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 * The interface Supplier repository.
 */
@Repository
@EnableJpaRepositories
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    /**
     * Exists all by supplier email boolean.
     *
     * @param supplierEmail the supplier email
     * @return the boolean
     */
    boolean existsAllBySupplierEmail(String supplierEmail);
}