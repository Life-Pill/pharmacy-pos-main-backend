package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.EmployerWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.*;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.EmployerBankDetails;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerBankDetailsRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.service.EmployerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployerServiceIMPL implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private EmployerBankDetailsRepository cashierBankDetailsRepo;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String saveEmployer(EmployerDTO employerDTO){
        // check if the cashier already exists email or id
        if (employerRepository.existsById(employerDTO.getEmployerId()) || employerRepository.existsAllByEmployerEmail(employerDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {
            // Retrieve the Branch entity by its ID
            Branch branch = branchRepository.findById(employerDTO.getBranchId())
                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + employerDTO.getBranchId()));

            // Map EmployerDTO to Employer entity
            Employer employer = modelMapper.map(employerDTO, Employer.class);

            // Set the Branch entity to the Employer
            employer.setBranch(branch);

            // Save the Employer entity
            employerRepository.save(employer);
            return "Employer Saved";
        }
    }


    @Override
    public String saveEmployerWithoutImage(EmployerWithoutImageDTO employerWithoutImageDTO) {
        // check if the employer already exists email or id
        if (employerRepository.existsById(employerWithoutImageDTO.getEmployerId()) || employerRepository.existsAllByEmployerEmail(employerWithoutImageDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {
/*
            Employer employer = new Employer(
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
            );*/

            //model mappers
            Employer employer = modelMapper.map(employerWithoutImageDTO, Employer.class);

            employerRepository.save(employer);
            return "Saved";
        }
    }

   /* @Override
    public String updateCashier(EmployerUpdateDTO cashierUpdateDTO) {
        if (cashierRepo.existsById(cashierUpdateDTO.getCashierId())){
            Employer cashier = cashierRepo.getReferenceById(cashierUpdateDTO.getCashierId());

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
    }*/

    @Override
    public String updateEmployer(Long employerId, EmployerAllDetailsUpdateDTO employerAllDetailsUpdateDTO) {
        // Check if the cashier exists
        Employer existingEmployer = employerRepository.findById(employerId)
                .orElseThrow(() -> new NotFoundException("Employer not found with ID: " + employerId));

        // Check if the email is already associated with another cashier
        if (!existingEmployer.getEmployerEmail().equals(employerAllDetailsUpdateDTO.getEmployerEmail()) &&
                employerRepository.existsAllByEmployerEmail(employerAllDetailsUpdateDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Email already exists");
        }

        // Map updatedCashierDTO to existingEmployer
        modelMapper.map(employerAllDetailsUpdateDTO, existingEmployer);

        // If the branch ID is provided, update the branch
        if (employerAllDetailsUpdateDTO.getBranchId() != 0) {
            Branch branch = branchRepository.findById(employerAllDetailsUpdateDTO.getBranchId())
                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + employerAllDetailsUpdateDTO.getBranchId()));
            existingEmployer.setBranch(branch);
        }

        // Save the updated cashier
        employerRepository.save(existingEmployer);

        return "Employer updated successfully";
    }


    @Override
    public String updateEmployerAccountDetails(EmployerUpdateAccountDetailsDTO employerUpdateAccountDetailsDTO) {
        if (employerRepository.existsById(employerUpdateAccountDetailsDTO.getEmployerId())){
            Employer employer = employerRepository.getReferenceById(employerUpdateAccountDetailsDTO.getEmployerId());

            employer.setEmployerFirstName(employerUpdateAccountDetailsDTO.getEmployerFirstName());
            employer.setEmployerLastName(employerUpdateAccountDetailsDTO.getEmployerLastName());
            employer.setGender(employerUpdateAccountDetailsDTO.getGender());
            employer.setEmployerAddress(employerUpdateAccountDetailsDTO.getEmployerAddress());
            employer.setDateOfBirth(employerUpdateAccountDetailsDTO.getDateOfBirth());

            employerRepository.save(employer);

            System.out.println(employer);

            return "Successfully Update employer account details";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateEmployerPassword(EmployerPasswordResetDTO employerPasswordResetDTO) {
        if (employerRepository.existsById(employerPasswordResetDTO.getEmployerId())){
            Employer employer = employerRepository.getReferenceById(employerPasswordResetDTO.getEmployerId());

            employer.setEmployerPassword(employerPasswordResetDTO.getEmployerPassword());
            employerRepository.save(employer);

            System.out.println(employer);

            return "Successfully Reset employer password";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateRecentPin(EmployerRecentPinUpdateDTO employerRecentPinUpdateDTO) {
        if (employerRepository.existsById(employerRecentPinUpdateDTO.getEmployerId())){
            Employer employer = employerRepository.getReferenceById(employerRecentPinUpdateDTO.getEmployerId());

            employer.setPin(employerRecentPinUpdateDTO.getPin());
            employerRepository.save(employer);

            System.out.println(employer);

            return "Successfully Reset employer PIN";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    @Override
    public String updateEmployerBankAccountDetails(EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO) {
        long cashierId = employerUpdateBankAccountDTO.getEmployerId();

        if (employerRepository.existsById(cashierId)) {
            Employer employer = employerRepository.getReferenceById(cashierId);

            // Check if the employer already has bank details
            EmployerBankDetails existingBankDetails = cashierBankDetailsRepo.findById(cashierId).orElse(null);

            if (existingBankDetails != null) {
                // Update existing bank details
                existingBankDetails.setBankName(employerUpdateBankAccountDTO.getBankName());
                existingBankDetails.setBankBranchName(employerUpdateBankAccountDTO.getBankBranchName());
                existingBankDetails.setBankAccountNumber(employerUpdateBankAccountDTO.getBankAccountNumber());
                existingBankDetails.setEmployerDescription(employerUpdateBankAccountDTO.getEmployerDescription());
                existingBankDetails.setMonthlyPayment(employerUpdateBankAccountDTO.getMonthlyPayment());
                existingBankDetails.setMonthlyPaymentStatus(employerUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(existingBankDetails);
            } else {
                // Create new bank details if not present
                EmployerBankDetails newBankDetails = new EmployerBankDetails();
               // newBankDetails.setCashierId(cashierId);
                newBankDetails.setBankName(employerUpdateBankAccountDTO.getBankName());
                newBankDetails.setBankBranchName(employerUpdateBankAccountDTO.getBankBranchName());
                newBankDetails.setBankAccountNumber(employerUpdateBankAccountDTO.getBankAccountNumber());
                newBankDetails.setEmployerDescription(employerUpdateBankAccountDTO.getEmployerDescription());
                newBankDetails.setMonthlyPayment(employerUpdateBankAccountDTO.getMonthlyPayment());
                newBankDetails.setMonthlyPaymentStatus(employerUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(newBankDetails);
            }

            return "Successfully updated employer bank account details";
        } else {
            throw new NotFoundException("No data found for that id");
        }
    }


    @Override
    public EmployerDTO getEmployerById(long employerId) {
        if (employerRepository.existsById(employerId)){
            Employer employer = employerRepository.getReferenceById(employerId);
            EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);

           /* EmployerDTO employerDTO = new EmployerDTO(
                    employer.getCashierId(),
                    employer.getBranch().getBranchId(),
                    employer.getCashierNicName(),
                    employer.getCashierFirstName(),
                    employer.getCashierLastName(),
                    employer.getCashierPassword(),
                    employer.getCashierEmail(),
                    employer.getCashierPhone(),
                    employer.getCashierAddress(),
                    employer.getCashierSalary(),
                    employer.getCashierNic(),
                    employer.isActiveStatus(),
                    employer.getGender(),
                    employer.getDateOfBirth(),
                    employer.getRole(),
                    employer.getPin(),
                    employer.getProfileImage()
                    //(Order) employer.getOrders()
            );*/
            return employerDTO;
        }else {
           throw  new NotFoundException("No employer found for that id");
        }

    }

    @Override
    public EmployerDTO getEmployerByIdWithImage(long employerId) {
        if (employerRepository.existsById(employerId)){
            Employer employer = employerRepository.getReferenceById(employerId);

/*             can use mappers to easily below that task
            EmployerDTO employerDTO = new EmployerDTO(
                    employer.getCashierId(),
                    employer.getCashierNicName(),
                    employer.getCashierFirstName(),
                    employer.getCashierLastName(),
                    employer.getCashierPassword(),
                    employer.getCashierEmail(),
                    employer.getCashierPhone(),
                    employer.getCashierAddress(),
                    employer.getCashierSalary(),
                    employer.getCashierNic(),
                    employer.isActiveStatus(),
                    employer.getGender(),
                    employer.getDateOfBirth(),
                    employer.getRole(),
                    employer.getPin(),
                    employer.getProfileImage()
                    //(Order) employer.getOrders()
            );*/
            EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);

            return employerDTO;
        }else {
            throw  new NotFoundException("No employer found for that id");
        }
    }

    @Override
    public byte[] getImageData(long employerId) {
        Optional<Employer> branchOptional = employerRepository.findById(employerId);
        return branchOptional.map(com.lifepill.possystem.entity.Employer::getProfileImage).orElse(null);
    }

    @Override
    public String deleteEmployer(long employerId) {
        if (employerRepository.existsById(employerId)){
            employerRepository.deleteById(employerId);

            return "deleted succesfully : "+ employerId;
        }else {
            throw new NotFoundException("No employer found for that id");
        }
    }
    @Override
    public List<EmployerUpdateBankAccountDTO> getAllEmployerBankDetails() {
        List<EmployerBankDetails> getAllCashiersBankDetails = cashierBankDetailsRepo.findAll();

        if (!getAllCashiersBankDetails.isEmpty()){
            List<EmployerUpdateBankAccountDTO> cashierUpdateBankAccountDTOList = new ArrayList<>();
            for (EmployerBankDetails employerBankDetails : getAllCashiersBankDetails){
                EmployerUpdateBankAccountDTO cashierUpdateBankAccountDTO = new EmployerUpdateBankAccountDTO(
                        employerBankDetails.getEmployerBankDetailsId(),
                        employerBankDetails.getBankName(),
                        employerBankDetails.getBankBranchName(),
                        employerBankDetails.getBankAccountNumber(),
                        employerBankDetails.getEmployerDescription(),
                        employerBankDetails.getMonthlyPayment(),
                        employerBankDetails.getMonthlyPaymentStatus(),
                        employerBankDetails.getEmployerId()
                );
                cashierUpdateBankAccountDTOList.add(cashierUpdateBankAccountDTO);
            }
            return cashierUpdateBankAccountDTOList;
        }else {
            throw new NotFoundException("No Employer Bank Details Found");
        }
    }



    @Override
    public List<EmployerDTO> getAllEmployer() {
        List<Employer> getAllEmployers = employerRepository.findAll();

        if (getAllEmployers.size() > 0){
            List<EmployerDTO> employerDTOList = new ArrayList<>();
            for (Employer employer : getAllEmployers){
/*                EmployerDTO employerDTO = new EmployerDTO(
                        employer.getCashierId(),
                        employer.getCashierNicName(),
                        employer.getCashierFirstName(),
                        employer.getCashierLastName(),
                        employer.getCashierPassword(),
                        employer.getCashierEmail(),
                        employer.getCashierPhone(),
                        employer.getCashierAddress(),
                        employer.getCashierSalary(),
                        employer.getCashierNic(),
                        employer.isActiveStatus(),
                        employer.getGender(),
                        employer.getDateOfBirth(),
                        employer.getRole(),
                        employer.getPin(),
                        employer.getProfileImage()
                      // (Order) employer.getOrders()
                );*/
                EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);
                employerDTOList.add(employerDTO);
            }
            return employerDTOList;
        }else {
            throw new NotFoundException("No Employer Found");
        }
    }


    @Override
    public List<EmployerDTO> getAllEmployerByActiveState(boolean activeState) {
        List<Employer> getAllEmployers = employerRepository.findByIsActiveStatusEquals(activeState);
        if (getAllEmployers.size() > 0){
            List<EmployerDTO> employerDTOList = new ArrayList<>();
            for (Employer employer : getAllEmployers){
/*                EmployerDTO employerDTO = new EmployerDTO(
                        employer.getCashierId(),
                        employer.getCashierNicName(),
                        employer.getCashierFirstName(),
                        employer.getCashierLastName(),
                        employer.getCashierPassword(),
                        employer.getCashierEmail(),
                        employer.getCashierPhone(),
                        employer.getCashierAddress(),
                        employer.getCashierSalary(),
                        employer.getCashierNic(),
                        employer.isActiveStatus(),
                        employer.getGender(),
                        employer.getDateOfBirth(),
                        employer.getRole(),
                        employer.getPin(),
                        employer.getProfileImage()
                      //  (Order) employer.getOrders()
                );*/
                EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);
                employerDTOList.add(employerDTO);
            }
            return employerDTOList;
        }else {
           // throw new RuntimeException("No Employer Found");
            throw new NotFoundException("No Employer Found");
        }
    }

    @Override
    public String updateEmployerBankAccountDetailsByCashierId(long cashierId, EmployerUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        // Fetch the Employer entity by cashierId
        Employer employer = employerRepository.findById(cashierId).orElse(null);

        // Check if the Employer entity exists
        if (employer != null) {
            // Create a new EmployerBankDetails entity
            EmployerBankDetails newBankDetails = new EmployerBankDetails();

            // Set attributes with data from cashierUpdateBankAccountDTO
            newBankDetails.setBankName(cashierUpdateBankAccountDTO.getBankName());
            newBankDetails.setBankBranchName(cashierUpdateBankAccountDTO.getBankBranchName());
            newBankDetails.setBankAccountNumber(cashierUpdateBankAccountDTO.getBankAccountNumber());
            newBankDetails.setEmployerDescription(cashierUpdateBankAccountDTO.getEmployerDescription());
            newBankDetails.setMonthlyPayment(cashierUpdateBankAccountDTO.getMonthlyPayment());
            newBankDetails.setMonthlyPaymentStatus(cashierUpdateBankAccountDTO.isMonthlyPaymentStatus());
            newBankDetails.setEmployerId(cashierId);

            // Save the new EmployerBankDetails entity
            cashierBankDetailsRepo.save(newBankDetails);

            return "Employer bank account details created successfully.";
        } else {
            return "Employer not found.";
        }
    }

    @Override
    public List<EmployerDTO> getAllEmployerByBranchId(int branchId) {
        // Retrieve the branch by its ID
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + branchId));

        // Retrieve all employers associated with the branch
        List<Employer> employers = employerRepository.findAllByBranch(branch);

        // Map cashier entities to DTOs
        List<EmployerDTO> employerDTOS = employers.stream()
                .map(cashier -> modelMapper.map(cashier, EmployerDTO.class))
                .collect(Collectors.toList());

        return employerDTOS;
    }

    @Override
    public List<EmployerDTO> getAllEmployerByRole(Role role) {
        List<Employer> employers = employerRepository.findAllByRole(role);

        return employers.stream()
                .map(employer -> modelMapper.map(employer, EmployerDTO.class))
                .collect(Collectors.toList());
    }

}