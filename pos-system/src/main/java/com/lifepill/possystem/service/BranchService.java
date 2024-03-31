package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BranchService {

    public void saveBranch(BranchDTO branchDTO, MultipartFile image);

    byte[] getImageData(int branchId);

    List<BranchDTO> getAllBranches();

    public BranchDTO getBranchById(int branchId);

    String deleteBranch(int branchId);
    String updateBranch(int branchId, BranchUpdateDTO branchUpdateDTO, MultipartFile image);

    void updateBranchImage(int branchId, MultipartFile image);

    void updateBranchWithoutImage(int branchId, BranchUpdateDTO branchUpdateDTO);
}
