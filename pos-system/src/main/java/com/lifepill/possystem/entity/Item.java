package com.lifepill.possystem.entity;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "selling_price", length = 20, nullable = false)
    private double sellingPrice;

    @Column(name = "item_barcode", length = 20, nullable = false)
    private String itemBarCode;

    @Column(name = "supply_date")
    private Date supplyDate;

    @Column(name = "supplier_price", length = 20, nullable = false)
    private double supplierPrice;

    @Column(name = "is_freeIssued", columnDefinition = "BOOLEAN default false")
    private boolean isFreeIssued;

    @Column(name = "is_discounted", columnDefinition = "BOOLEAN default false")
    private boolean isDiscounted;

    @Column(name = "item_Manufacture", length = 100)
    private String itemManufacture;

    @Column(name = "item_quantity", nullable = false)
    private double itemQuantity;

    /*@Column(name = "item_category", length = 100, nullable = false)
    private String itemCategory;*/

    @Column(name = "is_stock", columnDefinition = "BOOLEAN default false")
    private boolean stock;;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_unit_type", length = 20)
    private MeasuringUnitType measuringUnitType;

    @Column(name = "manufacture_date")
    private Date manufactureDate;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "warranty_period")
    private String warrantyPeriod;

    @Column(name = "rack_number", length = 20)
    private String rackNumber;

    @Column(name = "discounted_price", length = 20)
    private double discountedPrice;

    @Column(name = "discounted_percentage", length = 20)
    private double discountedPercentage;

    @Column(name = "warehouse_name", length = 100)
    private String warehouseName;

    @Column(name = "is_specialCondition", columnDefinition = "BOOLEAN default false")
    private boolean isSpecialCondition;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "item_description", length = 100)
    private String itemDescription;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated_date")
    @UpdateTimestamp
    private Date lastUpdatedDate;


//    // change to out of stock or not
//    @Column(name = "active_status", columnDefinition = "BOOLEAN default false")
//    private boolean activeStatus;


    @OneToMany(mappedBy = "items")
    private Set<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ItemCategory itemCategory;


}
