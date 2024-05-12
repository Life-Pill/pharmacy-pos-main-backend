package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.ItemCategoryDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The type Item category controller test.
 */
class ItemCategoryControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemCategoryController itemCategoryController;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test get all categories.
     */
    @Test
    void testGetAllCategories() {
        // Arrange
        List<ItemCategoryDTO> categories = Arrays.asList(
                new ItemCategoryDTO(1L, "Category 1", "Description 1", "image1.jpg"),
                new ItemCategoryDTO(2L, "Category 2", "Description 2", "image2.jpg")
        );
        when(itemService.getAllCategories()).thenReturn(categories);

        // Act
        ResponseEntity<StandardResponse> responseEntity = itemCategoryController.getAllCategories();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
        assertEquals(categories, responseEntity.getBody().getData());
    }

    /**
     * Test get all categories success.
     */
    @Test
    void testGetAllCategories_Success() {
        // Arrange
        List<ItemCategoryDTO> categories = Arrays.asList(
                new ItemCategoryDTO(1L, "Category 1", "Description 1", "image1.jpg"),
                new ItemCategoryDTO(2L, "Category 2", "Description 2", "image2.jpg")
        );
        when(itemService.getAllCategories()).thenReturn(categories);

        // Act
        ResponseEntity<StandardResponse> responseEntity = itemCategoryController.getAllCategories();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
        assertEquals(categories, responseEntity.getBody().getData());
    }

    /**
     * Test get all categories no categories.
     */
    @Test
    void testGetAllCategories_NoCategories() {
        // Arrange
        when(itemService.getAllCategories()).thenReturn(List.of());

        // Act
        ResponseEntity<StandardResponse> responseEntity = itemCategoryController.getAllCategories();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
        assertTrue(responseEntity.getBody().getData() instanceof List);
        assertEquals(0, ((List<?>) responseEntity.getBody().getData()).size());
    }

    /**
     * Test update category details category not found.
     */
    @Test
    void testUpdateCategoryDetails_CategoryNotFound() {
        // Arrange
        long categoryId = 1L;
        ItemCategoryDTO categoryDTO = new ItemCategoryDTO(categoryId, "Updated Category", "Updated Description", "updated_image.jpg");
        String expectedMessage = "Category not found";
        when(itemService.updateCategoryDetails(categoryId, categoryDTO)).thenThrow(new RuntimeException(expectedMessage));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> itemCategoryController.updateCategoryDetails(categoryId, categoryDTO), expectedMessage);
    }

    /**
     * Test delete category category not found.
     */
    @Test
    void testDeleteCategory_CategoryNotFound() {
        // Arrange
        long categoryId = 1L;
        String expectedMessage = "Category not found";
        when(itemService.deleteCategory(categoryId)).thenThrow(new RuntimeException(expectedMessage));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> itemCategoryController.deleteCategory(categoryId), expectedMessage);
    }

    /**
     * Test delete category category with associated items.
     */
    @Test
    void testDeleteCategory_CategoryWithAssociatedItems() {
        // Arrange
        long categoryId = 1L;
        String expectedMessage = "Cannot delete category with associated items";
        when(itemService.deleteCategory(categoryId)).thenThrow(new IllegalStateException(expectedMessage));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> itemCategoryController.deleteCategory(categoryId), expectedMessage);
    }

    /**
     * Test save category.
     */
    @Test
    void testSaveCategory() {
        // Arrange
        ItemCategoryDTO categoryDTO = new ItemCategoryDTO(1L, "Category 1", "Description 1", "image1.jpg");
        String expectedMessage = "Category saved successfully";
        when(itemService.saveCategory(categoryDTO)).thenReturn(expectedMessage);

        // Act
        ResponseEntity<StandardResponse> responseEntity = itemCategoryController.saveCategory(categoryDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(201, responseEntity.getBody().getCode());
       // assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }

    /**
     * Test save category category already exists.
     */
    @Test
    void testSaveCategory_CategoryAlreadyExists() {
        // Arrange
        ItemCategoryDTO categoryDTO = new ItemCategoryDTO(1L, "Category 1", "Description 1", "image1.jpg");
        String expectedMessage = "Category already exists";
        when(itemService.saveCategory(categoryDTO)).thenThrow(new IllegalStateException(expectedMessage));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> itemCategoryController.saveCategory(categoryDTO), expectedMessage);
    }

    /**
     * Test update category details.
     */
    @Test
    void testUpdateCategoryDetails() {
        // Arrange
        long categoryId = 1L;
        ItemCategoryDTO categoryDTO = new ItemCategoryDTO(categoryId, "Updated Category", "Updated Description", "updated_image.jpg");
        String expectedMessage = "Category updated successfully";
        when(itemService.updateCategoryDetails(categoryId, categoryDTO)).thenReturn(expectedMessage);

        // Act
        ResponseEntity<StandardResponse> responseEntity = itemCategoryController.updateCategoryDetails(categoryId, categoryDTO);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
    }

    /**
     * Test delete category.
     */
    @Test
    void testDeleteCategory() {
        // Arrange
        long categoryId = 1L;
        String expectedMessage = "Category deleted successfully";
        when(itemService.deleteCategory(categoryId)).thenReturn(expectedMessage);

        // Act
        ResponseEntity<StandardResponse> responseEntity = itemCategoryController.deleteCategory(categoryId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
        //assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }

}