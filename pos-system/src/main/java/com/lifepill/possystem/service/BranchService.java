package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.BranchS3DTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BranchService {

    void saveBranch(BranchDTO branchDTO, MultipartFile image);

    byte[] getImageData(long branchId);

    List<BranchDTO> getAllBranches();

    BranchDTO getBranchById(long branchId);

    String deleteBranch(long branchId);

    void updateBranch(long branchId, BranchUpdateDTO branchUpdateDTO, MultipartFile image);

    void updateBranchImage(long branchId, MultipartFile image);

    void updateBranchWithoutImage(long branchId, BranchUpdateDTO branchUpdateDTO);

    BranchS3DTO createBranch(BranchS3DTO branchS3DTO, MultipartFile file) throws IOException;

    BranchS3DTO getBranchS3ById(long branchId);

    InputStreamResource getBranchProfileImage(String branchProfileImageUrl);
}
