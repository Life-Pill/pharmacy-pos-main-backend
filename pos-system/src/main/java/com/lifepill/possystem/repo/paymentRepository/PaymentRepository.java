package com.lifepill.possystem.repo.paymentRepository;

import com.lifepill.possystem.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {
}
