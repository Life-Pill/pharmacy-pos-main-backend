package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.CashierWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.*;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Cashier;
import com.lifepill.possystem.entity.CashierBankDetails;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.cashierRepo.CashierBankDetailsRepo;
import com.lifepill.possystem.repo.cashierRepo.CashierRepo;
import com.lifepill.possystem.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CashierServiceIMPL implements CashierService {

    @Autowired
    private CashierRepo  cashierRepo;

    @Autowired
    private CashierBankDetailsRepo cashierBankDetailsRepo;


    @Override
    public String saveCashier(CashierDTO cashierDTO){
        // check if the cashier already exists email or id
        if (cashierRepo.existsById(cashierDTO.getCashierId()) || cashierRepo.existsAllByCashierEmail(cashierDTO.getCashierEmail())) {
            throw new EntityDuplicationException("Cashier already exists");
        } else {

        Cashier cashier = new Cashier(
                    cashierDTO.getCashierId(),
                    cashierDTO.getCashierNicName(),
                    cashierDTO.getCashierFirstName(),
                    cashierDTO.getCashierLastName(),
                    cashierDTO.getProfileImage(),
                    cashierDTO.getCashierPassword(),
                    cashierDTO.getCashierEmail(),
                    cashierDTO.getCashierPhone(),
                    cashierDTO.getCashierAddress(),
                    cashierDTO.getCashierSalary(),
                    cashierDTO.getCashierNic(),
                    cashierDTO.isActiveStatus(),
                    cashierDTO.getPin(),
                    cashierDTO.getGender(),
                    cashierDTO.getDateOfBirth(),
                    cashierDTO.getRole()
                    //  (Set<Order>) cashierDTO.getOrder()
            );

            cashierRepo.save(cashier);
            return "Saved";
        }

    }

    @Override
    public String saveCashierWithoutImage(CashierWithoutImageDTO cashierWithoutImageDTO) {
        // check if the cashier already exists email or id
        if (cashierRepo.existsById(cashierWithoutImageDTO.getCashierId()) || cashierRepo.existsAllByCashierEmail(cashierWithoutImageDTO.getCashierEmail())) {
            throw new EntityDuplicationException("Cashier already exists");
        } else {

            Cashier cashier = new Cashier(
                    cashierWithoutImageDTO.getCashierId(),
                    cashierWithoutImageDTO.getCashierNicName(),
                    cashierWithoutImageDTO.getCashierFirstName(),
                    cashierWithoutImageDTO.getCashierLastName(),
                    cashierWithoutImageDTO.getCashierPassword(),
                    cashierWithoutImageDTO.getCashierEmail(),
                    cashierWithoutImageDTO.getCashierPhone(),
                    cashierWithoutImageDTO.getCashierAddress(),
                    cashierWithoutImageDTO.getCashierSalary(),
                    cashierWithoutImageDTO.getCashierNic(),
                    cashierWithoutImageDTO.isActiveStatus(),
                    cashierWithoutImageDTO.getPin(),
                    cashierWithoutImageDTO.getGender(),
                    cashierWithoutImageDTO.getDateOfBirth(),
                    cashierWithoutImageDTO.getRole()
                    //  (Set<Order>) cashierDTO.getOrder()
            );

            cashierRepo.save(cashier);
            return "Saved";
        }
    }

    @Override
    public String updateCashier(CashierUpdateDTO cashierUpdateDTO) {
        if (cashierRepo.existsById(cashierUpdateDTO.getCashierId())){
            Cashier cashier = cashierRepo.getReferenceById(cashierUpdateDTO.getCashierId());

            cashier.setCashierNicName(cashierUpdateDTO.getCashierNicName());
            cashier.setCashierEmail(cashierUpdateDTO.getCashierEmail());
            cashier.setCashierNic(cashierUpdateDTO.getCashierNic());
            cashier.setCashierPhone(cashierUpdateDTO.getCashierPhone());
            cashier.setRole(cashierUpdateDTO.getRole());
            cashier.setCashierSalary(cashierUpdateDTO.getCashierSalary());

            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "UPDATED CUSTOMER";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateCashierAccountDetails(CashierUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO) {
        if (cashierRepo.existsById(cashierUpdateAccountDetailsDTO.getCashierId())){
            Cashier cashier = cashierRepo.getReferenceById(cashierUpdateAccountDetailsDTO.getCashierId());

            cashier.setCashierFirstName(cashierUpdateAccountDetailsDTO.getCashierFirstName());
            cashier.setCashierLastName(cashierUpdateAccountDetailsDTO.getCashierLastName());
            cashier.setGender(cashierUpdateAccountDetailsDTO.getGender());
            cashier.setCashierAddress(cashierUpdateAccountDetailsDTO.getCashierAddress());
            cashier.setDateOfBirth(cashierUpdateAccountDetailsDTO.getDateOfBirth());

            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "Successfully Update cashier account details";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateCashierPassword(CashierPasswordResetDTO cashierPasswordResetDTO) {
        if (cashierRepo.existsById(cashierPasswordResetDTO.getCashierId())){
            Cashier cashier = cashierRepo.getReferenceById(cashierPasswordResetDTO.getCashierId());

            cashier.setCashierPassword(cashierPasswordResetDTO.getCashierPassword());
            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "Successfully Reset cashier password";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateRecentPin(CashierRecentPinUpdateDTO cashierRecentPinUpdateDTO) {
        if (cashierRepo.existsById(cashierRecentPinUpdateDTO.getCashierId())){
            Cashier cashier = cashierRepo.getReferenceById(cashierRecentPinUpdateDTO.getCashierId());

            cashier.setPin(cashierRecentPinUpdateDTO.getPin());
            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "Successfully Reset cashier PIN";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateCashierBankAccountDetails(CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        long cashierId = cashierUpdateBankAccountDTO.getCashierId();

        if (cashierRepo.existsById(cashierId)) {
            Cashier cashier = cashierRepo.getReferenceById(cashierId);

            // Check if the cashier already has bank details
            CashierBankDetails existingBankDetails = cashierBankDetailsRepo.findById(cashierId).orElse(null);

            if (existingBankDetails != null) {
                // Update existing bank details
                existingBankDetails.setBankName(cashierUpdateBankAccountDTO.getBankName());
                existingBankDetails.setBankBranchName(cashierUpdateBankAccountDTO.getBankBranchName());
                existingBankDetails.setBankAccountNumber(cashierUpdateBankAccountDTO.getBankAccountNumber());
                existingBankDetails.setCashierDescription(cashierUpdateBankAccountDTO.getCashierDescription());
                existingBankDetails.setMonthlyPayment(cashierUpdateBankAccountDTO.getMonthlyPayment());
                existingBankDetails.setMonthlyPaymentStatus(cashierUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(existingBankDetails);
            } else {
                // Create new bank details if not present
                CashierBankDetails newBankDetails = new CashierBankDetails();
               // newBankDetails.setCashierId(cashierId);
                newBankDetails.setBankName(cashierUpdateBankAccountDTO.getBankName());
                newBankDetails.setBankBranchName(cashierUpdateBankAccountDTO.getBankBranchName());
                newBankDetails.setBankAccountNumber(cashierUpdateBankAccountDTO.getBankAccountNumber());
                newBankDetails.setCashierDescription(cashierUpdateBankAccountDTO.getCashierDescription());
                newBankDetails.setMonthlyPayment(cashierUpdateBankAccountDTO.getMonthlyPayment());
                newBankDetails.setMonthlyPaymentStatus(cashierUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(newBankDetails);
            }

            return "Successfully updated cashier bank account details";
        } else {
            throw new NotFoundException("No data found for that id");
        }
    }


    @Override
    public CashierDTO getCashierById(long cashierId) {
        if (cashierRepo.existsById(cashierId)){
            Cashier cashier = cashierRepo.getReferenceById(cashierId);

            // can use mappers to easily below that task
            CashierDTO cashierDTO = new CashierDTO(
                    cashier.getCashierId(),
                    cashier.getCashierNicName(),
                    cashier.getCashierFirstName(),
                    cashier.getCashierLastName(),
                    cashier.getCashierPassword(),
                    cashier.getCashierEmail(),
                    cashier.getCashierPhone(),
                    cashier.getCashierAddress(),
                    cashier.getCashierSalary(),
                    cashier.getCashierNic(),
                    cashier.isActiveStatus(),
                    cashier.getGender(),
                    cashier.getDateOfBirth(),
                    cashier.getRole(),
                    cashier.getPin(),
                    cashier.getProfileImage()
                    //(Order) cashier.getOrders()
            );
            return cashierDTO;
        }else {
           throw  new NotFoundException("No cashier found for that id");
        }

    }

    @Override
    public CashierDTO getCashierByIdWithImage(long cashierId) {
        if (cashierRepo.existsById(cashierId)){
            Cashier cashier = cashierRepo.getReferenceById(cashierId);

            // can use mappers to easily below that task
            CashierDTO cashierDTO = new CashierDTO(
                    cashier.getCashierId(),
                    cashier.getCashierNicName(),
                    cashier.getCashierFirstName(),
                    cashier.getCashierLastName(),
                    cashier.getCashierPassword(),
                    cashier.getCashierEmail(),
                    cashier.getCashierPhone(),
                    cashier.getCashierAddress(),
                    cashier.getCashierSalary(),
                    cashier.getCashierNic(),
                    cashier.isActiveStatus(),
                    cashier.getGender(),
                    cashier.getDateOfBirth(),
                    cashier.getRole(),
                    cashier.getPin(),
                    cashier.getProfileImage()
                    //(Order) cashier.getOrders()
            );
            return cashierDTO;
        }else {
            throw  new NotFoundException("No cashier found for that id");
        }
    }

    @Override
    public byte[] getImageData(long cashierId) {
        Optional<Cashier> branchOptional = cashierRepo.findById(cashierId);
        return branchOptional.map(Cashier::getProfileImage).orElse(null);
    }

    @Override
    public String deleteCashier(long cashierId) {
        if (cashierRepo.existsById(cashierId)){
            cashierRepo.deleteById(cashierId);

            return "deleted succesfully : "+ cashierId;
        }else {
            throw new NotFoundException("No cashier found for that id");
        }
    }
    @Override
    public List<CashierUpdateBankAccountDTO> getAllCashiersBankDetails() {
        List<CashierBankDetails> getAllCashiersBankDetails = cashierBankDetailsRepo.findAll();

        if (getAllCashiersBankDetails.size()>0){
            List<CashierUpdateBankAccountDTO> cashierUpdateBankAccountDTOList = new ArrayList<>();
            for (CashierBankDetails cashierBankDetails: getAllCashiersBankDetails){
                CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO = new CashierUpdateBankAccountDTO(
                        cashierBankDetails.getCashierBankDetailsId(),
                        cashierBankDetails.getBankName(),
                        cashierBankDetails.getBankBranchName(),
                        cashierBankDetails.getBankAccountNumber(),
                        cashierBankDetails.getCashierDescription(),
                        cashierBankDetails.getMonthlyPayment(),
                        cashierBankDetails.getMonthlyPaymentStatus(),
                        cashierBankDetails.getCashierId()
                );
                cashierUpdateBankAccountDTOList.add(cashierUpdateBankAccountDTO);
            }
            return cashierUpdateBankAccountDTOList;
        }else {
            throw new NotFoundException("No Cashier Bank Details Found");
        }
    }



    @Override
    public List<CashierDTO> getAllCashiers() {
        List<Cashier> getAllCashiers = cashierRepo.findAll();

        if (getAllCashiers.size() > 0){
            List<CashierDTO> cashierDTOList = new ArrayList<>();
            for (Cashier cashier: getAllCashiers){
                CashierDTO cashierDTO = new CashierDTO(
                        cashier.getCashierId(),
                        cashier.getCashierNicName(),
                        cashier.getCashierFirstName(),
                        cashier.getCashierLastName(),
                        cashier.getCashierPassword(),
                        cashier.getCashierEmail(),
                        cashier.getCashierPhone(),
                        cashier.getCashierAddress(),
                        cashier.getCashierSalary(),
                        cashier.getCashierNic(),
                        cashier.isActiveStatus(),
                        cashier.getGender(),
                        cashier.getDateOfBirth(),
                        cashier.getRole(),
                        cashier.getPin(),
                        cashier.getProfileImage()
                      // (Order) cashier.getOrders()
                );
                cashierDTOList.add(cashierDTO);
            }
            return cashierDTOList;
        }else {
            throw new NotFoundException("No Cashier Found");
        }
    }


    @Override
    public List<CashierDTO> getAllCashiersByActiveState(boolean activeState) {
        List<Cashier> getAllCashiers = cashierRepo.findByIsActiveStatusEquals(activeState);
        if (getAllCashiers.size() > 0){
            List<CashierDTO> cashierDTOList = new ArrayList<>();
            for (Cashier cashier: getAllCashiers){
                CashierDTO cashierDTO = new CashierDTO(
                        cashier.getCashierId(),
                        cashier.getCashierNicName(),
                        cashier.getCashierFirstName(),
                        cashier.getCashierLastName(),
                        cashier.getCashierPassword(),
                        cashier.getCashierEmail(),
                        cashier.getCashierPhone(),
                        cashier.getCashierAddress(),
                        cashier.getCashierSalary(),
                        cashier.getCashierNic(),
                        cashier.isActiveStatus(),
                        cashier.getGender(),
                        cashier.getDateOfBirth(),
                        cashier.getRole(),
                        cashier.getPin(),
                        cashier.getProfileImage()
                      //  (Order) cashier.getOrders()
                );
                cashierDTOList.add(cashierDTO);
            }
            return cashierDTOList;
        }else {
           // throw new RuntimeException("No Cashier Found");
            throw new NotFoundException("No Cashier Found");
        }
    }

    @Override
    public String updateCashierBankAccountDetailsByCashierId(long cashierId, CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        // Fetch the Cashier entity by cashierId
        Cashier cashier = cashierRepo.findById(cashierId).orElse(null);

        // Check if the Cashier entity exists
        if (cashier != null) {
            // Create a new CashierBankDetails entity
            CashierBankDetails newBankDetails = new CashierBankDetails();

            // Set attributes with data from cashierUpdateBankAccountDTO
            newBankDetails.setBankName(cashierUpdateBankAccountDTO.getBankName());
            newBankDetails.setBankBranchName(cashierUpdateBankAccountDTO.getBankBranchName());
            newBankDetails.setBankAccountNumber(cashierUpdateBankAccountDTO.getBankAccountNumber());
            newBankDetails.setCashierDescription(cashierUpdateBankAccountDTO.getCashierDescription());
            newBankDetails.setMonthlyPayment(cashierUpdateBankAccountDTO.getMonthlyPayment());
            newBankDetails.setMonthlyPaymentStatus(cashierUpdateBankAccountDTO.isMonthlyPaymentStatus());
            newBankDetails.setCashierId(cashierId);

            // Save the new CashierBankDetails entity
            cashierBankDetailsRepo.save(newBankDetails);

            return "Cashier bank account details created successfully.";
        } else {
            return "Cashier not found.";
        }
    }




}