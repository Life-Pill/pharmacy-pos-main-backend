package com.lifepill.possystem.repo.cashierRepo;

import com.lifepill.possystem.entity.CashierBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CashierBankDetailsRepo extends JpaRepository<CashierBankDetails, Long> {

}
