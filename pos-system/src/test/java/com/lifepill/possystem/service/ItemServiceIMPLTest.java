/*
package com.lifepill.possystem.service;

import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.service.impl.ItemServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

*/
/**
 * The type Item service impl test.
 *//*

public class ItemServiceIMPLTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceIMPL itemService;

    */
/**
     * Sets up.
     *//*

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    */
/**
     * Gets item by name and stock item not found exception thrown.
     *//*

    @Test
    public void getItemByNameAndStock_ItemNotFound_ExceptionThrown() {
        // Arrange
        String itemName = "Non-existent Item";
        // Mocking behavior of the repository to return an empty list, indicating no item found
        when(itemRepository.findAllByItemNameEqualsAndStockEquals(anyString(), anyBoolean())).thenReturn(List.of());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> itemService.getItemByName(itemName));
    }

    */
/**
     * Gets item by stock status no active items found exception thrown.
     *//*

    @Test
    public void getItemByStockStatus_NoActiveItemsFound_ExceptionThrown() {
        // Arrange
        // Mocking behavior of the repository to return an empty list, indicating no active items found
        when(itemRepository.findAllByStockEquals(anyBoolean())).thenReturn(List.of());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> itemService.getItemByStockStatus(true));
    }

}
*/
