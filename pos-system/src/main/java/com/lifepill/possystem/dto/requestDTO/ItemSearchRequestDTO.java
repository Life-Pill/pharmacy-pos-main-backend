package com.lifepill.possystem.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for item search operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchRequestDTO {

    private String query;           // Main search query
    private String categoryName;    // Filter by category
    private String supplierName;    // Filter by supplier
    private Long branchId;          // Filter by branch
    private Boolean inStock;        // Filter by stock status
    private Boolean isDiscounted;   // Filter by discount status
    private Double minPrice;        // Minimum price filter
    private Double maxPrice;        // Maximum price filter

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 20;

    @Builder.Default
    private String sortBy = "itemName";

    @Builder.Default
    private String sortOrder = "asc";
}
