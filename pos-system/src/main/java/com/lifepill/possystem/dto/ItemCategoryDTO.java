package com.lifepill.possystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Item category dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemCategoryDTO {
    private long categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryImage;

}
