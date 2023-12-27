package com.lifepill.possystem.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@TypeDefs({
        @TypeDef(name = "json",typeClass = JsonTypeId.class)
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {
    @Id
    @Column(name = "order_details_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;

    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;

    @Column(name = "qty",length = 100,nullable = false)
    private Double qty;

    @Column(name = "total_amount",nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orders;

    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private Item items;

}