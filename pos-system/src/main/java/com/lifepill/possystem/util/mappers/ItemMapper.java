package com.lifepill.possystem.util.mappers;

import com.lifepill.possystem.dto.ItemDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    // itemList ----> ItemResponseDTO
    List<ItemGetResponseDTO> entityListToDTOList(List<Item> items);
    List<ItemGetAllResponseDTO> entityListAllItemToDTO(List<Item> items);

    //Page<Item> items ---> List<ItemGetResponseDTO> list;
    List<ItemGetResponseDTO>ListDTOToPage(Page<Item> items);
}
