package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.service.OrderService;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling order-related endpoints.
 */
@RestController
@RequestMapping("/lifepill/v1/order")
@CrossOrigin
public class OrderController{

    @Autowired
    private OrderService orderService;

    /**
     * Saves an order.
     *
     * @param requestOrderSaveDTO The DTO containing order details.
     * @return A response entity indicating the result of the operation.
     */
    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
       String id =  orderService.addOrder(requestOrderSaveDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, id +"Item Savd Successfully",id),
                HttpStatus.CREATED);
    }
}
