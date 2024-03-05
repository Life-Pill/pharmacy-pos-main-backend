package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepo.BranchRepo;
import com.lifepill.possystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceIMPL implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Override
    public String saveBranch(BranchDTO branchDTO) {
        if (branchRepo.existsById(branchDTO.getBranchId()) || branchRepo.existsByBranchEmail(branchDTO.getBranchEmail())) {
            throw new EntityDuplicationException("Branch already exists");
        } else {
            Branch branch = new Branch(
                    branchDTO.getBranchId(),
                    branchDTO.getBranchName(),
                    branchDTO.getBranchAddress(),
                    branchDTO.getBranchContact(),
                    branchDTO.getBranchFax(),
                    branchDTO.getBranchEmail(),
                    branchDTO.getBranchDescription(),
                    branchDTO.getBranchImage(),
                    branchDTO.isBranchStatus(),
                    branchDTO.getBranchLocation(),
                    branchDTO.getBranchCreatedOn(),
                    branchDTO.getBranchCreatedBy()
            );
            branchRepo.save(branch);
            return "Saved ";
        }
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> getAllBranches = branchRepo.findAll();
        if (getAllBranches.size() > 0){
            List<BranchDTO> branchDTOList = new ArrayList<>();
            for (Branch branch: getAllBranches){
                BranchDTO cashierDTO = new BranchDTO(
                        branch.getBranchId(),
                        branch.getBranchName(),
                        branch.getBranchAddress(),
                        branch.getBranchContact(),
                        branch.getBranchFax(),
                        branch.getBranchEmail(),
                        branch.getBranchDescription(),
                        branch.getBranchImage(),
                        branch.isBranchStatus(),
                        branch.getBranchLocation(),
                        branch.getBranchCreatedOn(),
                        branch.getBranchCreatedBy()
                );
                branchDTOList.add(cashierDTO);
            }
            return branchDTOList;
        }else {
            throw new NotFoundException("No Branch Found");
        }
    }

}