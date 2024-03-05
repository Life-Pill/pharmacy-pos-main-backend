package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;

import java.util.List;

public interface BranchService {

    public void saveBranch(BranchDTO branchDTO);

    List<BranchDTO> getAllBranches();

    public BranchDTO getBranchById(int branchId);

    String deleteBranch(int branchId);
    String updateBranch(BranchUpdateDTO branchUpdateDTO);
}
