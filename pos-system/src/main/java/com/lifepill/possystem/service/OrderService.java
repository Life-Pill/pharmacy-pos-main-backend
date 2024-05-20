package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.dto.responseDTO.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    List<OrderResponseDTO> getAllOrdersWithDetails();
}
