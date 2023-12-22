package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.ItemDTO;
import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.ItemRepo;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

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
        List<Item> item = itemRepo.findAllByActiveStatusEquals(activeStatus);
        if(!item.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    item,
                    new TypeToken<List<ItemGetResponseDTO>>(){}.getType()
            );
            return itemGetResponseDTOS;
        }else{
            throw new RuntimeException("out of Stock");
        }
    }


    @Override
    public String updateItem(ItemUpdateDTO itemUpdateDTO) {
        if(itemRepo.existsById(itemUpdateDTO.getItemId())){
            Item item = itemRepo.getReferenceById(itemUpdateDTO.getItemId());
            item.setItemName(itemUpdateDTO.getItemName());
            item.setMeasuringUnitType(itemUpdateDTO.getMeasuringUnitType());
            item.setBalanceQuantity(itemUpdateDTO.getBalanceQuantity());
            item.setStock(itemUpdateDTO.getStock());
            item.setSupplierPrice(itemUpdateDTO.getSupplierPrice());
            item.setSellingPrice(itemUpdateDTO.getSellingPrice());
            item.setActiveStatus(itemUpdateDTO.isActiveStatus());

            itemRepo.save(item);

            System.out.println(item);

            return "UPDATED ITEMS";
        }else {
            throw new RuntimeException("no Item found in that date");
        }
    }

    @Override
    public String deleteItem(int itemId) {
        if (itemRepo.existsById(itemId)){
            itemRepo.deleteById(itemId);

            return "deleted succesfully: "+ itemId;
        }else {
            throw new RuntimeException("No item found for that id");
        }
    }

    @Override
    public List<ItemGetAllResponseDTO> getAllItems() {
        List<Item> getAllItems = itemRepo.findAll();

        if(!getAllItems.isEmpty()){
            List<ItemGetAllResponseDTO> itemGetAllResponseDTOSList = new ArrayList<>();
            for (Item item:getAllItems){
                ItemGetAllResponseDTO itemGetAllResponseDTO = new ItemGetAllResponseDTO(
                        item.getItemId(),
                        item.getItemName(),
                        item.getBalanceQuantity(),
                        item.getStock(),
                        item.getSupplierPrice(),
                        item.getSellingPrice(),
                        item.isActiveStatus()
                );
                itemGetAllResponseDTOSList.add(itemGetAllResponseDTO);
            }
            return itemGetAllResponseDTOSList;
        }else {
            throw new RuntimeException("No Item Find or OUT of Stock");
        }
    }

 /*   @Override
    public List<ItemGetResponseDTO> getItemByActiveStatusLazy(boolean activeStatus) {
        List<Item> items = itemRepo.findAlByActiveStatusEquals(activeStatus);
        if (!items.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDTOList(items);

            return itemGetResponseDTOS;
        }else {
            throw new NotFoundException("Not found");
        }
    }*/
// paginated 7/2:27
    @Override
    public PaginatedResponseItemDTO getItemByActiveStatusWithPaginateed(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStatusEquals(activeStatus, PageRequest.of(page, size));

        if (items.getSize()<1){
            throw new NotFoundException("No Data");
        }else {
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.ListDTOToPage(items),
                itemRepo.countAllByActiveStatusEquals(activeStatus)
            );
            return paginatedResponseItemDTO;
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStatusEquals(itemName,true);
        if (!items.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDTOList(items);

            return itemGetResponseDTOS;
        }else {
            throw new RuntimeException("Not found");
        }
    }

}
