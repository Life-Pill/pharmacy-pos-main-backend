package com.lifepill.possystem.repo.orderRepository;

import com.lifepill.possystem.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {

}
