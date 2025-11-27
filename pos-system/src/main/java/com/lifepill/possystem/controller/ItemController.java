package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestCategoryDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.*;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * Saves an item along with its image file.
     *
     * @param file                      The image file to be saved.
     * @param itemSaveRequestCategoryDTO The DTO containing details of the item to be saved.
     * @return ResponseEntity containing a StandardResponse object with the saved item details.
     * @throws IOException If an I/O error occurs.
     */
    @PostMapping(path = "/save-item-with-image")
    public ResponseEntity<StandardResponse> saveItemWithImage(
            @RequestParam(value = "file") MultipartFile file,
            @ModelAttribute ItemSaveRequestCategoryDTO itemSaveRequestCategoryDTO
    ) throws IOException {
        ItemSaveRequestCategoryDTO savedDTO = itemService
                .createItemWithImage(file, itemSaveRequestCategoryDTO);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Successfully Saved Item",
                        savedDTO
                ),
                HttpStatus.CREATED
        );
    }

    /**
     * Retrieves the image of an item from the S3 bucket.
     *
     * @param itemId The ID of the item whose image is to be retrieved.
     * @return ResponseEntity containing the image stream and necessary headers.
     */
    @GetMapping(value = "/view-item-image/{itemId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getBranchProfileImage(@PathVariable long itemId) {
        ItemGetResponseWithoutSupplierDetailsDTO itemSaveRequestCategoryDTO =
                itemService.getItemById(itemId);
        InputStreamResource inputStreamResource =
                itemService.getItemImage(
                        itemSaveRequestCategoryDTO.getItemGetAllResponseDTO().getItemImage()
                );

        String itemImageUrl = itemSaveRequestCategoryDTO.getItemGetAllResponseDTO().getItemImage();
        String keyName = itemImageUrl.substring(itemImageUrl.lastIndexOf("/") + 1);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + keyName + "\"")
                .body(inputStreamResource);
    }

    /**
     * Updates the image of an item in the S3 bucket.
     *
     * @param itemId The ID of the item whose image is to be updated.
     * @param file   The new image file to be uploaded.
     * @return ResponseEntity containing a StandardResponse object with the update status.
     * @throws IOException If an I/O error occurs.
     */
    @PutMapping("/update-item-image/{itemId}")
    public ResponseEntity<StandardResponse> updateBranchProfileImage(
            @PathVariable long itemId,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        itemService.updateItemImage(itemId, file);
        return new ResponseEntity<>(
                new StandardResponse(201, "SUCCESS", "Item profile image updated"),
                HttpStatus.OK
        );
    }

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
                new StandardResponse(
                        201,
                        "Successfully Saved Item",
                        message
                ),
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
    public ResponseEntity<StandardResponse> saveItem(
            @RequestBody ItemSaveRequestDTO itemSaveRequestDTO
    ) {
        String message = itemService.saveItems(itemSaveRequestDTO);

        return new ResponseEntity<>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED);
    }

    /**
     * Retrieves all items.
     *
     * @return ResponseEntity containing a StandardResponse object with a list of all items.
     */
    @GetMapping(path = "get-all-items")
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemGetAllResponseDTO> allItems = itemService.getAllItems();
        return new ResponseEntity<>(
                new StandardResponse(201, "SUCCESS", allItems),
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
    public List<ItemGetResponseDTO> getItemByNameAndStock(
            @RequestParam(value = "itemName") String itemName
    ) {
        return itemService.getItemByName(itemName);
    }

    /**
     * Retrieves items by barcode.
     *
     * @param itemBarCode The barcode of the item to retrieve.
     * @return List of ItemGetResponseDTO containing items with the specified barcode.
     */
    @GetMapping(path = "/get-by-barcode", params = "barcode")
    public List<ItemGetResponseDTO> getItemByBarCode(
            @RequestParam(value = "barcode") String itemBarCode
    ) {
        return itemService.getItemByBarCode(itemBarCode);
    }

    /**
     * Retrieves items by name and status using MapStruct.
     *
     * @param itemName The name of the item to retrieve.
     * @return List of ItemGetResponseDTO containing items with the specified name and status.
     */
    //TODO: need to improvement
    @GetMapping(path = "/get-by-name-with-mapstruct", params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapStruct(
            @RequestParam(value = "name") String itemName
    ) {
        return itemService.getItemByNameAndStatusBymapstruct(itemName);
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
        return new ResponseEntity<>(
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
    public String updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO) {
        return itemService.updateItem(itemUpdateDTO);
    }

    /**
     * Deletes an item by its ID.
     *
     * @param itemId The ID of the item to be deleted.
     * @return A message indicating the result of the delete operation.
     */
    @DeleteMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable(value = "id") int itemId) {
        return itemService.deleteItem(itemId);
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
            path = "/get-all-item-by-status-lazy-initialized",
            params = {"activeStatus", "page", "size"}
    )
    public ResponseEntity<StandardResponse> getItemByStockStatusLazy(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
            // @RequestParam(value = "size") @Max(50)int size
    ) {
        PaginatedResponseItemDTO paginatedResponseItemDTO =
                itemService.getItemByStockStatusWithPaginateed(activeStatus, page, size);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Success",
                        paginatedResponseItemDTO
                ),
                HttpStatus.OK
        );
    }

    /**
     * Saves an item category.
     *
     * @return A message indicating the result of the save operation.
     */
    @GetMapping(path = "/get-item-all-details-by-id/{id}")
    public ResponseEntity<StandardResponse> getItemAllDetailsById(
            @PathVariable(value = "id") long itemId
    ) {
        ItemGetIdResponseDTO itemGetIdResponseDTO = itemService.getAllDetailsItemById(itemId);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Success",
                        itemGetIdResponseDTO
                ),
                HttpStatus.OK
        );
    }

    /**
     * Endpoint for retrieving all details of an item by its ID using the old method.
     * This endpoint is mapped to the "/get-item-all-details-by-id-old/{id}" URL and
     * responds to HTTP GET requests.
     *
     * @param itemId The ID of the item to be retrieved. This is a path variable.
     * @return ResponseEntity containing a StandardResponse object with the item details.
     * The HTTP status is 200 (OK) if the operation is successful.
     */
    @GetMapping(path = "/get-item-all-details-by-id-old/{id}")
    public ResponseEntity<StandardResponse> getItemAllDetailsByIdOld(
            @PathVariable(value = "id") long itemId
    ) {
        ItemGetIdOldResponseDTO itemGetIdOldResponseDTO = itemService.getAllDetailsItemByIdOld(itemId);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Success",
                        itemGetIdOldResponseDTO
                ),
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
    public ResponseEntity<StandardResponse> getItemWithCategoryById(@PathVariable(value = "id") long itemId) {
        ItemGetResponseWithoutSupplierDetailsDTO itemGetResponsewithoutSupplierDetailsDTO =
                itemService.getItemById(itemId);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Success",
                        itemGetResponsewithoutSupplierDetailsDTO
                ),
                HttpStatus.OK
        );
    }

    /**
     * Endpoint for retrieving items by their associated branch ID.
     * This endpoint is mapped to the "/branched/get-item/{branchId}" URL and
     * responds to HTTP GET requests.
     *
     * @param branchId The ID of the branch whose items are to be retrieved.
     *                This is a path variable.
     * @return ResponseEntity containing a StandardResponse object with a list of items
     * associated with the branch.
     * The HTTP status is 200 (OK) if the operation is successful.
     */
    @GetMapping(path = "/branched/get-item/{branchId}")
    public ResponseEntity<StandardResponse> getItemByBranchId(
            @PathVariable(value = "branchId") long branchId
    ) {
        List<ItemGetResponseDTO> itemGetResponseDTO = itemService.getItemByBranchId(branchId);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Success",
                        itemGetResponseDTO
                ),
                HttpStatus.OK
        );
    }
}