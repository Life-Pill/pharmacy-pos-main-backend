package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemSaveRequestDTO {
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQuantity;
    private double stock;
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeStatus;
}
