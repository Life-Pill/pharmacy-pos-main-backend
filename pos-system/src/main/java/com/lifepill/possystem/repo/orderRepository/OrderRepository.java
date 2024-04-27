package com.lifepill.possystem.repo.orderRepository;

import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Collection;

import java.util.List;

/**
 * The interface Order repository.
 */
@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order,Long> {
    /**
     * Find by branch id list.
     *
     * @param branchId the branch id
     * @return the list
     */
    List<Order> findByBranchId(Long branchId);

    /**
     * Find by order date list.
     *
     * @param date the date
     * @return the list
     */
    List<Order> findByOrderDate(Date date);
    //  Collection<Object> findByBranchId(Branch branch);


}