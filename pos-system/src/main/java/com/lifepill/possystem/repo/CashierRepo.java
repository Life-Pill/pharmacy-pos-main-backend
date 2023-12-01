package com.lifepill.possystem.repo;

import com.lifepill.possystem.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CashierRepo extends JpaRepository<Cashier, Integer> {

}
