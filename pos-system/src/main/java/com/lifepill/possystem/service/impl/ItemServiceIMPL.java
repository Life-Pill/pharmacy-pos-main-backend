package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestCategoryDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.entity.ItemCategory;
import com.lifepill.possystem.entity.Supplier;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.itemRepository.ItemCategoryRepository;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.repo.supplierRepository.SupplierRepository;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.mappers.ItemMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Saves a new item based on the provided item save request DTO.
     *
     * @param itemSaveRequestDTO The DTO containing the details of the item to be saved.
     * @return A message indicating the success of the save operation.
     * @throws EntityDuplicationException If an item with the same ID already exists.
     */
    @Override
    public String saveItems(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);

        // Check if the item category exists
        ItemCategory category = itemCategoryRepository.findById(itemSaveRequestDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with ID: "
                        + itemSaveRequestDTO.getCategoryId())
                );

        // Check if the item branch exists
        Branch branch = branchRepository.findById(itemSaveRequestDTO.getBranchId())
                .orElseThrow(() -> new NotFoundException("Branch not found with ID: "
                        + itemSaveRequestDTO.getBranchId())
                );

        // Check if the item supplier exists
        Supplier supplier = supplierRepository.findById(itemSaveRequestDTO.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier not found with ID: "
                        + itemSaveRequestDTO.getSupplierId())
                );

        if (!itemRepository.existsById(item.getItemId())) {
            itemRepository.save(item);
            return item.getItemName() + " Saved Successfull";
        } else {
            throw new EntityDuplicationException("Already added this Id item");
        }
    }

    /**
     * Retrieves all items from the database.
     *
     * @return A list of DTOs representing all items.
     * @throws NotFoundException If no items are found or they are out of stock.
     */
    @Override
    public List<ItemGetAllResponseDTO> getAllItems() {
        List<Item> getAllItems = itemRepository.findAll();

        if (!getAllItems.isEmpty()) {
            List<ItemGetAllResponseDTO> itemGetAllResponseDTOSList = new ArrayList<>();
            for (Item item : getAllItems) {
                ItemGetAllResponseDTO itemGetAllResponseDTO = new ItemGetAllResponseDTO(
                        item.getItemId(),
                        item.getBranchId(),
                        item.getItemName(),
                        item.getSellingPrice(),
                        item.getItemBarCode(),
                        item.getSupplyDate(),
                        item.getSupplierPrice(),
                        item.isFreeIssued(),
                        item.isDiscounted(),
                        item.getItemManufacture(),
                        item.getItemQuantity(),
                        item.getItemCategory().getCategoryName(),
                        item.getItemCategory().getCategoryId(),
                        item.isStock(),
                        item.getMeasuringUnitType(),
                        item.getManufactureDate(),
                        item.getExpireDate(),
                        item.getPurchaseDate(),
                        item.getWarrantyPeriod(),
                        item.getRackNumber(),
                        item.getDiscountedPrice(),
                        item.getDiscountedPercentage(),
                        item.getWarehouseName(),
                        item.isSpecialCondition(),
                        item.getItemImage(),
                        item.getItemDescription()
                );
                itemGetAllResponseDTOSList.add(itemGetAllResponseDTO);
            }
            return itemGetAllResponseDTOSList;
        } else {
            throw new NotFoundException("No Item Find or OUT of Stock");
        }
    }


    /**
     * Retrieves items by name and stock status.
     *
     * @param itemName The name of the item to search for.
     * @return A list of DTOs representing items matching the search criteria.
     * @throws NotFoundException If no items are found.
     */
    @Override
    public List<ItemGetResponseDTO> getItemByName(String itemName) {
        List<Item> items = itemRepository.findAllByItemName(itemName);
        if (!items.isEmpty()) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    items,
                    new TypeToken<List<ItemGetResponseDTO>>() {
                    }.getType()
            );
            return itemGetResponseDTOS;
        } else {
            throw new NotFoundException("Not found");
        }
    }

    /**
     * Retrieves items by stock status.
     *
     * @param activeStatus The stock status to filter by.
     * @return A list of DTOs representing items with the specified stock status.
     * @throws NotFoundException If no items are found.
     */
    @Override
    public List<ItemGetResponseDTO> getItemByStockStatus(boolean activeStatus) {
        List<Item> item = itemRepository.findAllByStockEquals(activeStatus);
        if (!item.isEmpty()) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    item,
                    new TypeToken<List<ItemGetResponseDTO>>() {
                    }.getType()
            );
            // System.out.println(itemGetResponseDTOS);
            //get responseDTOS getItemCategory.getItemName()



            return itemGetResponseDTOS;
        } else {
            throw new NotFoundException("out of Stock");
        }
    }

    /**
     * Retrieves items by barcode.
     *
     * @param itemBarCode The barcode of the item to search for.
     * @return A list of DTOs representing items with the specified barcode.
     * @throws NotFoundException If no items are found.
     */
    @Override
    public List<ItemGetResponseDTO> getItemByBarCode(String itemBarCode) {
        List<Item> item = itemRepository.findAllByItemBarCodeEquals(itemBarCode);
        if (!item.isEmpty()) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    item,
                    new TypeToken<List<ItemGetResponseDTO>>() {
                    }.getType()
            );
            return itemGetResponseDTOS;
        } else {
            throw new NotFoundException("No any item found for that barcode");
        }
    }

    /**
     * Updates an existing item based on the provided update DTO.
     *
     * @param itemUpdateDTO The DTO containing the updated details of the item.
     * @return A message indicating the success of the update operation.
     * @throws NotFoundException If the item to be updated is not found.
     */
    @Override
    public String updateItem(ItemUpdateDTO itemUpdateDTO) {
        if (itemRepository.existsById(itemUpdateDTO.getItemId())) {
            Item item = itemRepository.getReferenceById(itemUpdateDTO.getItemId());
            item.setItemName(itemUpdateDTO.getItemName());
            item.setItemBarCode(itemUpdateDTO.getItemBarCode());
            item.setSupplyDate(itemUpdateDTO.getSupplyDate());
            item.setFreeIssued(itemUpdateDTO.isFreeIssued());
            item.setDiscounted(itemUpdateDTO.isDiscounted());
            item.setItemManufacture(itemUpdateDTO.getItemManufacture());
            item.setItemQuantity(itemUpdateDTO.getItemQuantity());
//            item.setItemCategory(itemUpdateDTO.getItemCategory());
            item.setStock(itemUpdateDTO.isStock());
            item.setMeasuringUnitType(itemUpdateDTO.getMeasuringUnitType());
            item.setManufactureDate(itemUpdateDTO.getManufactureDate());
            item.setExpireDate(itemUpdateDTO.getExpireDate());
            item.setPurchaseDate(itemUpdateDTO.getPurchaseDate());
            item.setWarrantyPeriod(itemUpdateDTO.getWarrantyPeriod());
            item.setRackNumber(itemUpdateDTO.getRackNumber());
            item.setDiscountedPrice(itemUpdateDTO.getDiscountedPrice());
            item.setDiscountedPercentage(itemUpdateDTO.getDiscountedPercentage());
            item.setWarehouseName(itemUpdateDTO.getWarehouseName());
            item.setSpecialCondition(itemUpdateDTO.isSpecialCondition());
            item.setItemImage(itemUpdateDTO.getItemImage());
            item.setItemDescription(itemUpdateDTO.getItemDescription());

            itemRepository.save(item);

            System.out.println(item);

            return "UPDATED ITEMS";
        } else {
            throw new NotFoundException("no Item found in that date");
        }
    }

    /**
     * Deletes an item with the specified ID.
     *
     * @param itemId The ID of the item to be deleted.
     * @return A message indicating the success of the delete operation.
     * @throws NotFoundException If the item to be deleted is not found.
     */
    @Override
    public String deleteItem(long itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);

            return "deleted succesfully: " + itemId;
        } else {
            throw new NotFoundException("No item found for that id");
        }
    }

    /**
     * Retrieves items by stock status with pagination.
     *
     * @param activeStatus The stock status to filter by.
     * @param page         The page number.
     * @param size         The number of items per page.
     * @return A paginated response containing items with the specified stock status.
     * @throws NotFoundException If no items are found.
     */
    @Override
    public PaginatedResponseItemDTO getItemByStockStatusWithPaginateed(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepository.findAllByStockEquals(activeStatus, PageRequest.of(page, size));

        if (items.getSize() < 1) {
            throw new NotFoundException("No Data");
        } else {
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                    itemMapper.ListDTOToPage(items),
                    itemRepository.countAllByStockEquals(activeStatus)
            );
            return paginatedResponseItemDTO;
        }
    }


    /**
     * Retrieves items by name and status using MapStruct for mapping.
     *
     * @param itemName The name of the item to search for.
     * @return A list of DTOs representing items with the specified name and stock status.
     * @throws NotFoundException If no items are found.
     */
    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName) {
        List<Item> items = itemRepository.findAllByItemNameEqualsAndStockEquals(itemName, true);
        if (!items.isEmpty()) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDTOList(items);

            return itemGetResponseDTOS;
        } else {
            throw new NotFoundException("Not found");
        }
    }

    /**
     * Saves a new item category.
     *
     * @param categoryDTO The DTO containing the details of the category to be saved.
     * @return A message indicating the success of the save operation.
     */
    @Override
    public String saveCategory(ItemCategoryDTO categoryDTO) {
        // Convert DTO to entity and save the category
        ItemCategory category = modelMapper.map(categoryDTO, ItemCategory.class);
        itemCategoryRepository.save(category);
        return "Category saved successfully";
    }

    /**
     * Saves an item with its associated category and supplier.
     *
     * @param itemSaveRequestCategoryDTO The DTO containing the details of the item and its associated category and supplier.
     * @return A message indicating the success of the save operation.
     * @throws NotFoundException If the associated category or supplier is not found.
     */
    @Override
    public String saveItemWithCategory(ItemSaveRequestCategoryDTO itemSaveRequestCategoryDTO) {
        // Check if category exists
        ItemCategory category = itemCategoryRepository.findById(itemSaveRequestCategoryDTO.getCategoryId())
                .orElseGet(() -> {

                    // If category doesn't exist, create a new one
                    ItemCategory newCategory = new ItemCategory();
                    // Set category properties if needed
                    // newCategory.setCategoryName(itemSaveRequestCategoryDTO.getCategoryName());
                    // newCategory.setCategoryDescription(itemSaveRequestCategoryDTO.getCategoryDescription());
                    // Save the new category
                    return itemCategoryRepository.save(newCategory);
                });

        // Check if supplier exists
        Supplier supplier = supplierRepository.findById(itemSaveRequestCategoryDTO.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier not found with ID: "
                        + itemSaveRequestCategoryDTO.getSupplierId())
                );

        // Check if branch exists
         Branch branch = branchRepository.findById(itemSaveRequestCategoryDTO.getBranchId())
                .orElseThrow(() -> new NotFoundException("Branch not found with ID: "
                        + itemSaveRequestCategoryDTO.getBranchId())
                );

        itemRepository.findById(itemSaveRequestCategoryDTO.getItemId())
                .ifPresent(item -> {
                    throw new EntityDuplicationException("Item already exists with ID: "
                            + itemSaveRequestCategoryDTO.getItemId());
                });

        // Now, associate the item with the category and supplier
        Item item = modelMapper.map(itemSaveRequestCategoryDTO, Item.class);
        item.setItemCategory(category);
        item.setSupplier(supplier); // Ensure the supplier is set
        item.setBranchId(itemSaveRequestCategoryDTO.getBranchId());

        itemRepository.save(item);
        return "Item saved successfully with category and supplier";

        //TODO: Need to get response of real item id now it shows in zero
    }

    /**
     * Retrieves all item categories.
     *
     * @return A list of DTOs representing all item categories.
     * @throws NotFoundException If no categories are found.
     */
    @Override
    public List<ItemCategoryDTO> getAllCategories() {
        List<ItemCategory> categories = itemCategoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories.stream()
                    .map(category -> modelMapper.map(category, ItemCategoryDTO.class))
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("No categories found");
        }
    }

    /**
     * Updates the details of an existing item category.
     *
     * @param categoryId The ID of the category to be updated.
     * @param categoryDTO The DTO containing the updated details of the category.
     * @return A message indicating the success of the update operation.
     * @throws NotFoundException If the category to be updated is not found.
     */
    @Override
    public String updateCategoryDetails(long categoryId, ItemCategoryDTO categoryDTO) {
        // Check if the category exists
        Optional<ItemCategory> optionalCategory = itemCategoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            ItemCategory category = optionalCategory.get();

            // Update category details
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setCategoryDescription(categoryDTO.getCategoryDescription());
            category.setCategoryImage(categoryDTO.getCategoryImage());

            // Save the updated category
            itemCategoryRepository.save(category);

            return "Category details updated successfully";
        } else {
            throw new NotFoundException("Category not found");
        }
    }

    /**
     * Deletes a category with the specified ID.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return A message indicating the success of the delete operation.
     * @throws NotFoundException If the category to be deleted is not found.
     */
    @Override
    public String deleteCategory(long categoryId) {
        // Check if the category exists
        Optional<ItemCategory> optionalCategory = itemCategoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            ItemCategory category = optionalCategory.get();

            // Check if there are any items associated with this category
            if (!category.getItems().isEmpty()) {
                throw new IllegalStateException("Cannot delete category with associated items");
            }

            // Delete the category
            itemCategoryRepository.delete(category);

            return "Category deleted successfully";
        } else {
            throw new NotFoundException("Category not found");
        }
    }
}