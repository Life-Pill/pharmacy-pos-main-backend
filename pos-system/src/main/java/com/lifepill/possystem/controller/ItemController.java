package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.ItemDTO;
import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestCategoryDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetIdResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponsewithoutSupplierDetailsDTO;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.MapKeyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing item-related operations.
 */
@RestController
@RequestMapping("/lifepill/v1/item")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    /**
     * Saves an item along with its category.
     *
     * @param itemSaveRequestCategoryDTO The DTO containing details of the item and its category to be saved.
     * @return ResponseEntity containing a StandardResponse object with a success message.
     */
    @PostMapping(path = "/save-item")
    public ResponseEntity<StandardResponse> saveItemWithCategory(
            @RequestBody ItemSaveRequestCategoryDTO itemSaveRequestCategoryDTO
    ) {
        String message = itemService.saveItemWithCategory(itemSaveRequestCategoryDTO);

        return new ResponseEntity<>(
                new StandardResponse(201, "Successfully Saved Item", message),
                HttpStatus.CREATED);
    }

    /**
     * Saves an item.
     *
     * @param itemSaveRequestDTO The DTO containing details of the item to be saved.
     * @return ResponseEntity containing a StandardResponse object with a success message.
     */
    //TODO: need to improvement don't use saveItems for this use save item
    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String message = itemService.saveItems(itemSaveRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED);
    }

    /**
     * Retrieves all items.
     *
     * @return ResponseEntity containing a StandardResponse object with a list of all items.
     */
    @CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
    @GetMapping(path = "get-all-items")
    public ResponseEntity<StandardResponse> getAllItems(){
        List<ItemGetAllResponseDTO> allItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"SUCCESS",allItems),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves items by name and stock.
     *
     * @param itemName The name of the item to retrieve.
     * @return List of ItemGetResponseDTO containing items with the specified name and stock.
     */
    @GetMapping(path = "/get-by-name", params = "itemName")
    public List<ItemGetResponseDTO> getItemByNameAndStock(@RequestParam(value = "itemName") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByName(itemName);
        return itemDTOS;
    }

    /**
     * Retrieves items by barcode.
     *
     * @param itemBarCode The barcode of the item to retrieve.
     * @return List of ItemGetResponseDTO containing items with the specified barcode.
     */
    @GetMapping(path = "/get-by-barcode", params = "barcode")
    public List<ItemGetResponseDTO> getItemByBarCode(@RequestParam(value = "barcode") String itemBarCode) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByBarCode(itemBarCode);
        return itemDTOS;
    }

    /**
     * Retrieves items by name and status using MapStruct.
     *
     * @param itemName The name of the item to retrieve.
     * @return List of ItemGetResponseDTO containing items with the specified name and status.
     */
    //TODO: need to improvement
    @GetMapping(path = "/get-by-name-with-mapstruct", params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusBymapstruct(itemName);
        return itemDTOS;
    }

    /**
     * Retrieves all items by their active status.
     *
     * @param activeStatus The status indicating whether the items are active or not.
     * @return ResponseEntity containing a StandardResponse object with a list of items based on their active status.
     */
    @GetMapping(path = "/get-all-item-by-status", params = "activeStatus")
    public ResponseEntity<StandardResponse> getAllItemByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByStockStatus(activeStatus);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,
                        "Success", itemDTOS),
                HttpStatus.OK);
    }

    /**
     * Handles a test POST request.
     *
     * @return ResponseEntity containing a success message for the POST request.
     */
    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }

    /**
     * Updates an item.
     *
     * @param itemUpdateDTO The DTO containing updated item information.
     * @return A message indicating the result of the update operation.
     */
    @PutMapping("/update")
    public String updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO){
        String message = itemService.updateItem(itemUpdateDTO);
        return message;
    }

    /**
     * Deletes an item by its ID.
     *
     * @param itemId The ID of the item to be deleted.
     * @return A message indicating the result of the delete operation.
     */
    @DeleteMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable(value = "id") int itemId) {
        String delete = itemService.deleteItem(itemId);
        return delete;
    }

    /**
     * Retrieves all items by their active status with lazy initialization.
     *
     * @param activeStatus The status indicating whether the items are active or not.
     * @param page         The page number for pagination.
     * @param size         The number of items per page for pagination.
     * @return ResponseEntity containing a StandardResponse object with paginated items based on their active status.
     */
    @GetMapping(
            path = "/get-all-item-by-status-lazy-initailized",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemByStockStatusLazy(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
            // @RequestParam(value = "size") @Max(50)int size
            ){
       // size = 10; // only load 10 data
        //List<ItemGetResponseDTO> itemDTOS = itemService.getItemByActiveStatusLazy(activeStatus);
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByStockStatusWithPaginateed(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }

    /**
     * Saves an item category.
     *
     * @return A message indicating the result of the save operation.
     */
    @GetMapping(path = "/get-item-all-details-by-id/{id}")
    public ResponseEntity<StandardResponse> getItemAllDetailsById(@PathVariable(value = "id") long itemId){
        ItemGetIdResponseDTO itemGetIdResponseDTO = itemService.getAllDetailsItemById(itemId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",itemGetIdResponseDTO),
                HttpStatus.OK
        );
    }

    /**
     * Gets item with category by id.
     *
     * @param itemId the item id
     * @return the item with category by id
     */
    @GetMapping(path = "/get-item-details-by-id/{id}")
    public ResponseEntity<StandardResponse> getItemWithCategoryById(@PathVariable(value = "id") long itemId){
        ItemGetResponsewithoutSupplierDetailsDTO itemGetResponsewithoutSupplierDetailsDTO = itemService.getItemById(itemId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",itemGetResponsewithoutSupplierDetailsDTO),
                HttpStatus.OK
        );
    }
}