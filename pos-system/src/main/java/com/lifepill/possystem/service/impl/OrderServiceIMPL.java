package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.GroupedOrderDetails;
import com.lifepill.possystem.dto.requestDTO.RequestOrderDetailsSaveDTO;
import com.lifepill.possystem.dto.requestDTO.RequestOrderSMSDTO;
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
import com.lifepill.possystem.service.SMSService;
import com.lifepill.possystem.util.mappers.OrderMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(OrderServiceIMPL.class);
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private EmployerRepository employerRepository;
    private OrderDetailsRepository orderDetailsRepo;
    private ItemRepository itemRepository;
    private BranchRepository branchRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private PaymentRepository paymentRepository;
    private OrderMapper orderMapper;
    private SMSService smsService;


    /**
     * Adds an order to the system.
     *
     * @param requestOrderSaveDTO The DTO containing order details.
     * @return A message indicating the result of the operation.
     */
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

        if (orderRepository.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {
                            }
                                    .getType()
                    );
            for (int i = 0; i < orderDetails.size(); i++) {
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepository
                        .getById(requestOrderSaveDTO
                                .getOrderDetails().get(i).getId()
                        )
                );
            }
            if (!orderDetails.isEmpty()) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            savePaymentDetails(requestOrderSaveDTO.getPaymentDetails(), order);
            return "saved";
        }

        return "Order saved";
    }

    @Override
    public String addOrderWithSMS(RequestOrderSMSDTO requestOrderSaveDTO) {
        // Check if items in the order have sufficient quantity
        checkItemStockSMS(requestOrderSaveDTO);
        // Update item quantities
        updateItemQuantitiesSMS(requestOrderSaveDTO);

        Order order = new Order();
        order.setEmployer(employerRepository.getById(requestOrderSaveDTO.getEmployerId()));
        order.setOrderDate(requestOrderSaveDTO.getOrderDate());
        order.setTotal(requestOrderSaveDTO.getTotal());
        order.setBranchId(requestOrderSaveDTO.getBranchId());

        orderRepository.save(order);

        if (orderRepository.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails = modelMapper
                    .map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {
                            }
                                    .getType()
                    );
            for (int i = 0; i < orderDetails.size(); i++) {
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepository
                        .getById(requestOrderSaveDTO
                                .getOrderDetails().get(i).getId()
                        )
                );
            }
            if (!orderDetails.isEmpty()) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            System.out.println("Payment Details: " + requestOrderSaveDTO.getPaymentDetails());
            log.info("Payment Details: " + requestOrderSaveDTO.getPaymentDetails());
            savePaymentDetails(requestOrderSaveDTO.getPaymentDetails(), order);
            log.info("requestOrderSaveDTO.getCustomerPhoneNumber(): " + requestOrderSaveDTO.getCustomerPhoneNumber());
            log.info("order: " + order);
            sendOrderDetailsSms(requestOrderSaveDTO.getCustomerPhoneNumber(), order);
            return "saved";
        }

        return "Order saved";

    }

    private void sendOrderDetailsSms(String customerPhoneNumber, Order order) {
        StringBuilder message = new StringBuilder();
        message.append("Thank you for your order!\n\n");
        message.append("Order ID: ").append(order.getOrderId()).append("\n");
        message.append("Date: ").append(order.getOrderDate()).append("\n");
        message.append("Total: ").append(order.getTotal()).append("\n\n");
        message.append("Items:\n");

        // Check if order.getOrderDetails() is not null
        if (order.getOrderDetails() != null) {
            for (OrderDetails orderDetails : order.getOrderDetails()) {
                message.append(orderDetails.getName()).append(" - ").append(orderDetails.getAmount()).append("\n");
            }
        } else {
            // Handle the case when order.getOrderDetails() is null
            message.append("No order details found.");
        }

        smsService.sendSms(customerPhoneNumber, message.toString());
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
     * @throws NotFoundException                 if an item in the order is not found in the database.
     */
    private void checkItemStock(RequestOrderSaveDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getId());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                if (item.getItemQuantity() < orderDetail.getAmount()) {
                    throw new InsufficientItemQuantityException(
                            "Item " + item.getItemId()
                                    + " does not have enough quantity"
                    );
                }
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getId());
            }
        }
    }

    private void checkItemStockSMS(RequestOrderSMSDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getId());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                if (item.getItemQuantity() < orderDetail.getAmount()) {
                    throw new InsufficientItemQuantityException(
                            "Item " + item.getItemId()
                                    + " does not have enough quantity"
                    );
                }
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getId());
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
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getId());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                int remainingQuantity = (int) (item.getItemQuantity() - orderDetail.getAmount());
                item.setItemQuantity(remainingQuantity);
                itemRepository.save(item);
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getId());
            }
        }
    }

    private void updateItemQuantitiesSMS(RequestOrderSMSDTO requestOrderSaveDTO) {
        for (RequestOrderDetailsSaveDTO orderDetail : requestOrderSaveDTO.getOrderDetails()) {
            Optional<Item> optionalItem = itemRepository.findById(orderDetail.getId());
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                int remainingQuantity = (int) (item.getItemQuantity() - orderDetail.getAmount());
                item.setItemQuantity(remainingQuantity);
                itemRepository.save(item);
            } else {
                throw new NotFoundException("Item not found with ID: " + orderDetail.getId());
            }
        }
    }

    /**
     * Retrieves all orders with their details.
     *
     * @return A list of OrderResponseDTO containing orders with details.
     */
    public List<OrderResponseDTO> getAllOrdersWithDetails() {
        List<Order> orders = orderRepository.findAll();
        Map<String, List<Order>> groupedOrders = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate() + "-"
                                + order.getBranchId() + "-"
                                + order.getEmployer().getEmployerId()
                ));

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
                            .map(orderDetail -> {
                                RequestOrderDetailsSaveDTO dto = modelMapper.map(orderDetail, RequestOrderDetailsSaveDTO.class);
                                dto.setId(firstOrder.getOrderDetails().iterator().next().getItems().getItemId()); // Ensure the ID is set correctly
                                return dto;
                            })
                            .collect(Collectors.toList());

                    // Limit orderDetails to the actual number of orders in the group
                    //    orderDetails = orderDetails.stream().limit(ordersInGroup.size()).collect(Collectors.toList());

                    RequestPaymentDetailsDTO paymentDetails = ordersInGroup.stream()
                            .filter(order -> order.getPaymentDetails() != null && !order.getPaymentDetails().isEmpty())
                            .map(order -> modelMapper.map(
                                    order.getPaymentDetails().iterator().next(), // Get the first payment detail
                                    RequestPaymentDetailsDTO.class)
                            )
                            .findFirst()
                            .orElse(null);

                    if (paymentDetails != null && !ordersInGroup.get(0).getPaymentDetails().isEmpty()) {
                        paymentDetails.setPayedAmount(firstOrder.getPaymentDetails().iterator().next().getPaidAmount());
                    }

                    int orderCount = ordersInGroup.size();

                    // Set these values to orderResponseDTO object
                    orderResponseDTO.setGroupedOrderDetails(
                            new GroupedOrderDetails(orderDetails, paymentDetails, orderCount)
                    );

                    firstOrder.getOrderDetails().iterator().next().getItems().getItemId();
                    System.out.println(" item id: " + firstOrder.getOrderDetails().iterator().next().getItems().getItemId());
                    // Logging statements to debug and verify values
                    System.out.println("Order ID: " + firstOrder.getOrderId());
                    System.out.println("First Order Details ID: " + firstOrder.getOrderDetails().iterator().next().getOrderDetailsId());
                    System.out.println("First Order Payed Amount: " + firstOrder.getPaymentDetails().iterator().next().getPaidAmount());

                    return orderResponseDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an order with its details by the provided order ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The order response DTO containing the order details.
     * @throws NotFoundException if no order is found with the provided ID.
     */
    public OrderResponseDTO getOrderWithDetailsById(long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        System.out.println("Order ID: " + orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return orderMapper.mapOrderToResponseDTO(order);
        } else {
            throw new NotFoundException("Order not found with ID: " + orderId);
        }
    }

    /**
     * Retrieves orders with their details by the provided branch ID.
     *
     * @param branchId The ID of the branch to retrieve orders from.
     * @return A list of OrderResponseDTO containing orders with details.
     * @throws NotFoundException if no orders are found for the provided branch ID.
     */
    public List<OrderResponseDTO> getOrderWithDetailsByBranchId(long branchId) {
        List<Order> orders = orderRepository.findByBranchId(branchId);
        if (orders.isEmpty()) {
            throw new NotFoundException("No orders found for branch ID: " + branchId);
        }

        Map<String, List<Order>> groupedOrders = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate() + "-"
                                + order.getBranchId() + "-"
                                + order.getEmployer().getEmployerId()
                ));

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
                            .map(orderDetail -> {
                                RequestOrderDetailsSaveDTO dto = modelMapper.map(orderDetail, RequestOrderDetailsSaveDTO.class);
                                dto.setId(orderDetail.getItems().getItemId()); // Ensure the ID is set correctly
                                return dto;
                            })
                            .collect(Collectors.toList());

                    RequestPaymentDetailsDTO paymentDetails = ordersInGroup.stream()
                            .filter(order -> order.getPaymentDetails() != null && !order.getPaymentDetails().isEmpty())
                            .map(order -> modelMapper.map(
                                    order.getPaymentDetails().iterator().next(), // Get the first payment detail
                                    RequestPaymentDetailsDTO.class)
                            )
                            .findFirst()
                            .orElse(null);

                    if (paymentDetails != null && !firstOrder.getPaymentDetails().isEmpty()) {
                        paymentDetails.setPayedAmount(firstOrder.getPaymentDetails().iterator().next().getPaidAmount());
                    }

                    int orderCount = ordersInGroup.size();

                    orderResponseDTO.setGroupedOrderDetails(
                            new GroupedOrderDetails(orderDetails, paymentDetails, orderCount)
                    );

                    return orderResponseDTO;
                })
                .collect(Collectors.toList());
    }

}