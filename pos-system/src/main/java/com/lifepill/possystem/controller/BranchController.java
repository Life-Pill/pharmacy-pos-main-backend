package com.lifepill.possystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifepill.possystem.dto.BranchDTO;
import com.lifepill.possystem.dto.requestDTO.BranchUpdateDTO;
import com.lifepill.possystem.service.BranchService;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("lifepill/v1/branch")
@CrossOrigin
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveBranch(@RequestParam("image") MultipartFile image,@ModelAttribute BranchDTO branchDTO) {
        branchService.saveBranch(branchDTO, image);
        return "saved";
    }


    @GetMapping("/view-image/{branchId}")
    public ResponseEntity<byte[]> viewImage(@PathVariable int branchId) {
        byte[] imageData = branchService.getImageData(branchId);

        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image format
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/get-all-branches")
    public ResponseEntity<StandardResponse> getAllBranches() {
        List<BranchDTO> allBranches = branchService.getAllBranches();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", allBranches),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-id", params = "id")
    @Transactional
    public BranchDTO getBranchById(@RequestParam(value = "id") int branchId) {
        BranchDTO branchDTO = branchService.getBranchById(branchId);
        return branchDTO;
    }

    @DeleteMapping(path = "/delete-branch/{id}")
    public String deleteBranch(@PathVariable(value = "id") int branchId) {
        String deleted = branchService.deleteBranch(branchId);
        return deleted;
    }

@PutMapping(value = "/update-branch/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
@Transactional
public String updateBranch(
        @PathVariable(value = "id") int branchId,
        @RequestParam("image") MultipartFile image,
        @ModelAttribute BranchUpdateDTO branchUpdateDTO) {
    branchService.updateBranch(branchId, branchUpdateDTO, image);
    return "updated";
}

}
