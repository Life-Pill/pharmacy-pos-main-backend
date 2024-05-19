package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.GroupedOrderDetails;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestPaymentDetailsDTO;
import com.lifepill.possystem.dto.responseDTO.OrderResponseDTO;
import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.OrderDetails;
import com.lifepill.possystem.entity.PaymentDetails;
import com.lifepill.possystem.exception.InsufficientItemQuantityException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.repo.itemRepository.ItemRepository;
import com.lifepill.possystem.repo.orderRepository.OrderDetailsRepository;
import com.lifepill.possystem.repo.orderRepository.OrderRepository;
import com.lifepill.possystem.repo.paymentRepository.PaymentRepository;
import com.lifepill.possystem.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link OrderService} interface that handles order-related operations.
 */
@Service
@Transactional
@AllArgsConstructor
public class OrderServiceIMPL implements OrderService {

    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private EmployerRepository employerRepository;
    private OrderDetailsRepository orderDetailsRepo;
    private ItemRepository itemRepository;
    private BranchRepository branchRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private PaymentRepository paymentRepository;


    /**
     * Adds an order to the system.
     *
     * @param requestOrderSaveDTO The DTO containing order details.
     * @return A message indicating the result of the operation.
     * */
    @Override
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        // Check if items in the order have sufficient quantity
        checkItemStock(requestOrderSaveDTO);

        // Update item quantities
        updateItemQuantities(requestOrderSaveDTO);

        Order order = new Order();
        order.setEmployer(employerRepository.getById(requestOrderSaveDTO.getEmployerId()));
        order.setOrderDate(requestOrderSaveDTO.getOrderDate());
        order.setTotal(requestOrderSaveDTO.getTotal());
        order.setBranchId(requestOrderSaveDTO.getBranchId());
        orderRepository.save(order);

        if (orderRepository.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>(){}
                            .getType()
                    );
            for (int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepository
                        .getById(requestOrderSaveDTO
                            .getOrderDetails().get(i).getItemId()
                        )
                );
            }
            if (!orderDetails.isEmpty()){
                orderDetailsRepo.saveAll(orderDetails);
            }
            savePaymentDetails(requestOrderSaveDTO.getPaymentDetails(), order);
            return "saved";
        }
        return "Order saved successfully";
    }

    /**
     * Saves payment details for an order.
     *
     * @param paymentDetailsDTO The DTO containing payment details.
     * @param order             The order for which the payment is made.
     */
    private void savePaymentDetails(RequestPaymentDetailsDTO paymentDetailsDTO, Order order) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentMethod(paymentDetailsDTO.getPaymentMethod());
        paymentDetails.setPaymentAmount(paymentDetailsDTO.getPaymentAmount());
        paymentDetails.setPaymentDate(paymentDetailsDTO.getPaymentDate());
        paymentDetails.setPaymentNotes(paymentDetailsDTO.getPaymentNotes());
        paymentDetails.setPaymentDiscount(paymentDetailsDTO.getPaymentDiscount());
        paymentDetails.setPaidAmount(paymentDetailsDTO.getPayedAmount());
        paymentDetails.setOrders(order); // Set the order for which this payment is made
        paymentRepository.save(paymentDetails);
    }

    /**
     * Checks the stock availability of items in the order.
     *
     * @param requestOrderSaveDTO The DTO containing the order details.
     * @throws InsufficientItemQuantityException if an item in the order does not have enough quantity.
     * @throws NotFoundException                if an item in the order is not found in the database.
     */
    private void checkItemStock(RequestOrderSaveDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getItemId());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                if (item.getItemQuantity() < orderDetail.getItemAmount()) {
                    throw new InsufficientItemQuantityException(
                            "Item " + item.getItemId()
                            + " does not have enough quantity"
                    );
                }
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getItemId());
            }
        }
    }

    /**
     * Updates the quantities of items in the database after an order is placed.
     *
     * @param requestOrderSaveDTO The DTO containing the order details.
     * @throws NotFoundException if an item in the order is not found in the database.
     */
    private void updateItemQuantities(RequestOrderSaveDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getItemId());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                int remainingQuantity = (int) (item.getItemQuantity() - orderDetail.getItemAmount());
                item.setItemQuantity(remainingQuantity);
                itemRepository.save(item);
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getItemId());
            }
        }
    }

    public List<OrderResponseDTO> getAllOrdersWithDetails() {
        List<Order> orders = orderRepository.findAll();
        Map<String, List<Order>> groupedOrders = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getOrderDate() + "-" + order.getBranchId()));

        return groupedOrders.entrySet().stream()
                .map(entry -> {
                    List<Order> ordersInGroup = entry.getValue();
                    Order firstOrder = ordersInGroup.get(0);

                    OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
                    orderResponseDTO.setEmployerId(firstOrder.getEmployer().getEmployerId());
                    orderResponseDTO.setBranchId(firstOrder.getBranchId());
                    orderResponseDTO.setOrderDate(firstOrder.getOrderDate());
                    orderResponseDTO.setTotal(ordersInGroup.stream().mapToDouble(Order::getTotal).sum());

                    List<RequestOrderDetailsSaveDTO> orderDetails = ordersInGroup.stream()
                            .flatMap(order -> order.getOrderDetails().stream())
                            .map(orderDetail -> modelMapper.map(orderDetail, RequestOrderDetailsSaveDTO.class))
                            .collect(Collectors.toList());

                    RequestPaymentDetailsDTO paymentDetails = ordersInGroup.stream()
                            .filter(order -> order.getPaymentDetails() != null)
                            .map(order -> modelMapper.map(order.getPaymentDetails(), RequestPaymentDetailsDTO.class))
                            .findFirst()
                            .orElse(null);

                    orderResponseDTO.setGroupedOrderDetails(new GroupedOrderDetails(orderDetails, paymentDetails));
                    return orderResponseDTO;
                })
                .collect(Collectors.toList());
    }
}