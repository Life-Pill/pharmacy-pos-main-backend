package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestCategoryDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.StandardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Item controller test.
 */
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test save item with category.
     */
    @Test
    void testSaveItemWithCategory() {
        ItemSaveRequestCategoryDTO itemSaveRequestCategoryDTO = new ItemSaveRequestCategoryDTO();
        String message = "Item saved successfully with category and supplier";
        when(itemService.saveItemWithCategory(itemSaveRequestCategoryDTO)).thenReturn(message);

        ResponseEntity<StandardResponse> responseEntity = itemController.saveItemWithCategory(itemSaveRequestCategoryDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(201, responseEntity.getBody().getCode());
        assertEquals("Successfully Saved Item", responseEntity.getBody().getMessage());
        assertEquals(message, responseEntity.getBody().getData());
    }

    /**
     * Test save item.
     */
    @Test
    void testSaveItem() {
        ItemSaveRequestDTO itemSaveRequestDTO = new ItemSaveRequestDTO();
        String message = "Item Saved Successful";
        when(itemService.saveItems(itemSaveRequestDTO)).thenReturn(message);

        ResponseEntity<StandardResponse> responseEntity = itemController.saveItem(itemSaveRequestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(201, responseEntity.getBody().getCode());
        assertEquals("Success", responseEntity.getBody().getMessage());
        assertEquals(message, responseEntity.getBody().getData());
    }

    /**
     * Test get all items.
     */
    @Test
    void testGetAllItems() {
        List<ItemGetAllResponseDTO> itemGetAllResponseDTOS = Arrays.asList(new ItemGetAllResponseDTO(), new ItemGetAllResponseDTO());
        when(itemService.getAllItems()).thenReturn(itemGetAllResponseDTOS);

        ResponseEntity<StandardResponse> responseEntity = itemController.getAllItems();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(201, responseEntity.getBody().getCode());
        assertEquals("SUCCESS", responseEntity.getBody().getMessage());
        assertEquals(itemGetAllResponseDTOS, responseEntity.getBody().getData());
    }

    /**
     * Test get item by name and stock.
     */
    @Test
    void testGetItemByNameAndStock() {
        String itemName = "Item 1";
        List<ItemGetResponseDTO> itemGetResponseDTOS = Arrays.asList(new ItemGetResponseDTO(), new ItemGetResponseDTO());
        when(itemService.getItemByName(itemName)).thenReturn(itemGetResponseDTOS);

        List<ItemGetResponseDTO> result = itemController.getItemByNameAndStock(itemName);

        assertEquals(itemGetResponseDTOS, result);
    }

    /**
     * Test get item by bar code.
     */
    @Test
    void testGetItemByBarCode() {
        String itemBarCode = "123456789";
        List<ItemGetResponseDTO> itemGetResponseDTOS = Arrays.asList(new ItemGetResponseDTO(), new ItemGetResponseDTO());
        when(itemService.getItemByBarCode(itemBarCode)).thenReturn(itemGetResponseDTOS);

        List<ItemGetResponseDTO> result = itemController.getItemByBarCode(itemBarCode);

        assertEquals(itemGetResponseDTOS, result);
    }

    /**
     * Test get item by name and status bymapstruct.
     */
    @Test
    void testGetItemByNameAndStatusBymapstruct() {
        String itemName = "Item 1";
        List<ItemGetResponseDTO> itemGetResponseDTOS = Arrays.asList(new ItemGetResponseDTO(), new ItemGetResponseDTO());
        when(itemService.getItemByNameAndStatusBymapstruct(itemName)).thenReturn(itemGetResponseDTOS);

        List<ItemGetResponseDTO> result = itemController.getItemByNameAndStatusByMapStruct(itemName);

        assertEquals(itemGetResponseDTOS, result);
    }

    /**
     * Test get all item by active status.
     */
    @Test
    void testGetAllItemByActiveStatus() {
        boolean activeStatus = true;
        List<ItemGetResponseDTO> itemGetResponseDTOS = Arrays.asList(new ItemGetResponseDTO(), new ItemGetResponseDTO());
        when(itemService.getItemByStockStatus(activeStatus)).thenReturn(itemGetResponseDTOS);

        ResponseEntity<StandardResponse> responseEntity = itemController.getAllItemByActiveStatus(activeStatus);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
        assertEquals("Success", responseEntity.getBody().getMessage());
        assertEquals(itemGetResponseDTOS, responseEntity.getBody().getData());
    }

    /**
     * Test update item.
     */
    @Test
    void testUpdateItem() {
        ItemUpdateDTO itemUpdateDTO = new ItemUpdateDTO();
        String message = "UPDATED ITEMS";
        when(itemService.updateItem(itemUpdateDTO)).thenReturn(message);

        String result = itemController.updateItem(itemUpdateDTO);

        assertEquals(message, result);
    }

    /**
     * Test delete item.
     */
    @Test
    void testDeleteItem() {
        int itemId = 1;
        String message = "deleted succesfully: " + itemId;
        when(itemService.deleteItem(itemId)).thenReturn(message);

        String result = itemController.deleteItem(itemId);

        assertEquals(message, result);
    }

    /**
     * Test get item by stock status lazy.
     */
    @Test
    void testGetItemByStockStatusLazy() {
        boolean activeStatus = true;
        int page = 0;
        int size = 10;
        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO();
        when(itemService.getItemByStockStatusWithPaginateed(activeStatus, page, size)).thenReturn(paginatedResponseItemDTO);

        ResponseEntity<StandardResponse> responseEntity = itemController.getItemByStockStatusLazy(activeStatus, page, size);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
        assertEquals("Success", responseEntity.getBody().getMessage());
        assertEquals(paginatedResponseItemDTO, responseEntity.getBody().getData());
    }
}