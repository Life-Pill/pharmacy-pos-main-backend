package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemSaveRequestCategoryDTO {

        private long itemId;
        private String itemName;
        private double sellingPrice;
        private String itemBarCode;
        private Date supplyDate;
        private double supplierPrice;
        private boolean isFreeIssued;
        private boolean isDiscounted;
        private String itemManufacture;
        private double itemQuantity;
        //    private String itemCategory;
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

        private int supplierId;

//        private String categoryName;
//        private String categoryDescription;


}
