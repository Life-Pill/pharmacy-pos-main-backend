package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.OrderDetails;
import com.lifepill.possystem.repo.cashierRepo.CashierRepo;
import com.lifepill.possystem.repo.itemRepo.ItemRepo;
import com.lifepill.possystem.repo.orderRepo.OrderDetailsRepo;
import com.lifepill.possystem.repo.orderRepo.OrderRepo;
import com.lifepill.possystem.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
