package com.lifepill.possystem.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response DTO for OpenSearch search results.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchResponseDTO {

    private List<ItemSearchHit> hits;
    private long totalHits;
    private long took; // time in milliseconds
    private int page;
    private int size;
    private int totalPages;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemSearchHit {
        private Long itemId;
        private String itemName;
        private String itemBarCode;
        private String itemManufacture;
        private String itemDescription;
        private double sellingPrice;
        private double itemQuantity;
        private boolean stock;
        private boolean discounted;
        private double discountedPrice;
        private String categoryName;
        private String supplierName;
        private String itemImage;
        private Long branchId;
        private double score; // relevance score from OpenSearch
    }
}
