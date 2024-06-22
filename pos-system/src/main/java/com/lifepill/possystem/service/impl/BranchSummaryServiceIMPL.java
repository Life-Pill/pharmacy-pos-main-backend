package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchDailySalesSummaryDTO;
import com.lifepill.possystem.dto.responseDTO.AllPharmacySummaryResponseDTO;
import com.lifepill.possystem.dto.responseDTO.DailySalesSummaryDTO;
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
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
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
            // fetch manager details based on branchId
            String manager = getManagerForBranch(branchId);
            // fetch branch details based on branchId
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

        // Fetch all orders for the selected date from the repository
        List<Order> ordersForDate = orderRepository.findByOrderDateBetween(
                // Start of the given date
                DateUtils.truncate(date, Calendar.DAY_OF_MONTH),
                // End of the given date
                DateUtils.addMilliseconds(
                        DateUtils.ceiling(date, Calendar.DAY_OF_MONTH),
                        -1
                )
        );

        // Group orders by branchId and calculate total sales and count of orders for each branch
        Map<Long, Double> branchSalesMap = ordersForDate.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.summingDouble(Order::getTotal))
                );
        Map<Long, Long> branchOrdersCountMap = ordersForDate.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.counting())
                );

        // Retrieve additional branch and manager details
        List<PharmacyBranchResponseDTO> pharmacyData = branchSalesMap.entrySet().stream().map(entry -> {
            Long branchId = entry.getKey();
            Double sales = entry.getValue();

            Integer orders = branchOrdersCountMap.getOrDefault(branchId, 0L).intValue();
            // fetch manager details based on branchId
            String manager = getManagerForBranch(branchId);
            // fetch branch details based on branchId
            BranchDTO branchDTO = getBranchDetails(branchId);
            return new PharmacyBranchResponseDTO(sales, orders, manager, branchDTO);
        }).collect(Collectors.toList());

        return pharmacyData;
    }

    /**
     * Retrieves pharmacy data within the given time period.
     *
     * @param startDate The start date of the time period.
     * @param endDate   The end date of the time period.
     * @return List of PharmacyBranchResponseDTO containing pharmacy data within the time period.
     */
    @Override
    public List<PharmacyBranchResponseDTO> getPharmacyDataByTimePeriod(Date startDate, Date endDate) {

        // Fetch all orders within the given time period from the repository
        List<Order> ordersForPeriod = orderRepository.findByOrderDateBetween(startDate, endDate);

        // Group orders by branchId and calculate total sales and count of orders for each branch
        Map<Long, Double> branchSalesMap = ordersForPeriod.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.summingDouble(Order::getTotal))
                );
        Map<Long, Long> branchOrdersCountMap = ordersForPeriod.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.counting())
                );

        // Retrieve additional branch and manager details
        List<PharmacyBranchResponseDTO> pharmacyData = branchSalesMap.entrySet().stream().map(entry -> {
            Long branchId = entry.getKey();
            Double sales = entry.getValue();

            Integer orders = branchOrdersCountMap.getOrDefault(branchId, 0L).intValue();
            //fetch manager details based on branchId
            String manager = getManagerForBranch(branchId);
            //fetch branch details based on branchId
            BranchDTO branchDTO = getBranchDetails(branchId);
            return new PharmacyBranchResponseDTO(sales, orders, manager, branchDTO);
        }).collect(Collectors.toList());
        return pharmacyData;
    }


    /**
     * Retrieves a summary of sales for the given month.
     *
     * @param month The month for which to retrieve the summary (1-12).
     * @param year  The year for which to retrieve the summary.
     * @return A list of PharmacyBranchResponseDTO containing the summary of sales for the given month.
     */
    @Override
    public List<PharmacyBranchResponseDTO> getMonthlySummary(int month, int year) {

        // Fetch all orders for the given month and year from the repository
        List<Order> ordersForMonth = orderRepository.findByOrderDateBetween(
                // Start of the given month
                DateUtils.truncate(new Date(year - 1900, month - 1, 1), Calendar.MONTH),
                // End of the given month
                DateUtils.addMilliseconds(
                        DateUtils.ceiling(new Date(year - 1900, month - 1, 1), Calendar.MONTH),
                        -1
                )
        );

        // Group orders by branchId and calculate total sales and count of orders for each branch
        Map<Long, Double> branchSalesMap = ordersForMonth.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.summingDouble(Order::getTotal))
                );
        Map<Long, Long> branchOrdersCountMap = ordersForMonth.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.counting())
                );

        // Retrieve additional branch and manager details
        List<PharmacyBranchResponseDTO> pharmacyData = branchSalesMap.entrySet().stream().map(entry -> {
            Long branchId = entry.getKey();
            Double sales = entry.getValue();

            Integer orders = branchOrdersCountMap.getOrDefault(branchId, 0L).intValue();
            //fetch manager details based on branchId
            String manager = getManagerForBranch(branchId);
            //fetch branch details based on branchId
            BranchDTO branchDTO = getBranchDetails(branchId);
            return new PharmacyBranchResponseDTO(sales, orders, manager, branchDTO);
        }).collect(Collectors.toList());

        return pharmacyData;
    }

    /**
     * Retrieves a summary of sales for the given year.
     *
     * @param year The year for which to retrieve the summary.
     * @return A list of PharmacyBranchResponseDTO containing the summary of sales for the given year.
     */
    @Override
    public List<PharmacyBranchResponseDTO> getYearlySummary(int year) {

        // Fetch all orders for the given year from the repository
        List<Order> ordersForYear = orderRepository.findByOrderDateBetween(
                // Start of the given year
                DateUtils.truncate(new Date(year - 1900, 0, 1), Calendar.YEAR),
                // End of the given year
                DateUtils.addMilliseconds(
                        DateUtils.ceiling(new Date(year - 1900, 0, 1), Calendar.YEAR),
                        -1
                )
        );

        // Group orders by branchId and calculate total sales and count of orders for each branch
        Map<Long, Double> branchSalesMap = ordersForYear.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.summingDouble(Order::getTotal))
                );
        Map<Long, Long> branchOrdersCountMap = ordersForYear.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId, Collectors.counting())
                );

        // Retrieve additional branch and manager details
        List<PharmacyBranchResponseDTO> pharmacyData = branchSalesMap.entrySet().stream().map(entry -> {
            Long branchId = entry.getKey();
            Double sales = entry.getValue();

            Integer orders = branchOrdersCountMap.getOrDefault(branchId, 0L).intValue();
            //fetch manager details based on branchId
            String manager = getManagerForBranch(branchId);
            //fetch branch details based on branchId
            BranchDTO branchDTO = getBranchDetails(branchId);
            return new PharmacyBranchResponseDTO(sales, orders, manager, branchDTO);
        }).collect(Collectors.toList());

        logger.info("Fetched all branches with sales information successfully.");
        return pharmacyData;
    }

    /**
     * Retrieves total details of all pharmacy branches.
     *
     * @return AllPharmacySummaryResponseDTO containing total sales, total orders, total employees, and total branches.
     */
    @Override
    public AllPharmacySummaryResponseDTO getAllPharmacySummary() {
        // Fetch total sales
        Double totalSales = orderRepository.getTotalSales();

        // Fetch total orders
        long totalOrders = orderRepository.count();

        // Fetch total employees
        long totalEmployees = employerRepository.count();

        // Fetch total branches
        long totalBranches = branchRepository.count();

        // Create and return AllPharmacySummaryResponseDTO
        return new AllPharmacySummaryResponseDTO(totalSales, (int) totalOrders, (int) totalEmployees, (int) totalBranches);
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

    /**
     * Retrieves daily sales summary for a specific branch.
     *
     * This method fetches all orders for a given branch, groups them by order date, and calculates
     * the total sales and count of orders for each date. It then creates a list of DailySalesSummaryDTO
     * objects, each representing the sales summary for a specific day, and returns this list.
     *
     * @param branchId The ID of the branch for which the daily sales summary is to be fetched.
     * @return A list of DailySalesSummaryDTO objects, each representing the sales summary for a specific day.
     */
    @Override
    public List<DailySalesSummaryDTO> getDailySalesSummary(long branchId) {
        // Retrieve all orders for the given branch
        List<Order> orders = orderRepository.findAllByBranchId(branchId);

        // Group orders by order date and calculate total sales and count of orders for each date
        Map<LocalDate, Double> dailySalesMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        Collectors.summingDouble(Order::getTotal))
                );
        Map<LocalDate, Long> dailyOrdersCountMap = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        Collectors.counting())
                );

        // Create and return DailySalesSummaryDTO
        List<DailySalesSummaryDTO> result = dailySalesMap.entrySet().stream().map(entry -> {
            LocalDate date = entry.getKey();
            Double sales = entry.getValue();
            Long ordersCount = dailyOrdersCountMap.getOrDefault(date, 0L);
            return new DailySalesSummaryDTO(date, ordersCount, sales);
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<BranchDailySalesSummaryDTO> getAllDailySalesSummary() {
        // Retrieve all orders for all branches
        List<Order> orders = orderRepository.findAll();

        // Group orders by branch ID and then by order date
        Map<Long, Map<LocalDate, List<Order>>> branchDateOrderMap = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getBranchId,
                        Collectors.groupingBy(order -> order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                );

        // Create result list
        List<BranchDailySalesSummaryDTO> result = new ArrayList<>();

        for (Map.Entry<Long, Map<LocalDate, List<Order>>> branchEntry : branchDateOrderMap.entrySet()) {
            long branchId = branchEntry.getKey();
            Map<LocalDate, List<Order>> dateOrderMap = branchEntry.getValue();

            List<DailySalesSummaryDTO> dailySalesSummaries = dateOrderMap.entrySet().stream().map(dateEntry -> {
                LocalDate date = dateEntry.getKey();
                List<Order> dailyOrders = dateEntry.getValue();

                double totalSales = dailyOrders.stream().mapToDouble(Order::getTotal).sum();
                long orderCount = dailyOrders.size();

                return new DailySalesSummaryDTO(date, orderCount, totalSales);
            }).collect(Collectors.toList());

            result.add(new BranchDailySalesSummaryDTO(branchId, dailySalesSummaries));
        }

        return result;
    }
}