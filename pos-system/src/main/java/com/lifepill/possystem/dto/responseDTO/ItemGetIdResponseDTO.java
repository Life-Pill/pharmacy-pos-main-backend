package com.lifepill.possystem.dto.responseDTO;

import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.dto.SupplierCompanyDTO;
import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.entity.ItemCategory;
import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemGetIdResponseDTO {
        private int itemId;
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
        private ItemCategoryDTO itemCategoryDTO;
        private SupplierDTO supplierDTO;
        private SupplierCompanyDTO supplierCompanyDTO;
}
