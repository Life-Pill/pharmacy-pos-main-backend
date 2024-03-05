package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    public String saveBranch(BranchDTO branchDTO);

    List<BranchDTO> getAllBranches();

    public BranchDTO getBranchById(int branchId);

    String deleteBranch(int branchId);
}
