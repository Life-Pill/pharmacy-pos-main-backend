package com.lifepill.possystem.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.BranchNewUpdateDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import com.lifepill.possystem.dto.responseDTO.BranchS3DTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.helper.SaveImageHelper;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.service.BranchService;
import com.lifepill.possystem.service.S3Service;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing branches.
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BranchServiceIMPL implements BranchService {

    private BranchRepository branchRepository;
    private ModelMapper modelMapper;
    private S3Service s3Service;

    /**
     * Saves a new branch.
     *
     * @param branchDTO The branch DTO containing branch details.
     * @param image     The image file associated with the branch.
     * @throws EntityDuplicationException If a branch with the same ID or email already exists.
     */
    @Override
    @Transactional
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

    /**
     * Retrieves image data associated with a branch.
     *
     * @param branchId The ID of the branch.
     * @return The byte array representing the image data.
     */
    @Override
    public byte[] getImageData(long branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        return branchOptional.map(Branch::getBranchImage).orElse(null);
    }

    /**
     * Retrieves all branches.
     *
     * @return A list of all branches.
     * @throws NotFoundException If no branches are found.
     */
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
    public BranchDTO getBranchById(long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("No Branch found for that id"));

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
    }

    /**
     * Retrieves all branches.
     *
     * @return A list of all branches.
     * @throws NotFoundException If no branches are found.
     */
    @Override
    @Transactional
    public String deleteBranch(long branchId) {
        if (branchRepository.existsById(branchId)){
            branchRepository.deleteById(branchId);

            return "deleted succesfully : "+ branchId;
        }else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

    /**
     * Updates a branch with new details and optionally changes its image.
     *
     * @param branchId        The ID of the branch to update.
     * @param branchUpdateDTO The DTO containing updated branch details.
     * @param image           The new image file for the branch (optional).
     * @throws EntityNotFoundException If the branch with the given ID is not found.
     * @throws NotFoundException       If the branch with the given ID is not found.
     */
    @Transactional
    public void updateBranch(long branchId, BranchUpdateDTO branchUpdateDTO, MultipartFile image) {
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
//                branch.setBranchImage(branchUpdateDTO.getBranchImage());
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
        } else {
            throw new NotFoundException("No Branch found for that id");
        }
    }

    /**
     * Updates the image of a branch.
     *
     * @param branchId The ID of the branch to update.
     * @param image    The new image file for the branch.
     * @throws NotFoundException If the branch with the given ID is not found.
     */
    @Override
    @Transactional
    public void updateBranchImage(long branchId, MultipartFile image) {
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
    @Transactional
    public BranchS3DTO createBranch(BranchS3DTO branchS3DTO, MultipartFile file) throws IOException {
        // Logic to handle file upload and save the branch details
        String imageUrl = s3Service.uploadFile(branchS3DTO.getBranchEmail(), file);
        branchS3DTO.setBranchProfileImageUrl(imageUrl);

        // Save branch details to the repository
        Branch branch = new Branch();
        BeanUtils.copyProperties(branchS3DTO, branch);
        branch = branchRepository.save(branch);

        // Copy properties back to DTO from entity
        BeanUtils.copyProperties(branch, branchS3DTO);
        return branchS3DTO;
    }

    @Override
    public BranchS3DTO getBranchS3ById(long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("No Branch found for that id"));
        BranchS3DTO branchS3DTO = new BranchS3DTO();
        BeanUtils.copyProperties(branch, branchS3DTO);
        return branchS3DTO;
    }

    @Override
    public InputStreamResource getBranchProfileImage(String branchProfileImageUrl) {
        //Extract the key name from the URL
        String keyName = branchProfileImageUrl.substring(branchProfileImageUrl.lastIndexOf("/") + 1);
        S3Object s3Object = s3Service.getFile(keyName);
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        return new InputStreamResource(s3ObjectInputStream);
    }

    @Override
    @Transactional
    public void updateBranchProfileImage(long branchId, MultipartFile file) throws IOException {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("No Branch found for that id"));
        String imageUrl = s3Service.uploadFile(branch.getBranchEmail(), file);
        branch.setBranchProfileImageUrl(imageUrl);
        branchRepository.save(branch);
    }

    @Override
    @Transactional
    public BranchNewUpdateDTO updateBranchWithoutImage(long branchId, BranchNewUpdateDTO branchUpdateDTO) {
        if (!branchRepository.existsById(branchId)) {
            throw new NotFoundException("Branch not found");
        }

        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();

            // Update only if values are provided in branchUpdateDTO
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
                branch.setBranchStatus(branchUpdateDTO.isBranchStatus());

            if (branchUpdateDTO.getBranchEmail() != null) {
                branch.setBranchEmail(branchUpdateDTO.getBranchEmail());
            }
            if (branchUpdateDTO.getBranchDescription() != null) {
                branch.setBranchDescription(branchUpdateDTO.getBranchDescription());
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

           Branch updatedBranch = branchRepository.save(branch);

            return modelMapper.map(updatedBranch, BranchNewUpdateDTO.class);
        } else {
            throw new NotFoundException("No Branch found for that id");
        }
    }
}
