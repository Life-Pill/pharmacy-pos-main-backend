package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.dto.ItemDTO;
import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestCategoryDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetIdResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItems(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName);
    List<ItemGetResponseDTO> getItemByName(String itemName);

    List<ItemGetResponseDTO> getItemByStockStatus(boolean activeStatus);
    String updateItem(ItemUpdateDTO itemUpdateDTO);
    String deleteItem(long itemId);
    List<ItemGetAllResponseDTO> getAllItems();

    //List<ItemGetResponseDTO> getItemByActiveStatusLazy(boolean activeStatus);
    PaginatedResponseItemDTO getItemByStockStatusWithPaginateed(boolean activeStatus, int page, int size);

    List<ItemGetResponseDTO> getItemByBarCode(String itemBarCode);
//    List<ItemGetResponseDTO> getAllItems();
    String saveCategory(ItemCategoryDTO categoryDTO);

    String saveItemWithCategory(ItemSaveRequestCategoryDTO itemSaveRequestCategoryDTO);

    List<ItemCategoryDTO> getAllCategories();

    String updateCategoryDetails(long categoryId, ItemCategoryDTO categoryDTO);

    String deleteCategory(long categoryId);

    ItemGetIdResponseDTO getItemById(long itemId);

    ItemGetIdResponseDTO getAllDetailsItemById(long itemId);
}
