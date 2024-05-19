/*
package com.lifepill.possystem.repo;

import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.repo.orderRepository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveOrder() {
        // Create test data
        Employer employer = new Employer(*/
/* Set employer details *//*
);
        employer = entityManager.persist(employer);

        Order order = new Order(employer, new Date(), 1000.0);
        order = entityManager.persist(order);
        entityManager.flush();

        // Call repository method
        Order savedOrder = orderRepository.findById(order.getOrderId()).orElse(null);

        // Assert result
        assertNotNull(savedOrder);
        assertEquals(order.getOrderId(), savedOrder.getOrderId());
        assertEquals(order.getEmployer().getEmployerId(), savedOrder.getEmployer().getEmployerId());
        assertEquals(order.getOrderDate(), savedOrder.getOrderDate());
        assertEquals(order.getTotal(), savedOrder.getTotal());
    }
}
*/
