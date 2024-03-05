package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.CashierDTO;

import java.util.List;

public interface BranchService {

    public String saveBranch(BranchDTO branchDTO);

    List<BranchDTO> getAllBranches();
}
