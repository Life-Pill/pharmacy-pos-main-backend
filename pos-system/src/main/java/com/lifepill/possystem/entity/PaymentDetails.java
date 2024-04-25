package com.lifepill.possystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDetails {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentId;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_amount")
    private double paymentAmount;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_notes")
    private String paymentNotes;
    @Column(name = "payment_discount")
    private double paymentDiscount;
    @Column(name = "paid_amount")
    private double paidAmount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orders;
}
