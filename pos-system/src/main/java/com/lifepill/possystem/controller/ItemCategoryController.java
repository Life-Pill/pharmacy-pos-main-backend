package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing item category-related operations.
 */
@RestController
@RequestMapping("/lifepill/v1/item-Category")
@CrossOrigin(origins =  "http://localhost:3000")
@AllArgsConstructor
public class ItemCategoryController {

    private ItemService itemService;

    /**
     * Saves a new item category.
     *
     * @param categoryDTO The DTO containing details of the item category to be saved.
     * @return ResponseEntity containing a StandardResponse object with a success message.
     */
    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveCategory(@RequestBody ItemCategoryDTO categoryDTO) {

        String message = itemService.saveCategory(categoryDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED);
    }

    /**
     * Retrieves all item categories.
     *
     * @return ResponseEntity containing a StandardResponse object with a list of all item categories.
     */
    @GetMapping("/all-category")
    public ResponseEntity<StandardResponse> getAllCategories() {
        List<ItemCategoryDTO> categories = itemService.getAllCategories();
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", categories),
                HttpStatus.OK
        );
    }

    /**
     * Updates details of an item category.
     *
     * @param categoryId The ID of the category to update.
     * @param categoryDTO The DTO containing updated details of the category.
     * @return ResponseEntity containing a StandardResponse object with a success message.
     */
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<StandardResponse> updateCategoryDetails(
            @PathVariable long categoryId,
            @RequestBody ItemCategoryDTO categoryDTO
    ) {
        String message = itemService.updateCategoryDetails(categoryId, categoryDTO);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.OK);
    }

    /**
      * Deletes an item category.
            *
            * @param categoryId The ID of the category to delete.
            * @return ResponseEntity containing a StandardResponse object with a success message.
     * */
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<StandardResponse> deleteCategory(@PathVariable long categoryId) {
        String message = itemService.deleteCategory(categoryId);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.OK);
    }
}
