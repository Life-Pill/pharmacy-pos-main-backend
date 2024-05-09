package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.EmployerWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.*;
import com.lifepill.possystem.entity.enums.Role;

import java.util.List;

public interface EmployerService {

    public String saveEmployer(EmployerDTO employerDTO);

    public String saveEmployerWithoutImage(EmployerWithoutImageDTO employerWithoutImageDTO);

//    String updateEmployer(EmployerUpdateDTO employerUpdateDTO);

    String updateEmployer(Long cashierId, EmployerAllDetailsUpdateDTO employerAllDetailsUpdateDTO);

    EmployerDTO getEmployerById(long cashierId);

    String deleteEmployer(long cashierId);

    List<EmployerDTO> getAllEmployer();

    List<EmployerDTO> getAllEmployerByActiveState(boolean activeState);

    String updateEmployerAccountDetails(EmployerUpdateAccountDetailsDTO employerUpdateAccountDetailsDTO);

    String updateEmployerPassword(EmployerPasswordResetDTO employerPasswordResetDTO);

    String updateRecentPin(EmployerRecentPinUpdateDTO employerRecentPinUpdateDTO);

    String updateEmployerBankAccountDetails(EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO);

    List<EmployerUpdateBankAccountDTO> getAllEmployerBankDetails();

    EmployerDTO getEmployerByIdWithImage(long employerId);

    byte[] getImageData(long cashierId);

    String updateEmployerBankAccountDetailsByCashierId(long employerId, EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO);

    List<EmployerDTO> getAllEmployerByBranchId(long branchId);

    List<EmployerDTO> getAllEmployerByRole(Role role);

    EmployerDTO getEmployerByUsername(String username);

    EmployerDTO getEmployerByIdWithBankDetails(long employerId);

}
