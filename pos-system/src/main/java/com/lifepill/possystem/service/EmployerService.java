package com.lifepill.possystem.service;


import com.lifepill.possystem.dto.*;
import com.lifepill.possystem.dto.requestDTO.ChangeManagerDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.*;
import com.lifepill.possystem.dto.responseDTO.ChangeManagerResponseDTO;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Role;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployerService {

    String saveEmployer(EmployerDTO employerDTO);

    String saveEmployerWithoutImage(EmployerWithoutImageDTO employerWithoutImageDTO);

    String updateEmployer(Long cashierId, EmployerAllDetailsUpdateDTO employerAllDetailsUpdateDTO);

    EmployerDTO getEmployerById(long cashierId);

    EmployerS3DTO getEmployerS3ById(long cashierId);

    String deleteEmployer(long cashierId);

    List<EmployerDTO> getAllEmployer();

    List<EmployerDTO> getAllEmployerByActiveState(boolean activeState);

    String updateEmployerAccountDetails(EmployerUpdateAccountDetailsDTO employerUpdateAccountDetailsDTO);

    String updateEmployerPassword(EmployerPasswordResetDTO employerPasswordResetDTO);

    String updateRecentPin(EmployerRecentPinUpdateDTO employerRecentPinUpdateDTO);

    EmployerWithBankDTO updateEmployerBankAccountDetails(EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO);

    List<EmployerUpdateBankAccountDTO> getAllEmployerBankDetails();

    EmployerDTO getEmployerByIdWithImage(long employerId);

    byte[] getImageData(long cashierId);

    String updateEmployerBankAccountDetailsByCashierId(long employerId, EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO);

    List<EmployerDTO> getAllEmployerByBranchId(long branchId);

    List<EmployerDTO> getAllEmployerByRole(Role role);

    EmployerDTO getEmployerByUsername(String username);

    EmployerBankDetailsDTO getEmployerBankDetailsById(long employerId);

    List<EmployerWithBankDTO> getAllEmployersWithBankDetails();

    EmployerWithBankDTO getEmployerWithBankDetailsById(long employerId);

    EmployerS3DTO createEmployer(MultipartFile file, Long branchId, EmployerNewDTO employerNewDTO) throws IOException;

    InputStreamResource getEmployerImage(String profileImageUrl);

    void updateEmployerImage(Long employerId, MultipartFile file) throws IOException;

    Employer findByUsername(String username);

    List<EmployerDTO> getEmployersByBranchIdAndRole(long branchId, Role role);

    List<EmployerDTO> getAllManagersByBranchId(long branchId);

    EmployerDTO updateOrCreateBranchManager(long branchId, UpdateManagerDTO employerDTO);

    ChangeManagerResponseDTO changeBranchManager(ChangeManagerDTO changeManagerDTO);

    List<EmployerIdNameDTO> getEmployersByBranchId(long branchId);
}
