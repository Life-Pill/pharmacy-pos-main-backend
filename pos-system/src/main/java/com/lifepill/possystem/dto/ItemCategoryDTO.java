package com.lifepill.possystem.dto;

import lombok.*;

/**
 * The type Item category dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemCategoryDTO {
    private long categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryImage;

}
