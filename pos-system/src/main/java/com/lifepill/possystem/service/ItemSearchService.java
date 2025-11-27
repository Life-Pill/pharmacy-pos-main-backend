package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.requestDTO.ItemSearchDocument;
import com.lifepill.possystem.dto.requestDTO.ItemSearchRequestDTO;
import com.lifepill.possystem.dto.responseDTO.ItemSearchResponseDTO;

import java.util.List;

/**
 * Service interface for OpenSearch item operations.
 */
public interface ItemSearchService {

    /**
     * Search for items based on the search request.
     *
     * @param request Search parameters
     * @return Search results
     */
    ItemSearchResponseDTO searchItems(ItemSearchRequestDTO request);

    /**
     * Get autocomplete suggestions for item names.
     *
     * @param prefix Prefix to search for
     * @param size Maximum number of suggestions
     * @return List of suggested item names
     */
    List<String> getSuggestions(String prefix, int size);

    /**
     * Index a single item in OpenSearch.
     *
     * @param itemId ID of the item to index
     * @return true if indexing was successful
     */
    boolean indexItem(Long itemId);

    /**
     * Index all items from the database to OpenSearch.
     *
     * @return Number of items indexed
     */
    int indexAllItems();

    /**
     * Delete an item from the OpenSearch index.
     *
     * @param itemId ID of the item to delete
     * @return true if deletion was successful
     */
    boolean deleteItemFromIndex(Long itemId);

    /**
     * Reindex all items (delete index and recreate).
     *
     * @return Number of items indexed
     */
    int reindexAll();
}
