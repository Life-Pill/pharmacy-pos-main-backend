package com.lifepill.possystem.repo.searchRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifepill.possystem.config.OpenSearchConfig;
import com.lifepill.possystem.dto.requestDTO.ItemSearchDocument;
import com.lifepill.possystem.dto.requestDTO.ItemSearchRequestDTO;
import com.lifepill.possystem.dto.responseDTO.ItemSearchResponseDTO;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.SortOrder;
import org.opensearch.client.opensearch._types.query_dsl.*;
import org.opensearch.client.opensearch.core.*;
import org.opensearch.client.opensearch.core.search.Hit;
import org.opensearch.client.opensearch.indices.CreateIndexRequest;
import org.opensearch.client.opensearch.indices.ExistsRequest;
import org.opensearch.client.opensearch._types.mapping.*;
import org.opensearch.client.opensearch.indices.IndexSettings;
import org.opensearch.client.opensearch.indices.IndexSettingsAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository for managing Item documents in OpenSearch.
 * Provides CRUD operations and advanced search capabilities.
 */
@Repository
public class ItemSearchRepository {

    private static final Logger logger = LoggerFactory.getLogger(ItemSearchRepository.class);
    private static final String INDEX_NAME = "items";

    private final OpenSearchClient client;
    private final OpenSearchConfig config;
    private final ObjectMapper objectMapper;

    @Autowired
    public ItemSearchRepository(OpenSearchClient client, OpenSearchConfig config, ObjectMapper objectMapper) {
        this.client = client;
        this.config = config;
        this.objectMapper = objectMapper;
    }

    /**
     * Initialize the items index with proper mappings when the application starts.
     */
    @PostConstruct
    public void initIndex() {
        if (!config.isEnabled() || client == null) {
            logger.info("OpenSearch is disabled. Skipping index initialization.");
            return;
        }

        try {
            boolean indexExists = client.indices().exists(
                    ExistsRequest.of(e -> e.index(INDEX_NAME))
            ).value();

            if (!indexExists) {
                // Create index with mappings using programmatic API
                CreateIndexRequest createRequest = CreateIndexRequest.of(c -> c
                        .index(INDEX_NAME)
                        .settings(s -> s
                                .numberOfShards("1")
                                .numberOfReplicas("0")
                        )
                        .mappings(m -> m
                                .properties("itemId", p -> p.long_(l -> l))
                                .properties("itemName", p -> p.text(t -> t
                                        .fields("keyword", f -> f.keyword(k -> k))
                                ))
                                .properties("itemBarCode", p -> p.keyword(k -> k))
                                .properties("itemManufacture", p -> p.text(t -> t
                                        .fields("keyword", f -> f.keyword(k -> k))
                                ))
                                .properties("itemDescription", p -> p.text(t -> t))
                                .properties("sellingPrice", p -> p.double_(d -> d))
                                .properties("supplierPrice", p -> p.double_(d -> d))
                                .properties("itemQuantity", p -> p.double_(d -> d))
                                .properties("stock", p -> p.boolean_(b -> b))
                                .properties("freeIssued", p -> p.boolean_(b -> b))
                                .properties("discounted", p -> p.boolean_(b -> b))
                                .properties("discountedPrice", p -> p.double_(d -> d))
                                .properties("discountedPercentage", p -> p.double_(d -> d))
                                .properties("measuringUnitType", p -> p.keyword(k -> k))
                                .properties("warehouseName", p -> p.keyword(k -> k))
                                .properties("rackNumber", p -> p.keyword(k -> k))
                                .properties("specialCondition", p -> p.boolean_(b -> b))
                                .properties("itemImage", p -> p.keyword(k -> k))
                                .properties("manufactureDate", p -> p.date(d -> d))
                                .properties("expireDate", p -> p.date(d -> d))
                                .properties("supplyDate", p -> p.date(d -> d))
                                .properties("categoryId", p -> p.long_(l -> l))
                                .properties("categoryName", p -> p.text(t -> t
                                        .fields("keyword", f -> f.keyword(k -> k))
                                ))
                                .properties("supplierId", p -> p.long_(l -> l))
                                .properties("supplierName", p -> p.text(t -> t
                                        .fields("keyword", f -> f.keyword(k -> k))
                                ))
                                .properties("supplierCompanyName", p -> p.text(t -> t))
                                .properties("branchId", p -> p.long_(l -> l))
                                .properties("dateCreated", p -> p.date(d -> d))
                                .properties("lastUpdatedDate", p -> p.date(d -> d))
                                .properties("searchKeywords", p -> p.text(t -> t))
                        )
                );

                client.indices().create(createRequest);
                logger.info("Created OpenSearch index: {}", INDEX_NAME);
            } else {
                logger.info("OpenSearch index '{}' already exists", INDEX_NAME);
            }
        } catch (IOException e) {
            logger.error("Failed to initialize OpenSearch index: {}", e.getMessage(), e);
        }
    }

