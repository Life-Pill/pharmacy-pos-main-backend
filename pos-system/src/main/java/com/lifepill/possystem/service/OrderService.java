package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);
}
