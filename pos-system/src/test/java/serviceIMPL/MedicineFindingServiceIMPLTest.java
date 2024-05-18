package serviceIMPL;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.entity.ItemCategory;
import com.lifepill.possystem.entity.enums.MeasuringUnitType;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.itemRepository.ItemCategoryRepository;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.repo.supplierRepository.SupplierRepository;
import com.lifepill.possystem.service.impl.MedicineFindingServiceIMPL;
import com.lifepill.possystem.util.mappers.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MedicineFindingServiceIMPLTest {

    @InjectMocks
    private MedicineFindingServiceIMPL medicineFindingServiceIMPL;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ItemMapper itemMapper;

    @Mock
    private ItemCategoryRepository itemCategoryRepository;

    @Mock
    private SupplierRepository supplierRepository;

    private Item item;
    private Branch branch;
    private ItemCategory itemCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        branch = new Branch();
        branch.setBranchId(1L);
        branch.setBranchName("Test Branch");

        itemCategory = new ItemCategory();
        itemCategory.setCategoryId(1L);
        itemCategory.setCategoryName("Test Category");

        item = new Item();
        item.setItemId(1L);
        item.setItemName("Paracetamol");
        item.setBranchId(1L);
        item.setItemCategory(itemCategory);
        item.setMeasuringUnitType(MeasuringUnitType.CAPSULE);
    }

    @Test
    void testGetItemByName_ItemFound() {
        String itemName = "Paracetamol";
        when(itemRepository.findAllByItemName(itemName)).thenReturn(Collections.singletonList(item));
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(modelMapper.map(item, MedicineGetResponseDTO.class)).thenReturn(new MedicineGetResponseDTO());
        when(modelMapper.map(branch, BranchDTO.class)).thenReturn(new BranchDTO());
        when(modelMapper.map(itemCategory, ItemCategoryDTO.class)).thenReturn(new ItemCategoryDTO());

        List<MedicineGetResponseDTO> result = medicineFindingServiceIMPL.getItemByName(itemName);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testGetItemByName_NoItemFound() {
        String itemName = "NonExistingItem";
        when(itemRepository.findAllByItemName(itemName)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> medicineFindingServiceIMPL.getItemByName(itemName));
    }
}