package com.lifepill.possystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Set;

/**
 * The type Order.
 */
@Entity
@Table(name = "orders")
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "employer_id",nullable = false)
    private Employer employer;

    @Column(name = "branch_id",nullable = false)
    private long branchId;

    @Column(name = "order_date", columnDefinition = "TIMESTAMP")
    private Date orderDate;

    @Column(name = "total",nullable = false)
    private Double total;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;

    @OneToMany(mappedBy = "orders")
    private Set<PaymentDetails> paymentDetails;

    /**
     * Instantiates a new Order.
     *
     * @param employers the employers
     * @param orderDate the order date
     * @param total     the total
     */
    public Order(Employer employers, Date orderDate, Double total) {
        this.employer = employers;
        this.orderDate = orderDate;
        this.total = total;
    }
}
