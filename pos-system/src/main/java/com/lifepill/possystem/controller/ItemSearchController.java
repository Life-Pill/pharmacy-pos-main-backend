package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.ItemSearchRequestDTO;
import com.lifepill.possystem.dto.responseDTO.ItemSearchResponseDTO;
import com.lifepill.possystem.service.ItemSearchService;
import com.lifepill.possystem.util.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for OpenSearch item search operations.
 */
@RestController
@RequestMapping("/lifepill/v1/search")
@RequiredArgsConstructor
@Tag(name = "Item Search", description = "OpenSearch powered item search endpoints")
public class ItemSearchController {

    private final ItemSearchService itemSearchService;

    /**
     * Search for items with full-text search and filters.
     *
     * @param query Search query (optional)
     * @param categoryName Filter by category name (optional)
     * @param supplierName Filter by supplier name (optional)
     * @param branchId Filter by branch ID (optional)
     * @param inStock Filter by stock status (optional)
     * @param isDiscounted Filter by discount status (optional)
     * @param minPrice Minimum price filter (optional)
     * @param maxPrice Maximum price filter (optional)
     * @param page Page number (default: 0)
     * @param size Page size (default: 20)
     * @param sortBy Field to sort by (default: itemName)
     * @param sortOrder Sort order: asc or desc (default: asc)
     * @return Search results with pagination
     */
    @GetMapping("/items")
    @Operation(summary = "Search items", description = "Full-text search for items with optional filters")
    public ResponseEntity<StandardResponse> searchItems(
            @Parameter(description = "Search query") 
            @RequestParam(required = false) String query,
            
            @Parameter(description = "Filter by category name")
            @RequestParam(required = false) String categoryName,
            
            @Parameter(description = "Filter by supplier name")
            @RequestParam(required = false) String supplierName,
            
            @Parameter(description = "Filter by branch ID")
            @RequestParam(required = false) Long branchId,
            
            @Parameter(description = "Filter by stock status")
            @RequestParam(required = false) Boolean inStock,
            
            @Parameter(description = "Filter by discount status")
            @RequestParam(required = false) Boolean isDiscounted,
            
            @Parameter(description = "Minimum price")
            @RequestParam(required = false) Double minPrice,
            
            @Parameter(description = "Maximum price")
            @RequestParam(required = false) Double maxPrice,
            
            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") int page,
            
            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "20") int size,
            
            @Parameter(description = "Sort field")
            @RequestParam(defaultValue = "itemName") String sortBy,
            
            @Parameter(description = "Sort order (asc/desc)")
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        ItemSearchRequestDTO request = ItemSearchRequestDTO.builder()
                .query(query)
                .categoryName(categoryName)
                .supplierName(supplierName)
                .branchId(branchId)
                .inStock(inStock)
                .isDiscounted(isDiscounted)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortOrder(sortOrder)
                .build();

        ItemSearchResponseDTO response = itemSearchService.searchItems(request);

        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Search completed", response),
                HttpStatus.OK
        );
    }

    /**
     * Advanced search with request body.
     *
     * @param request Search request DTO
     * @return Search results
     */
    @PostMapping("/items")
    @Operation(summary = "Advanced item search", description = "Search items with complex filters using request body")
    public ResponseEntity<StandardResponse> advancedSearch(@RequestBody ItemSearchRequestDTO request) {
        ItemSearchResponseDTO response = itemSearchService.searchItems(request);

        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Search completed", response),
                HttpStatus.OK
        );
    }

    /**
     * Get autocomplete suggestions for item names.
     *
     * @param prefix Prefix to search for
     * @param size Maximum number of suggestions
     * @return List of suggested item names
     */
    @GetMapping("/suggest")
    @Operation(summary = "Get suggestions", description = "Autocomplete suggestions for item names")
    public ResponseEntity<StandardResponse> getSuggestions(
            @Parameter(description = "Prefix to search for")
            @RequestParam String prefix,
            
            @Parameter(description = "Maximum suggestions")
            @RequestParam(defaultValue = "10") int size
    ) {
        List<String> suggestions = itemSearchService.getSuggestions(prefix, size);

        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Suggestions retrieved", suggestions),
                HttpStatus.OK
        );
    }

    /**
     * Index a single item in OpenSearch.
     * Requires OWNER or MANAGER role.
     *
     * @param itemId ID of the item to index
     * @return Success status
     */
    @PostMapping("/index/{itemId}")
    @PreAuthorize("hasAnyRole('OWNER', 'MANAGER')")
    @Operation(summary = "Index single item", description = "Index a specific item in OpenSearch")
    public ResponseEntity<StandardResponse> indexItem(
            @Parameter(description = "Item ID to index")
            @PathVariable Long itemId
    ) {
        boolean success = itemSearchService.indexItem(itemId);

        if (success) {
            return new ResponseEntity<>(
                    new StandardResponse(HttpStatus.OK.value(), "Item indexed successfully", null),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to index item", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    /**
     * Index all items from the database to OpenSearch.
     * Requires OWNER role.
     *
     * @return Number of items indexed
     */
    @PostMapping("/index/all")
    @PreAuthorize("hasRole('OWNER')")
    @Operation(summary = "Index all items", description = "Bulk index all items from database to OpenSearch")
    public ResponseEntity<StandardResponse> indexAllItems() {
        int count = itemSearchService.indexAllItems();

        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Indexed " + count + " items", count),
                HttpStatus.OK
        );
    }

    /**
     * Reindex all items (delete and recreate index).
     * Requires OWNER role.
     *
     * @return Number of items indexed
     */
    @PostMapping("/reindex")
    @PreAuthorize("hasRole('OWNER')")
    @Operation(summary = "Reindex all items", description = "Delete existing index and reindex all items")
    public ResponseEntity<StandardResponse> reindexAll() {
        int count = itemSearchService.reindexAll();

        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Reindexed " + count + " items", count),
                HttpStatus.OK
        );
    }

    /**
     * Delete an item from the search index.
     * Requires OWNER or MANAGER role.
     *
     * @param itemId ID of the item to delete from index
     * @return Success status
     */
    @DeleteMapping("/index/{itemId}")
    @PreAuthorize("hasAnyRole('OWNER', 'MANAGER')")
    @Operation(summary = "Delete item from index", description = "Remove a specific item from OpenSearch index")
    public ResponseEntity<StandardResponse> deleteFromIndex(
            @Parameter(description = "Item ID to remove from index")
            @PathVariable Long itemId
    ) {
        boolean success = itemSearchService.deleteItemFromIndex(itemId);

        if (success) {
            return new ResponseEntity<>(
                    new StandardResponse(HttpStatus.OK.value(), "Item removed from index", null),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to remove item from index", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
