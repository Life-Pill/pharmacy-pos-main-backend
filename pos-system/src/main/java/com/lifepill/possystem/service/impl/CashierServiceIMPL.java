package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdateDTO;
import com.lifepill.possystem.entity.Cashier;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.CashierRepo;
import com.lifepill.possystem.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CashierServiceIMPL implements CashierService {

    @Autowired
    private CashierRepo  cashierRepo;

    @Override
    public String saveCashier(CashierDTO cashierDTO){
        Cashier cashier = new Cashier(
                cashierDTO.getCashierId(),
                cashierDTO.getCashierName(),
                cashierDTO.getCashierEmail(),
                cashierDTO.getCashierPhone(),
                cashierDTO.getCashierAddress(),
                cashierDTO.getCashierSalary(),
                cashierDTO.getCashierNic(),
                cashierDTO.isActiveStatus()
                );
        cashierRepo.save(cashier);
        return "Saved";
    }

    @Override
    public String updateCashier(CashierUpdateDTO cashierUpdateDTO) {
        if (cashierRepo.existsById(cashierUpdateDTO.getCashierId())){
            Cashier cashier = cashierRepo.getReferenceById(cashierUpdateDTO.getCashierId());

            cashier.setCashierName(cashierUpdateDTO.getCashierName());
            cashier.setCashierEmail(cashierUpdateDTO.getCashierEmail());
            cashier.setCashierPhone(cashierUpdateDTO.getCashierPhone());
            cashier.setCashierSalary(cashierUpdateDTO.getCashierSalary());

            cashierRepo.save(cashier);

            System.out.println(cashier);

            return "UPDATED CUSTOMER";
        }else {
            throw new RuntimeException("no data found for that id");
        }

    }

    @Override
    public CashierDTO getCashierById(int cashierId) {
        if (cashierRepo.existsById(cashierId)){
            Cashier cashier = cashierRepo.getReferenceById(cashierId);

            // can use mappers to easily below that task
            CashierDTO cashierDTO = new CashierDTO(
                    cashier.getCashierId(),
                    cashier.getCashierName(),
                    cashier.getCashierEmail(),
                    cashier.getCashierPhone(),
                    cashier.getCashierAddress(),
                    cashier.getCashierSalary(),
                    cashier.getCashierNic(),
                    cashier.isActiveStatus()
            );
            return cashierDTO;
        }else {
           throw  new RuntimeException("No cashier found for that id");
        }

    }

    @Override
    public String deleteCashier(int cashierId) {
        if (cashierRepo.existsById(cashierId)){
            cashierRepo.deleteById(cashierId);

            return "deleted succesfully : "+ cashierId;
        }else {
            throw new RuntimeException("No cashier found for that id");
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
                        cashier.getCashierName(),
                        cashier.getCashierEmail(),
                        cashier.getCashierPhone(),
                        cashier.getCashierAddress(),
                        cashier.getCashierSalary(),
                        cashier.getCashierNic(),
                        cashier.isActiveStatus()
                );
                cashierDTOList.add(cashierDTO);
            }
            return cashierDTOList;
        }else {
            throw new RuntimeException("No Cashier Found");
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
                        cashier.getCashierName(),
                        cashier.getCashierEmail(),
                        cashier.getCashierPhone(),
                        cashier.getCashierAddress(),
                        cashier.getCashierSalary(),
                        cashier.getCashierNic(),
                        cashier.isActiveStatus()
                );
                cashierDTOList.add(cashierDTO);
            }
            return cashierDTOList;
        }else {
           // throw new RuntimeException("No Cashier Found");
            throw new NotFoundException("No Cashier Found");
        }
    }
}