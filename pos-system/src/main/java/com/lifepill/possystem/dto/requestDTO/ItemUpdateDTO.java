package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemUpdateDTO {
    private int itemId;
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQuantity;
    private double stock;
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeStatus;
}
