package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.config.OpenSearchConfig;
import com.lifepill.possystem.dto.requestDTO.ItemSearchDocument;
import com.lifepill.possystem.dto.requestDTO.ItemSearchRequestDTO;
import com.lifepill.possystem.dto.responseDTO.ItemSearchResponseDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.repo.searchRepository.ItemSearchRepository;
import com.lifepill.possystem.service.ItemSearchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of ItemSearchService for OpenSearch operations.
 */
@Service
@RequiredArgsConstructor
public class ItemSearchServiceIMPL implements ItemSearchService {

    private static final Logger logger = LoggerFactory.getLogger(ItemSearchServiceIMPL.class);

    private final ItemSearchRepository itemSearchRepository;
    private final ItemRepository itemRepository;
    private final OpenSearchConfig openSearchConfig;

    @Override
    public ItemSearchResponseDTO searchItems(ItemSearchRequestDTO request) {
        logger.debug("Searching items with query: {}", request.getQuery());
        return itemSearchRepository.search(request);
    }

    @Override
    public List<String> getSuggestions(String prefix, int size) {
        logger.debug("Getting suggestions for prefix: {}", prefix);
        return itemSearchRepository.suggest(prefix, size);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean indexItem(Long itemId) {
        if (!openSearchConfig.isEnabled()) {
            logger.debug("OpenSearch is disabled, skipping indexing for item: {}", itemId);
            return false;
        }

        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isEmpty()) {
            logger.warn("Item not found for indexing: {}", itemId);
            return false;
        }

        Item item = itemOpt.get();
        ItemSearchDocument document = mapItemToDocument(item);
        return itemSearchRepository.indexItem(document);
    }

    @Override
    @Transactional(readOnly = true)
    public int indexAllItems() {
        if (!openSearchConfig.isEnabled()) {
            logger.info("OpenSearch is disabled, skipping bulk indexing");
            return 0;
        }

        logger.info("Starting bulk indexing of all items...");
        List<Item> allItems = itemRepository.findAll();

        if (allItems.isEmpty()) {
            logger.info("No items found to index");
            return 0;
        }

        List<ItemSearchDocument> documents = allItems.stream()
                .map(this::mapItemToDocument)
                .collect(Collectors.toList());

        int indexed = itemSearchRepository.bulkIndex(documents);
        logger.info("Bulk indexing completed. Indexed {} out of {} items", indexed, allItems.size());
        return indexed;
    }

    @Override
    public boolean deleteItemFromIndex(Long itemId) {
        if (!openSearchConfig.isEnabled()) {
            return false;
        }
        return itemSearchRepository.deleteItem(itemId);
    }

    @Override
    @Transactional(readOnly = true)
    public int reindexAll() {
        if (!openSearchConfig.isEnabled()) {
            logger.info("OpenSearch is disabled, skipping reindexing");
            return 0;
        }

        logger.info("Starting reindex of all items...");

        // Delete all items from index first (by querying all and deleting)
        List<Item> allItems = itemRepository.findAll();
        for (Item item : allItems) {
            itemSearchRepository.deleteItem(item.getItemId());
        }

        // Re-index all items
        return indexAllItems();
    }

    /**
     * Maps a JPA Item entity to an OpenSearch document.
     *
     * @param item The Item entity
     * @return ItemSearchDocument for OpenSearch
     */
    private ItemSearchDocument mapItemToDocument(Item item) {
        ItemSearchDocument.ItemSearchDocumentBuilder builder = ItemSearchDocument.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .itemBarCode(item.getItemBarCode())
                .itemManufacture(item.getItemManufacture())
                .itemDescription(item.getItemDescription())
                .sellingPrice(item.getSellingPrice())
                .supplierPrice(item.getSupplierPrice())
                .itemQuantity(item.getItemQuantity())
                .stock(item.isStock())
                .freeIssued(item.isFreeIssued())
                .discounted(item.isDiscounted())
                .discountedPrice(item.getDiscountedPrice())
                .discountedPercentage(item.getDiscountedPercentage())
                .measuringUnitType(item.getMeasuringUnitType() != null ? item.getMeasuringUnitType().name() : null)
                .warehouseName(item.getWarehouseName())
                .rackNumber(item.getRackNumber())
                .specialCondition(item.isSpecialCondition())
                .itemImage(item.getItemImage())
                .manufactureDate(item.getManufactureDate())
                .expireDate(item.getExpireDate())
                .supplyDate(item.getSupplyDate())
                .branchId(item.getBranchId())
                .dateCreated(item.getDateCreated())
                .lastUpdatedDate(item.getLastUpdatedDate());

        // Add category info
        if (item.getItemCategory() != null) {
            builder.categoryId(item.getItemCategory().getCategoryId())
                    .categoryName(item.getItemCategory().getCategoryName());
        }

        // Add supplier info
        if (item.getSupplier() != null) {
            builder.supplierId(item.getSupplier().getSupplierId())
                    .supplierName(item.getSupplier().getSupplierName());

            if (item.getSupplier().getSupplierCompany() != null) {
                builder.supplierCompanyName(item.getSupplier().getSupplierCompany().getCompanyName());
            }
        }

        // Build search keywords for better search results
        StringBuilder keywords = new StringBuilder();
        if (item.getItemName() != null) keywords.append(item.getItemName()).append(" ");
        if (item.getItemBarCode() != null) keywords.append(item.getItemBarCode()).append(" ");
        if (item.getItemManufacture() != null) keywords.append(item.getItemManufacture()).append(" ");
        if (item.getItemDescription() != null) keywords.append(item.getItemDescription()).append(" ");
        if (item.getItemCategory() != null && item.getItemCategory().getCategoryName() != null) {
            keywords.append(item.getItemCategory().getCategoryName()).append(" ");
        }
        if (item.getSupplier() != null && item.getSupplier().getSupplierName() != null) {
            keywords.append(item.getSupplier().getSupplierName()).append(" ");
        }
        builder.searchKeywords(keywords.toString().trim());

        return builder.build();
    }
}
