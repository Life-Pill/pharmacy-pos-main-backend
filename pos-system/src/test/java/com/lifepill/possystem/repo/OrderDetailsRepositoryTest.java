package com.lifepill.possystem.repo;

import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.OrderDetails;
import com.lifepill.possystem.repo.orderRepository.OrderDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class OrderDetailsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    /*@Test
    void testSaveOrderDetails() {
        // Create test data
        Order order = new Order(*//* Set order details *//*);
        order = entityManager.persist(order);

        OrderDetails orderDetails = new OrderDetails(*//* Set order details *//*);
        orderDetails.setOrders(order);
        orderDetails = entityManager.persist(orderDetails);
        entityManager.flush();

        // Call repository method
        OrderDetails savedOrderDetails = orderDetailsRepository.findById(orderDetails.getOrderDetailsId()).orElse(null);

        // Assert result
        assertNotNull(savedOrderDetails);
        assertEquals(orderDetails.getOrderDetailsId(), savedOrderDetails.getOrderDetailsId());
        assertEquals(orderDetails.getName(), savedOrderDetails.getName());
        assertEquals(orderDetails.getAmount(), savedOrderDetails.getAmount());
        assertEquals(order.getOrderId(), savedOrderDetails.getOrders().getOrderId());
    }*/
}
