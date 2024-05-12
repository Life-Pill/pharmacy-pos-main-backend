package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.service.impl.ItemServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemServiceIMPLTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceIMPL itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getItemByNameAndStock_ItemNotFound_ExceptionThrown() {
        // Arrange
        String itemName = "Non-existent Item";
        // Mocking behavior of the repository to return an empty list, indicating no item found
        when(itemRepository.findAllByItemNameEqualsAndStockEquals(anyString(), anyBoolean())).thenReturn(List.of());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> itemService.getItemByNameAndStock(itemName));
    }

    @Test
    public void getItemByStockStatus_NoActiveItemsFound_ExceptionThrown() {
        // Arrange
        // Mocking behavior of the repository to return an empty list, indicating no active items found
        when(itemRepository.findAllByStockEquals(anyBoolean())).thenReturn(List.of());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> itemService.getItemByStockStatus(true));
    }

}
