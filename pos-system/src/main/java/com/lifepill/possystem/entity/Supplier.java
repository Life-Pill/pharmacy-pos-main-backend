package com.lifepill.possystem.entity;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "supplier_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int supplierId;

    //item id
    //supplier quantity

    @Column(name = "supplier_name", length = 100)
    private String supplierName;

}