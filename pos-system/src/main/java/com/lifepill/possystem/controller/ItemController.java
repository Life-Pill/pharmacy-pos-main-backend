package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.ItemDTO;
import com.lifepill.possystem.dto.paginated.PaginatedResponseItemDTO;
import com.lifepill.possystem.dto.requestDTO.ItemUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetAllResponseDTO;
import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lifepill/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String message = itemService.saveItems(itemSaveRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED);
    }

//    @GetMapping(path = "/get-all-item")
//    public  ResponseEntity<StandardResponse> getAllItemsResponse(){
//        List<ItemGetAllResponseDTO> allItems = itemService.getAllItems();
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"SUCCESS",allItems),
//                HttpStatus.OK
//        );
//    }

    @GetMapping(path = "get-all-items")
    public ResponseEntity<StandardResponse> getAllItems(){
        List<ItemGetAllResponseDTO> allItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"SUCCESS",allItems),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-name", params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStock(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStock(itemName);
        return itemDTOS;
    }

    @GetMapping(path = "/get-by-barcode", params = "barcode")
    public List<ItemGetResponseDTO> getItemByBarCode(@RequestParam(value = "barcode") String itemBarCode) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByBarCode(itemBarCode);
        return itemDTOS;
    }

    // Not fully implement (not work)
    @GetMapping(path = "/get-by-name-with-mapstruct", params = "name")
    public List<ItemGetResponseDTO> getItemByNameAndStatusBymapstruct(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusBymapstruct(itemName);
        return itemDTOS;
    }

    @GetMapping(path = "/get-all-item-by-status", params = "activeStatus")
    public ResponseEntity<StandardResponse> getAllItemByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByStockStatus(activeStatus);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,
                        "Success", itemDTOS),
                HttpStatus.OK);
    }

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }

    @PutMapping("/update")
    public String updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO){
        String message = itemService.updateItem(itemUpdateDTO);
        return message;
    }

    @DeleteMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable(value = "id") int itemId) {
        String delete = itemService.deleteItem(itemId);
        return delete;
    }

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
}