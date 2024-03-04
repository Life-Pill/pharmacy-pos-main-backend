package com.lifepill.possystem.repo.cashierRepo;

import com.lifepill.possystem.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CashierRepo extends JpaRepository<Cashier, Integer> {

    List<Cashier> findByIsActiveStatusEquals(boolean activeState);

    boolean existsAllByCashierEmail(String cashierEmail);
}
