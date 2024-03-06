package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
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
    public void saveBranch(BranchDTO branchDTO) {
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

    @Override
    public BranchDTO getBranchById(int branchId) {
        if (branchRepo.existsById(branchId)){
            Branch branch = branchRepo.getReferenceById(branchId);

            // can use mappers to easily below that task
            BranchDTO branchDTO  = new BranchDTO(
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
            return branchDTO;
        }else {
            throw  new NotFoundException("No Branch found for that id");
        }

    }

    @Override
    public String deleteBranch(int branchId) {
        if (branchRepo.existsById(branchId)){
            branchRepo.deleteById(branchId);

            return "deleted succesfully : "+ branchId;
        }else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

    @Override
    public String updateBranch(BranchUpdateDTO branchUpdateDTO) {
        if (branchRepo.existsById(branchUpdateDTO.getBranchId())){
            Branch branch = branchRepo.getReferenceById(branchUpdateDTO.getBranchId());
            branch.setBranchName(branchUpdateDTO.getBranchName());
            branch.setBranchAddress(branchUpdateDTO.getBranchAddress());
            branch.setBranchContact(branchUpdateDTO.getBranchContact());
            branch.setBranchFax(branchUpdateDTO.getBranchFax());
            branch.setBranchEmail(branchUpdateDTO.getBranchEmail());
            branch.setBranchDescription(branchUpdateDTO.getBranchDescription());
            branch.setBranchImage(branchUpdateDTO.getBranchImage());
            branch.setBranchStatus(branchUpdateDTO.isBranchStatus());
            branch.setBranchLocation(branchUpdateDTO.getBranchLocation());
            branch.setBranchCreatedOn(branchUpdateDTO.getBranchCreatedOn());
            branch.setBranchCreatedBy(branchUpdateDTO.getBranchCreatedBy());

            branchRepo.save(branch);

            System.out.println(branch);

            return "UPDATED BRANCH";
        }else {
            throw new NotFoundException("No Branch data found for that id");
        }
    }

}