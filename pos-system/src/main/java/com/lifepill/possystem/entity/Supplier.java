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

    @Column(name = "supplier_address", length = 100)
    private String supplierAddress;

    @Column(name = "supplier_phone", length = 12)
    private String supplierPhone;

    @Column(name = "supplier_email", length = 50)
    private String supplierEmail;

    @Column(name = "supplier_description", length = 100)
    private String supplierDescription;

    @Column(name = "supplier_image")
    private String supplierImage;

}