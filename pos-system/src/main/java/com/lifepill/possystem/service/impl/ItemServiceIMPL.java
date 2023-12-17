package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.repo.ItemRepo;
import com.lifepill.possystem.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveItems(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        if (!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemName() + " Saved Successfull";
        }else{
            throw new DuplicateKeyException("Already added this ID");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStatusEquals(itemName,true);
        if (!items.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    items,
                    new TypeToken<List<ItemGetResponseDTO>>(){}.getType()
            );
            return itemGetResponseDTOS;
        }else {
            throw new RuntimeException("Not found");
        }
    }
    @Override
    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus) {
        return null;
    }
    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName) {
        return null;
    }

}
