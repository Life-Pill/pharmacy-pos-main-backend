package com.lifepill.possystem.util.mappers;

import com.lifepill.possystem.dto.GroupedOrderDetails;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestPaymentDetailsDTO;
import com.lifepill.possystem.dto.responseDTO.OrderResponseDTO;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.PaymentDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapper {

    private ModelMapper modelMapper;

    /**
     * Maps an Order entity to an OrderResponseDTO.
     *
     * @param order The Order entity to map.
     * @return The mapped OrderResponseDTO.
     */
    public OrderResponseDTO mapOrderToResponseDTO(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setEmployerId(order.getEmployer().getEmployerId());
        orderResponseDTO.setBranchId(order.getBranchId());
        orderResponseDTO.setOrderDate(order.getOrderDate());
        orderResponseDTO.setTotal(order.getTotal());

        GroupedOrderDetails groupedOrderDetails = new GroupedOrderDetails();
        List<RequestOrderDetailsSaveDTO> orderDetails = order.getOrderDetails().stream()
                .map(orderDetail -> {
                    RequestOrderDetailsSaveDTO dto = modelMapper.map(orderDetail, RequestOrderDetailsSaveDTO.class);
                    dto.setId(orderDetail.getItems().getItemId());
                    return dto;
                })
                .collect(Collectors.toList());
        groupedOrderDetails.setOrderDetails(orderDetails);

        if (order.getPaymentDetails() != null && !order.getPaymentDetails().isEmpty()) {
            PaymentDetails paymentDetails = order.getPaymentDetails().iterator().next();
            RequestPaymentDetailsDTO paymentDetailsDTO = modelMapper.map(paymentDetails, RequestPaymentDetailsDTO.class);
            paymentDetailsDTO.setPayedAmount(paymentDetails.getPaidAmount());
            groupedOrderDetails.setPaymentDetails(paymentDetailsDTO);
        }
        groupedOrderDetails.setOrderCount(1); // Since we're retrieving a single order
        orderResponseDTO.setGroupedOrderDetails(groupedOrderDetails);
        return orderResponseDTO;
    }
}
