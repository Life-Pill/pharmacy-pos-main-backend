package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.repo.orderRepository.OrderRepository;
import com.lifepill.possystem.service.BranchSummaryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the BranchSummaryService interface.
 */
@Service
@AllArgsConstructor
public class BranchSummaryServiceIMPL implements BranchSummaryService {

    private final BranchRepository branchRepository;
    private final EmployerRepository employerRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(BranchSummaryServiceIMPL.class);

    /**
     * Retrieves all branches with their sales information.
     *
     * @return List of PharmacyBranchResponseDTO containing sales information for each branch.
     */
    @Override
    public List<PharmacyBranchResponseDTO> getAllBranchesWithSales() {

        // Fetch all orders from the repository
        List<Order> allOrders = orderRepository.findAll();

        // Group orders by branchId and calculate total sales and count of orders for each branch
        Map<Long, Double> branchSalesMap = allOrders
                .stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.summingDouble(Order::getTotal))
                );
        Map<Long, Long> branchOrdersCountMap = allOrders
                .stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.counting())
                );

        return branchSalesMap.entrySet().stream().map(entry -> {
            Long branchId = entry.getKey();
            Double sales = entry.getValue();

            Integer orders = branchOrdersCountMap.getOrDefault(branchId, 0L).intValue();
            // Assuming you have a method to fetch manager details based on branchId
            String manager = getManagerForBranch(branchId);
            // Assuming you have a method to fetch branch details based on branchId
            BranchDTO branchDTO = getBranchDetails(branchId);
            return new PharmacyBranchResponseDTO(sales, orders, manager, branchDTO);

        }).collect(Collectors.toList());
    }

    /**
     * Retrieves sales information for a pharmacy branch by its ID.
     *
     * @param branchId The ID of the pharmacy branch.
     * @return A PharmacyBranchResponseDTO containing the total sales, order count, manager details, and branch details.
     */
    @Override
    public PharmacyBranchResponseDTO getBranchSalesById(long branchId) {

        // Fetch all orders from the repository for the given branchId
        List<Order> branchOrders = orderRepository.findByBranchId(branchId);

        // Calculate total sales and count of orders for the branch
        Double totalSales = branchOrders.stream().mapToDouble(Order::getTotal).sum();
        Integer orderCount = branchOrders.size();

        // Fetch manager details for the branch
        String manager = getManagerForBranch(branchId);

        // Fetch branch details
        BranchDTO branchDTO = getBranchDetails(branchId);

        // Create and return PharmacyBranchResponseDTO
        return new PharmacyBranchResponseDTO(totalSales, orderCount, manager, branchDTO);
    }

    /**
     * Retrieves pharmacy data for a selected date.
     *
     * @param date The date for which pharmacy data is requested.
     * @return A list of PharmacyBranchResponseDTO containing sales, order count, manager, and branch details for each pharmacy branch.
     */
    @Override
    public List<PharmacyBranchResponseDTO> getPharmacyDataByDate(Date date) {
        logger.info("Fetching all branches with sales information...");
        // Fetch all orders for the selected date from the repository
        System.out.println(orderRepository.findByOrderDate(date) + "orderRepository.findByOrderDate(date)");
        List<Order> ordersForDate = orderRepository.findByOrderDate(date);

        logger.debug(ordersForDate.toString() + "ordersForDate");

        // Group orders by branchId and calculate total sales and count of orders for each branch
        Map<Long, Double> branchSalesMap = ordersForDate.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.summingDouble(Order::getTotal))
                );
        logger.info(branchSalesMap.toString() + "branchSalesMap");
        Map<Long, Long> branchOrdersCountMap = ordersForDate.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.counting())
                );

        // Retrieve additional branch and manager details
        List<PharmacyBranchResponseDTO> pharmacyData = branchSalesMap.entrySet().stream().map(entry -> {
            Long branchId = entry.getKey();
            Double sales = entry.getValue();

            Integer orders = branchOrdersCountMap.getOrDefault(branchId, 0L).intValue();
            // Assuming you have a method to fetch manager details based on branchId
            String manager = getManagerForBranch(branchId);
            // Assuming you have a method to fetch branch details based on branchId
            BranchDTO branchDTO = getBranchDetails(branchId);
            return new PharmacyBranchResponseDTO(sales, orders, manager, branchDTO);
        }).collect(Collectors.toList());

        logger.info("Fetched all branches with sales information successfully.");
        return pharmacyData;
    }


    /**
     * Retrieves the manager's first name for the given branch ID.
     *
     * @param branchId The ID of the branch.
     * @return The manager's first name or "No Manager Assigned" if no manager is found.
     */
    private String getManagerForBranch(Long branchId) {
    // Implement methods to fetch manager details and branch details based on branchId
        // Find the manager for the given branch ID with the role "MANAGER"
        Employer manager = employerRepository.findByBranch_BranchIdAndRole(branchId, Role.MANAGER);

        // If manager is found, return their first name
        if (manager != null) {
            return manager.getEmployerFirstName();
        } else {
            // If no manager found for the given branch, return a default value or handle as needed
            return "No Manager Assigned";
        }
    }

    /**
     * Retrieves branch details for the given branch ID.
     *
     * @param branchId The ID of the branch.
     * @return The BranchDTO object containing branch details.
     * @throws NotFoundException if no branch is found for the given ID.
     */
    private BranchDTO getBranchDetails(Long branchId) {

        if (branchRepository.existsById(branchId)) {
            Branch branch = branchRepository.getReferenceById(branchId);

            return modelMapper.map(branch, BranchDTO.class);

        } else {
            throw new NotFoundException("No Branch found for that id");
        }
    }
}
