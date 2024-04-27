package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.repo.orderRepository.OrderDetailsRepository;
import com.lifepill.possystem.repo.orderRepository.OrderRepository;
import com.lifepill.possystem.service.BranchSummaryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BranchSummaryServiceIMPL implements BranchSummaryService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;
  @Override
    public List<PharmacyBranchResponseDTO> getAllBranchesWithSales() {

        // Fetch all orders from the repository
        List<Order> allOrders = orderRepository.findAll();

          // Group orders by branchId and calculate total sales and count of orders for each branch
          Map<Long, Double> branchSalesMap = allOrders.stream()
                  .collect(Collectors.groupingBy(Order::getBranchId,
                          Collectors.summingDouble(Order::getTotal)));
          Map<Long, Long> branchOrdersCountMap = allOrders.stream()
                  .collect(Collectors.groupingBy(Order::getBranchId, Collectors.counting()));

          return branchSalesMap.entrySet().stream()
                  .map(entry -> {
                      Long branchId = entry.getKey();
                      Double sales = entry.getValue();
                      Integer orders = branchOrdersCountMap.getOrDefault(branchId, 0L).intValue();
                      // Assuming you have a method to fetch manager details based on branchId
                      // TODO: Implement this method
                      String manager = getManagerForBranch(branchId);
                      // Assuming you have a method to fetch branch details based on branchId
                      // TODO: Implement this method
                      BranchDTO branchDTO = getBranchDetails(branchId);
                      return new PharmacyBranchResponseDTO(sales, orders, manager, branchDTO);
                  })
                  .collect(Collectors.toList());
    }

    // Implement methods to fetch manager details and branch details based on branchId
    private String getManagerForBranch(Long branchId) {
//       String branchManagerName = employerRepository.findByBranchId(branchId).get(0).getName();
//        return branchManagerName;
        return null;
    }
    private BranchDTO getBranchDetails(Long branchId) {
        // Your implementation here
        return null;
    }
}
