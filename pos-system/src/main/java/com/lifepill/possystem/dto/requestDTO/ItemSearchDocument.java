package com.lifepill.possystem.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Document class representing an Item in OpenSearch index.
 * This is used for full-text search capabilities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchDocument {

    private Long itemId;
    private String itemName;
    private String itemBarCode;
    private String itemManufacture;
    private String itemDescription;
    private double sellingPrice;
    private double supplierPrice;
    private double itemQuantity;
    private boolean stock;
    private boolean freeIssued;
    private boolean discounted;
    private double discountedPrice;
    private double discountedPercentage;
    private String measuringUnitType;
    private String warehouseName;
    private String rackNumber;
    private boolean specialCondition;
    private String itemImage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date manufactureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expireDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date supplyDate;

    // Category info
    private Long categoryId;
    private String categoryName;

    // Supplier info
    private Long supplierId;
    private String supplierName;
    private String supplierCompanyName;

    // Branch info
    private Long branchId;

    // Timestamps
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date lastUpdatedDate;

    // Computed field for search boosting
    private String searchKeywords;
}
