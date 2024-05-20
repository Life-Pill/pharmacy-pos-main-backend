package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BranchService {

    public void saveBranch(BranchDTO branchDTO, MultipartFile image);

    byte[] getImageData(long branchId);

    List<BranchDTO> getAllBranches();

    public BranchDTO getBranchById(long branchId);

    String deleteBranch(long branchId);

    String updateBranch(long branchId, BranchUpdateDTO branchUpdateDTO, MultipartFile image);

    void updateBranchImage(long branchId, MultipartFile image);

    void updateBranchWithoutImage(long branchId, BranchUpdateDTO branchUpdateDTO);

}