    /**
     * Index a single item document.
     *
     * @param document The item document to index
     * @return true if indexing was successful
     */
    public boolean indexItem(ItemSearchDocument document) {
        if (!config.isEnabled() || client == null) {
            return false;
        }

        try {
            IndexResponse response = client.index(i -> i
                    .index(INDEX_NAME)
                    .id(String.valueOf(document.getItemId()))
                    .document(document)
            );

            logger.debug("Indexed item {} with result: {}", document.getItemId(), response.result());
            return true;
        } catch (IOException e) {
            logger.error("Failed to index item {}: {}", document.getItemId(), e.getMessage(), e);
            return false;
        }
    }

    /**
     * Index multiple items in bulk.
     *
     * @param documents List of item documents to index
     * @return Number of successfully indexed items
     */
    public int bulkIndex(List<ItemSearchDocument> documents) {
        if (!config.isEnabled() || client == null || documents.isEmpty()) {
            return 0;
        }

        try {
            BulkRequest.Builder bulkBuilder = new BulkRequest.Builder();

            for (ItemSearchDocument doc : documents) {
                bulkBuilder.operations(op -> op
                        .index(idx -> idx
                                .index(INDEX_NAME)
                                .id(String.valueOf(doc.getItemId()))
                                .document(doc)
                        )
                );
            }

            BulkResponse response = client.bulk(bulkBuilder.build());

            if (response.errors()) {
                logger.warn("Bulk indexing completed with errors");
                return (int) response.items().stream()
                        .filter(item -> item.error() == null)
                        .count();
            }

            logger.info("Bulk indexed {} items", documents.size());
            return documents.size();
        } catch (IOException e) {
            logger.error("Failed to bulk index items: {}", e.getMessage(), e);
            return 0;
        }
    }

    /**
     * Delete an item from the index.
     *
     * @param itemId The ID of the item to delete
     * @return true if deletion was successful
     */
    public boolean deleteItem(Long itemId) {
        if (!config.isEnabled() || client == null) {
            return false;
        }

        try {
            DeleteResponse response = client.delete(d -> d
                    .index(INDEX_NAME)
                    .id(String.valueOf(itemId))
            );

            logger.debug("Deleted item {} with result: {}", itemId, response.result());
            return true;
        } catch (IOException e) {
            logger.error("Failed to delete item {}: {}", itemId, e.getMessage(), e);
            return false;
        }
    }

