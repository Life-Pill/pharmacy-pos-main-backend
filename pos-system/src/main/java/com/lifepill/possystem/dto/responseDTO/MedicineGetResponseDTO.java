package com.lifepill.possystem.dto.responseDTO;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MedicineGetResponseDTO {
    private long itemId;
    private BranchDTO branchDTO;
    private String itemName;
    private double sellingPrice;
    private String itemBarCode;
    private MeasuringUnitType measuringUnitType;
    private ItemCategoryDTO itemCategoryDTO; // Added ItemCategoryDTO field
    private String itemImage;
    private String itemDescription;
    private boolean isStock;
    private boolean isOpen; // branch open or closed
    private long Rate; // out of 5

}
