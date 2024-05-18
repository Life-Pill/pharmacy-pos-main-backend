package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.service.MedicineFindingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the MedicineFindingService interface.
 * Provides methods to find items (medicines) by name.
 */
@Service
@AllArgsConstructor
public class MedicineFindingServiceIMPL implements MedicineFindingService {

    private ItemRepository itemRepository;
    private BranchRepository branchRepository;
    private ModelMapper modelMapper;

    /**
     * Retrieves a list of MedicineGetResponseDTO objects representing items with the given name.
     *
     * @param itemName the name of the item to search for
     * @return a list of MedicineGetResponseDTO objects representing the found items
     * @throws NotFoundException if no items are found with the given name
     */
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
