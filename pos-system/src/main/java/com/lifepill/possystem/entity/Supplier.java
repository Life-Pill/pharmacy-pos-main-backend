package com.lifepill.possystem.entity;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * The type Supplier.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@Table(name = "supplier")
public class Supplier extends BaseEntity {
    @Id
    @Column(name = "supplier_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long supplierId;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private Set<Item> items;

    @Column(name = "supplier_rating")
    private String supplierRating;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = true)
    private SupplierCompany supplierCompany;

}