package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.itemRepo.ItemRepo;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
            throw new EntityDuplicationException("Already added this Id item");
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
                        item.getSellingPrice(),
                        item.getItemBarCode(),
                        item.getSupplyDate(),
                        item.getSupplierPrice(),
                        item.isFreeIssued(),
                        item.isDiscounted(),
                        item.getItemManufacture(),
                        item.getItemQuantity(),
                        item.getItemCategory(),
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
        }else {
            throw new NotFoundException("No Item Find or OUT of Stock");
        }
    }


    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStock(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndStockEquals(itemName,true);
        if (!items.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    items,
                    new TypeToken<List<ItemGetResponseDTO>>(){}.getType()
            );
            return itemGetResponseDTOS;
        }else {
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByStockStatus(boolean activeStatus) {
        List<Item> item = itemRepo.findAllByStockEquals(activeStatus);
        if(!item.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    item,
                    new TypeToken<List<ItemGetResponseDTO>>(){}.getType()
            );
            return itemGetResponseDTOS;
        }else{
            throw new NotFoundException("out of Stock");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByBarCode(String itemBarCode) {
        List<Item> item = itemRepo.findAllByItemBarCodeEquals(itemBarCode);
        if(!item.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(
                    item,
                    new TypeToken<List<ItemGetResponseDTO>>(){}.getType()
            );
            return itemGetResponseDTOS;
        }else{
            throw new NotFoundException("No any item found for that barcode");
        }
    }


    @Override
    public String updateItem(ItemUpdateDTO itemUpdateDTO) {
        if(itemRepo.existsById(itemUpdateDTO.getItemId())){
            Item item = itemRepo.getReferenceById(itemUpdateDTO.getItemId());
            item.setItemName(itemUpdateDTO.getItemName());
            item.setItemBarCode(itemUpdateDTO.getItemBarCode());
            item.setSupplyDate(itemUpdateDTO.getSupplyDate());
            item.setFreeIssued(itemUpdateDTO.isFreeIssued());
            item.setDiscounted(itemUpdateDTO.isDiscounted());
            item.setItemManufacture(itemUpdateDTO.getItemManufacture());
            item.setItemQuantity(itemUpdateDTO.getItemQuantity());
            item.setItemCategory(itemUpdateDTO.getItemCategory());
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

            itemRepo.save(item);

            System.out.println(item);

            return "UPDATED ITEMS";
        }else {
            throw new NotFoundException("no Item found in that date");
        }
    }

    @Override
    public String deleteItem(int itemId) {
        if (itemRepo.existsById(itemId)){
            itemRepo.deleteById(itemId);

            return "deleted succesfully: "+ itemId;
        }else {
            throw new NotFoundException("No item found for that id");
        }
    }



// paginated 7/2:27
    @Override
    public PaginatedResponseItemDTO getItemByStockStatusWithPaginateed(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByStockEquals(activeStatus, PageRequest.of(page, size));

        if (items.getSize()<1){
            throw new NotFoundException("No Data");
        }else {
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.ListDTOToPage(items),
                itemRepo.countAllByStockEquals(activeStatus)
            );
            return paginatedResponseItemDTO;
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndStockEquals(itemName,true);
        if (!items.isEmpty()){
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDTOList(items);

            return itemGetResponseDTOS;
        }else {
            throw new NotFoundException("Not found");
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
}