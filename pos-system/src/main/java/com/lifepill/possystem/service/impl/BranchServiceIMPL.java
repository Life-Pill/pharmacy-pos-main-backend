package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.helper.SaveImageHelper;
import com.lifepill.possystem.repo.branchRepo.BranchRepo;
import com.lifepill.possystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceIMPL implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Override
    public void saveBranch(BranchDTO branchDTO, MultipartFile image) {
        if (branchRepo.existsById(branchDTO.getBranchId()) || branchRepo.existsByBranchEmail(branchDTO.getBranchEmail())) {
            throw new EntityDuplicationException("Branch already exists");
        } else {
            byte[] imageBytes = SaveImageHelper.saveImage(image);
            Branch branch = new Branch(
                    branchDTO.getBranchId(),
                    branchDTO.getBranchName(),
                    branchDTO.getBranchAddress(),
                    branchDTO.getBranchContact(),
                    branchDTO.getBranchFax(),
                    branchDTO.getBranchEmail(),
                    branchDTO.getBranchDescription(),
                    imageBytes,
                    branchDTO.isBranchStatus(),
                    branchDTO.getBranchLocation(),
                    branchDTO.getBranchCreatedOn(),
                    branchDTO.getBranchCreatedBy()
            );
            branchRepo.save(branch);
        }
    }


    @Override
    public byte[] getImageData(int branchId) {
        Optional<Branch> branchOptional = branchRepo.findById(branchId);
        return branchOptional.map(Branch::getBranchImage).orElse(null);
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

    public String updateBranch(int branchId, BranchUpdateDTO branchUpdateDTO, MultipartFile image) {
        if (!branchRepo.existsById(branchId)) {
            throw new EntityNotFoundException("Branch not found");
        }

        Optional<Branch> branchOptional = branchRepo.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();

            if (branchUpdateDTO.getBranchName() != null) {
                branch.setBranchName(branchUpdateDTO.getBranchName());
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
            }

            // Repeat the same pattern for other fields

            if (image != null && !image.isEmpty()) {
                byte[] imageBytes = SaveImageHelper.saveImage(image);
                branch.setBranchImage(imageBytes);
            }

            branchRepo.save(branch);
            return "updated";
        } else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

}