package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestPaymentDetailsDTO;
import com.lifepill.possystem.dto.responseDTO.OrderResponseDTO;
import com.lifepill.possystem.service.OrderService;
import com.lifepill.possystem.util.StandardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * The type Order controller test.
 */
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test save order success.
     */
    @Test
    void testSaveOrder_Success() {
        // Arrange
        RequestOrderSaveDTO requestOrderSaveDTO = createRequestOrderSaveDTO();
        String expectedMessage = "Order saved successfully";
        when(orderService.addOrder(requestOrderSaveDTO)).thenReturn(expectedMessage);

        // Act
        ResponseEntity<StandardResponse> responseEntity = orderController.saveItem(requestOrderSaveDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(201, responseEntity.getBody().getCode());
//        assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }

    /**
     * Test save order insufficient item quantity.
     */
    @Test
    void testSaveOrder_InsufficientItemQuantity() {
        // Arrange
        RequestOrderSaveDTO requestOrderSaveDTO = createRequestOrderSaveDTO();
        String expectedMessage = "Item 1 does not have enough quantity";
        when(orderService.addOrder(requestOrderSaveDTO)).thenThrow(new RuntimeException(expectedMessage));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> orderController.saveItem(requestOrderSaveDTO));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test save order item not found.
     */
    @Test
    void testSaveOrder_ItemNotFound() {
        // Arrange
        RequestOrderSaveDTO requestOrderSaveDTO = createRequestOrderSaveDTO();
        String expectedMessage = "Item not found with ID: 1";
        when(orderService.addOrder(requestOrderSaveDTO)).thenThrow(new RuntimeException(expectedMessage));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> orderController.saveItem(requestOrderSaveDTO));
        assertEquals(expectedMessage, exception.getMessage());
    }


    private RequestOrderSaveDTO createRequestOrderSaveDTO() {
        RequestOrderSaveDTO requestOrderSaveDTO = new RequestOrderSaveDTO();
        requestOrderSaveDTO.setEmployerId(1L);
        requestOrderSaveDTO.setOrderId(1L);
        requestOrderSaveDTO.setBranchId(1L);
        requestOrderSaveDTO.setOrderDate(new Date());
        requestOrderSaveDTO.setTotal(100.0);

        List<RequestOrderDetailsSaveDTO> orderDetails = new ArrayList<>();
        RequestOrderDetailsSaveDTO orderDetail1 = new RequestOrderDetailsSaveDTO("Item 1", 10.0, 1L);
        RequestOrderDetailsSaveDTO orderDetail2 = new RequestOrderDetailsSaveDTO("Item 2", 5.0, 2L);
        orderDetails.add(orderDetail1);
        orderDetails.add(orderDetail2);
        requestOrderSaveDTO.setOrderDetails(orderDetails);

        RequestPaymentDetailsDTO paymentDetails = new RequestPaymentDetailsDTO("Cash", 100.0, new Date(), "No notes", 0.0, 100.0);
        requestOrderSaveDTO.setPaymentDetails(paymentDetails);

        return requestOrderSaveDTO;
    }


    /**
     * Test getAllOrdersWithDetails success.
     */
    @Test
    void testGetAllOrdersWithDetails_Success() {
        // Arrange
        List<OrderResponseDTO> expectedResponse = createMockOrderResponseList();
        when(orderService.getAllOrdersWithDetails()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<StandardResponse> responseEntity = orderController.getAllOrdersWithDetails();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getBody().getCode());
        assertEquals(expectedResponse, responseEntity.getBody().getData());
    }

    private List<OrderResponseDTO> createMockOrderResponseList() {
        // Create mock order response list
        List<OrderResponseDTO> orderResponseList = new ArrayList<>();
        // Add mock order responses
        OrderResponseDTO order1 = new OrderResponseDTO();
        // Set order details for order1
        orderResponseList.add(order1);
        OrderResponseDTO order2 = new OrderResponseDTO();
        // Set order details for order2
        orderResponseList.add(order2);

        return orderResponseList;
    }


}