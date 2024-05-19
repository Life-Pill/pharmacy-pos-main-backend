package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.ItemSaveRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.dto.responseDTO.OrderResponseDTO;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.service.OrderService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling order-related endpoints.
 */
@RestController
@RequestMapping("/lifepill/v1/order")
@CrossOrigin
@AllArgsConstructor
public class OrderController{

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

    /**
     * Retrieves all orders in the system.
     *
     * @return A response entity containing a list of all orders.
     */
    @GetMapping("/getAllOrdersWithDetails")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrdersWithDetails() {
        List<OrderResponseDTO> orders = orderService.getAllOrdersWithDetails();
        return ResponseEntity.ok(orders);
    }
}
