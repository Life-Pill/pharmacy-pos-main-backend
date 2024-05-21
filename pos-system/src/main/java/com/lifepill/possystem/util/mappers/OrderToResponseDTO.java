package com.lifepill.possystem.util.mappers;

import com.lifepill.possystem.dto.GroupedOrderDetails;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestPaymentDetailsDTO;
import com.lifepill.possystem.dto.responseDTO.OrderResponseDTO;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.PaymentDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderToResponseDTO {
    private ModelMapper modelMapper;


}
