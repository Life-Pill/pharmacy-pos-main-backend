package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.*;

import java.util.Date;

/**
 * The type Item save request dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemSaveRequestDTO {
    private long itemId;
    private long branchId;
    private String itemName;
    private double sellingPrice;
    private String itemBarCode;
    private Date supplyDate;
    private double supplierPrice;
    private boolean isFreeIssued;
    private boolean isDiscounted;
    private String itemManufacture;
    private double itemQuantity;
    private boolean isStock;
    private MeasuringUnitType measuringUnitType;
    private Date manufactureDate;
    private Date expireDate;
    private Date purchaseDate;
    private String warrantyPeriod;
    private String rackNumber;
    private double discountedPrice;
    private double discountedPercentage;
    private String warehouseName;
    private boolean isSpecialCondition;
    private String itemImage;
    private String itemDescription;
    private long categoryId;
    private long supplierId;
}
