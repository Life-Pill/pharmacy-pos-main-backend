package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.ItemDTO;
import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItems(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName);
    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);
    List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus);
    String updateItem(ItemUpdateDTO itemUpdateDTO);
    String deleteItem(int itemId);

    List<ItemGetAllResponseDTO> getAllItems();

    //List<ItemGetResponseDTO> getItemByActiveStatusLazy(boolean activeStatus);

    PaginatedResponseItemDTO getItemByActiveStatusWithPaginateed(boolean activeStatus, int page, int size);
//    List<ItemGetResponseDTO> getAllItems();
}