    /**
     * Search for items based on the provided search request.
     *
     * @param request The search request with query and filters
     * @return ItemSearchResponseDTO containing search results
     */
    public ItemSearchResponseDTO search(ItemSearchRequestDTO request) {
        if (!config.isEnabled() || client == null) {
            return ItemSearchResponseDTO.builder()
                    .hits(new ArrayList<>())
                    .totalHits(0)
                    .took(0)
                    .page(request.getPage())
                    .size(request.getSize())
                    .totalPages(0)
                    .build();
        }

        try {
            BoolQuery.Builder boolQuery = new BoolQuery.Builder();

            // Main text search query
            if (request.getQuery() != null && !request.getQuery().isBlank()) {
                boolQuery.must(m -> m
                        .multiMatch(mm -> mm
                                .query(request.getQuery())
                                .fields(
                                        "itemName^3",
                                        "itemBarCode^2",
                                        "itemManufacture^2",
                                        "itemDescription",
                                        "categoryName",
                                        "supplierName",
                                        "searchKeywords"
                                )
                                .type(TextQueryType.BestFields)
                                .fuzziness("AUTO")
                        )
                );
            } else {
                boolQuery.must(m -> m.matchAll(ma -> ma));
            }

            // Apply filters
            if (request.getCategoryName() != null && !request.getCategoryName().isBlank()) {
                boolQuery.filter(f -> f
                        .term(t -> t
                                .field("categoryName.keyword")
                                .value(FieldValue.of(request.getCategoryName()))
                        )
                );
            }

            if (request.getSupplierName() != null && !request.getSupplierName().isBlank()) {
                boolQuery.filter(f -> f
                        .term(t -> t
                                .field("supplierName.keyword")
                                .value(FieldValue.of(request.getSupplierName()))
                        )
                );
            }

            if (request.getBranchId() != null) {
                boolQuery.filter(f -> f
                        .term(t -> t
                                .field("branchId")
                                .value(FieldValue.of(request.getBranchId()))
                        )
                );
            }

            if (request.getInStock() != null) {
                boolQuery.filter(f -> f
                        .term(t -> t
                                .field("stock")
                                .value(FieldValue.of(request.getInStock()))
                        )
                );
            }

            if (request.getIsDiscounted() != null) {
                boolQuery.filter(f -> f
                        .term(t -> t
                                .field("discounted")
                                .value(FieldValue.of(request.getIsDiscounted()))
                        )
                );
            }

            // Price range filter
            if (request.getMinPrice() != null || request.getMaxPrice() != null) {
                boolQuery.filter(f -> f
                        .range(r -> {
                            r.field("sellingPrice");
                            if (request.getMinPrice() != null) {
                                r.gte(org.opensearch.client.json.JsonData.of(request.getMinPrice()));
                            }
                            if (request.getMaxPrice() != null) {
                                r.lte(org.opensearch.client.json.JsonData.of(request.getMaxPrice()));
                            }
                            return r;
                        })
                );
            }

            // Determine sort order
            SortOrder sortOrder = "desc".equalsIgnoreCase(request.getSortOrder())
                    ? SortOrder.Desc
                    : SortOrder.Asc;

            String sortField = request.getSortBy();
            if ("itemName".equals(sortField)) {
                sortField = "itemName.keyword";
            }

            String finalSortField = sortField;
            SearchResponse<ItemSearchDocument> response = client.search(s -> s
                            .index(INDEX_NAME)
                            .query(q -> q.bool(boolQuery.build()))
                            .from(request.getPage() * request.getSize())
                            .size(request.getSize())
                            .sort(sort -> sort
                                    .field(f -> f
                                            .field(finalSortField)
                                            .order(sortOrder)
                                    )
                            ),
                    ItemSearchDocument.class
            );

            List<ItemSearchResponseDTO.ItemSearchHit> hits = response.hits().hits().stream()
                    .map(this::mapToSearchHit)
                    .collect(Collectors.toList());

            long totalHits = response.hits().total() != null
                    ? response.hits().total().value()
                    : 0;

            int totalPages = (int) Math.ceil((double) totalHits / request.getSize());

            return ItemSearchResponseDTO.builder()
                    .hits(hits)
                    .totalHits(totalHits)
                    .took(response.took())
                    .page(request.getPage())
                    .size(request.getSize())
                    .totalPages(totalPages)
                    .build();

        } catch (IOException e) {
            logger.error("Search failed: {}", e.getMessage(), e);
            return ItemSearchResponseDTO.builder()
                    .hits(new ArrayList<>())
                    .totalHits(0)
                    .took(0)
                    .page(request.getPage())
                    .size(request.getSize())
                    .totalPages(0)
                    .build();
        }
    }

    /**
     * Autocomplete/suggest items based on partial input.
     *
     * @param prefix The prefix to search for
     * @param size Maximum number of suggestions
     * @return List of suggested item names
     */
    public List<String> suggest(String prefix, int size) {
        if (!config.isEnabled() || client == null || prefix == null || prefix.isBlank()) {
            return new ArrayList<>();
        }

        try {
            SearchResponse<ItemSearchDocument> response = client.search(s -> s
                            .index(INDEX_NAME)
                            .query(q -> q
                                    .prefix(p -> p
                                            .field("itemName")
                                            .value(prefix.toLowerCase())
                                    )
                            )
                            .size(size)
                            .source(src -> src
                                    .filter(f -> f
                                            .includes("itemName")
                                    )
                            ),
                    ItemSearchDocument.class
            );

            return response.hits().hits().stream()
                    .map(hit -> hit.source() != null ? hit.source().getItemName() : null)
                    .filter(name -> name != null)
                    .distinct()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            logger.error("Suggestion failed: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private ItemSearchResponseDTO.ItemSearchHit mapToSearchHit(Hit<ItemSearchDocument> hit) {
        ItemSearchDocument source = hit.source();
        if (source == null) {
            return null;
        }

        return ItemSearchResponseDTO.ItemSearchHit.builder()
                .itemId(source.getItemId())
                .itemName(source.getItemName())
                .itemBarCode(source.getItemBarCode())
                .itemManufacture(source.getItemManufacture())
                .itemDescription(source.getItemDescription())
                .sellingPrice(source.getSellingPrice())
                .itemQuantity(source.getItemQuantity())
                .stock(source.isStock())
                .discounted(source.isDiscounted())
                .discountedPrice(source.getDiscountedPrice())
                .categoryName(source.getCategoryName())
                .supplierName(source.getSupplierName())
                .itemImage(source.getItemImage())
                .branchId(source.getBranchId())
                .score(hit.score() != null ? hit.score() : 0.0)
                .build();
    }
}
