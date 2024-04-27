package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.PharmacyBranchResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.helper.SaveImageHelper;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceIMPL implements BranchService {

    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void saveBranch(BranchDTO branchDTO, MultipartFile image) {
        if (branchRepository.existsById(branchDTO.getBranchId()) || branchRepository.existsByBranchEmail(branchDTO.getBranchEmail())) {
            throw new EntityDuplicationException("Branch already exists");
        } else {
            byte[] imageBytes = SaveImageHelper.saveImage(image);
            Branch branch = modelMapper.map(branchDTO, Branch.class);
            branch.setBranchImage(imageBytes);


            branchRepository.save(branch);
        }
    }
    @Override
    public byte[] getImageData(int branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        return branchOptional.map(Branch::getBranchImage).orElse(null);
    }



    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> getAllBranches = branchRepository.findAll();
        if (!getAllBranches.isEmpty()){
            List<BranchDTO> branchDTOList = new ArrayList<>();
            for (Branch branch: getAllBranches){
                BranchDTO employerDTO = new BranchDTO(
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
                branchDTOList.add(employerDTO);
            }
            return branchDTOList;
        }else {
            throw new NotFoundException("No Branch Found");
        }
    }

    @Override
    public BranchDTO getBranchById(int branchId) {
        if (branchRepository.existsById(branchId)){
            Branch branch = branchRepository.getReferenceById(branchId);

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
        if (branchRepository.existsById(branchId)){
            branchRepository.deleteById(branchId);

            return "deleted succesfully : "+ branchId;
        }else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

    public String updateBranch(int branchId, BranchUpdateDTO branchUpdateDTO, MultipartFile image) {
        if (!branchRepository.existsById(branchId)) {
            throw new EntityNotFoundException("Branch not found");
        }

        Optional<Branch> branchOptional = branchRepository.findById(branchId);
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

            branchRepository.save(branch);
            return "updated";
        } else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

    @Override
    public void updateBranchImage(int branchId, MultipartFile image) {
        if (!branchRepository.existsById(branchId)) {
            throw new NotFoundException("Branch not found");
        }

        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();

            if (image != null && !image.isEmpty()) {
                byte[] imageBytes = SaveImageHelper.saveImage(image);
                branch.setBranchImage(imageBytes);
            }

            branchRepository.save(branch);
        } else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

    @Override
    public void updateBranchWithoutImage(int branchId, BranchUpdateDTO branchUpdateDTO) {
        if (!branchRepository.existsById(branchId)) {
            throw new NotFoundException("Branch not found");
        }

        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();

            if (branchUpdateDTO.getBranchName() != null) {
                branch.setBranchName(branchUpdateDTO.getBranchName());
            }
            if (branchUpdateDTO.getBranchAddress() != null) {
                branch.setBranchAddress(branchUpdateDTO.getBranchAddress());
            }
            if (branchUpdateDTO.getBranchContact() != null) {
                branch.setBranchContact(branchUpdateDTO.getBranchContact());
            }
            if (branchUpdateDTO.getBranchFax() != null) {
                branch.setBranchFax(branchUpdateDTO.getBranchFax());
            }
            if (branchUpdateDTO.getBranchEmail() != null) {
                branch.setBranchEmail(branchUpdateDTO.getBranchEmail());
            }
            if (branchUpdateDTO.getBranchDescription() != null) {
                branch.setBranchDescription(branchUpdateDTO.getBranchDescription());
            }
            if (branchUpdateDTO.getBranchImage() != null) {
                branch.setBranchImage(branchUpdateDTO.getBranchImage());
            }
            if (branchUpdateDTO.getBranchLocation() != null) {
                branch.setBranchLocation(branchUpdateDTO.getBranchLocation());
            }
            if (branchUpdateDTO.getBranchCreatedOn() != null) {
                branch.setBranchCreatedOn(branchUpdateDTO.getBranchCreatedOn());
            }
            if (branchUpdateDTO.getBranchCreatedBy() != null) {
                branch.setBranchCreatedBy(branchUpdateDTO.getBranchCreatedBy());
            }
            branch.setBranchStatus(branchUpdateDTO.isBranchStatus());


            branchRepository.save(branch);
        } else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

}