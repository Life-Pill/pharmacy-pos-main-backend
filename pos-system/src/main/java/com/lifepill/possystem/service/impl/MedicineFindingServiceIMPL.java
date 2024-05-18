package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.itemRepository.ItemCategoryRepository;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.repo.supplierRepository.SupplierRepository;
import com.lifepill.possystem.service.MedicineFindingService;
import com.lifepill.possystem.util.mappers.ItemMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicineFindingServiceIMPL implements MedicineFindingService {


    private ItemRepository itemRepository;


    private BranchRepository branchRepository;

    private ModelMapper modelMapper;

    private ItemMapper itemMapper;

    private ItemCategoryRepository itemCategoryRepository;

    private SupplierRepository supplierRepository;

    @Override
    public List<MedicineGetResponseDTO> getItemByName(String itemName) {
        List<Item> items = itemRepository.findAllByItemName(itemName);

        if (items.isEmpty()) {
            throw new NotFoundException("No item found with the name " + itemName);
        }

        List<MedicineGetResponseDTO> medicineGetResponseDTOS = new ArrayList<>();

        for (Item item : items) {
            MedicineGetResponseDTO medicineGetResponseDTO = modelMapper.map(item, MedicineGetResponseDTO.class);
            medicineGetResponseDTO.setItemCategoryDTO(modelMapper.map(item.getItemCategory(), ItemCategoryDTO.class));

            Branch branch = branchRepository.findById(item.getBranchId()).orElse(null);
            if (branch != null) {
                medicineGetResponseDTO.setBranchDTO(modelMapper.map(branch, BranchDTO.class));
            }

            medicineGetResponseDTOS.add(medicineGetResponseDTO);
        }

        return medicineGetResponseDTOS;
    }
}
