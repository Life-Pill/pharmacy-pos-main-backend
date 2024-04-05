package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.OrderDetails;
import com.lifepill.possystem.exception.InsufficientItemQuantityException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.repo.orderRepository.OrderDetailsRepository;
import com.lifepill.possystem.repo.orderRepository.OrderRepository;
import com.lifepill.possystem.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepo;
    @Autowired
    private ItemRepository itemRepository;

 /*   @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
          cashierRepo.getById(requestOrderSaveDTO.getEmployers()),
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
    }*/

    @Override
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        // Check if items in the order have sufficient quantity
        checkItemStock(requestOrderSaveDTO);

        // Update item quantities
        updateItemQuantities(requestOrderSaveDTO);

        Order order = new Order(
          employerRepository.getById(requestOrderSaveDTO.getEmployerId()),
          requestOrderSaveDTO.getOrderDate(),
          requestOrderSaveDTO.getTotal()
        );
        orderRepository.save(order);
        if (orderRepository.existsById(order.getOrderId())){

            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(),new TypeToken<List<OrderDetails>>(){
                    }.getType());

            for (int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepository.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }

            if (orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved";
        }

        return "Order saved successfully";
    }

    /*private void checkItemStock(RequestOrderSaveDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepo.findById(orderDetail.getItems());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                if (item.getItemQuantity() < orderDetail.getQty()) {
                    throw new InsufficientItemQuantityException("Item " + item.getItemId() + " does not have enough quantity");
                }
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getItems());
            }
        }
    }*/

    private void checkItemStock(RequestOrderSaveDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getItems());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                if (item.getItemQuantity() < orderDetail.getQty()) {
                    throw new InsufficientItemQuantityException("Item " + item.getItemId() + " does not have enough quantity");
                }
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getItems());
            }
        }
    }

    private void updateItemQuantities(RequestOrderSaveDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getItems());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                int remainingQuantity = (int) (item.getItemQuantity() - orderDetail.getQty());
                item.setItemQuantity(remainingQuantity);
                itemRepository.save(item);
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getItems());
            }
        }
    }
}
