package com.lifepill.possystem.repo.employerRepository;

import com.lifepill.possystem.entity.EmployerBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface EmployerBankDetailsRepository extends JpaRepository<EmployerBankDetails, Long> {

}
