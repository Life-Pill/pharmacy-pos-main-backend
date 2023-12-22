package com.lifepill.possystem.entity;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data // ToString,Getters,Setters
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;
    @Enumerated(EnumType.STRING)
    @Column(name = "measure_type",length = 100,nullable = false)
    private MeasuringUnitType measuringUnitType;
    @Column(name = "balance_qty",length = 100,nullable = false)
    private double balanceQuantity;
    @Column(name = "stock_qty",length = 100,nullable = false)
    private double stock;
    @Column(name = "supplier_price",length = 20,nullable = false)
    private double supplierPrice;
    @Column(name = "selling_price",length = 20,nullable = false)
    private double sellingPrice;
    // change to out of stock or not
    @Column(name = "active_status", columnDefinition = "BOOLEAN default false")
    private boolean activeStatus;
//    @Column(name = "unit_price",nullable = false)
//    private double unitPrice;

}
