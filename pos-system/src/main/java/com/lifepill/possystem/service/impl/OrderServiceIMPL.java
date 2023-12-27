package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.OrderDetails;
import com.lifepill.possystem.repo.CashierRepo;
import com.lifepill.possystem.repo.ItemRepo;
import com.lifepill.possystem.repo.OrderDetailsRepo;
import com.lifepill.possystem.repo.OrderRepo;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.service.OrderService;
import com.lifepill.possystem.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CashierRepo cashierRepo;
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;
    @Autowired
    private ItemRepo itemRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
          cashierRepo.getById(requestOrderSaveDTO.getCashiers()),
          requestOrderSaveDTO.getOrderDate(),
          requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if (orderRepo.existsById(order.getOrderId())){

            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(),new TypeToken<List<OrderDetails>>(){
                    }.getType());

            for (int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }

            if (orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return null;
    }
}
