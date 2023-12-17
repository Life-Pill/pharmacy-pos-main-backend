package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItems(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName);
    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);
    List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus);
    String updateItem(ItemUpdateDTO itemUpdateDTO);
    String deleteItem(int itemId);
}
