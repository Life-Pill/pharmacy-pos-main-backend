package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.dto.responseDTO.OrderResponseDTO;
import com.lifepill.possystem.service.OrderService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling order-related endpoints.
 */
@RestController
@RequestMapping("/lifepill/v1/order")
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

        return new ResponseEntity<>(
                new StandardResponse(201, id +"Item Savd Successfully",id),
                HttpStatus.CREATED);
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return A response entity containing a list of all orders.
     */
    @GetMapping("/getAllOrdersWithDetails")
    public ResponseEntity<StandardResponse> getAllOrdersWithDetails() {
        List<OrderResponseDTO> orderResponseDTOList = orderService.getAllOrdersWithDetails();

        return new ResponseEntity<>(
                new StandardResponse(200, "All Orders Retrieved Successfully", orderResponseDTOList),
                HttpStatus.OK);
    }

    /**
     * Gets order with details by id.
     *
     * @param orderId the order id
     * @return the order with details by id
     */
    @GetMapping("/getOrderWithDetails/{orderId}")
    public ResponseEntity<StandardResponse> getOrderWithDetailsById(@PathVariable long orderId) {
        OrderResponseDTO orderResponseDTO = orderService.getOrderWithDetailsById(orderId);
        return new ResponseEntity<>(
                new StandardResponse(200, "Order Retrieved Successfully", orderResponseDTO),
                HttpStatus.OK);
    }

    /**
     * Gets orders with details by branch id.
     *
     * @param branchId the branch id
     * @return the orders with details by branch id
     */
    @GetMapping("/getOrderWithDetailsByBranchId/{branchId}")
    public ResponseEntity<StandardResponse> getOrderWithDetailsByBranchId(@PathVariable long branchId) {
        List<OrderResponseDTO> orderResponseDTOs = orderService.getOrderWithDetailsByBranchId(branchId);
        return new ResponseEntity<>(
                new StandardResponse(200, "Orders Retrieved Successfully", orderResponseDTOs),
                HttpStatus.OK);
    }
}
