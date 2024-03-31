package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.CashierDTO;
import com.lifepill.possystem.dto.CashierWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.CashierUpdate.*;
import com.lifepill.possystem.entity.Cashier;
import com.lifepill.possystem.entity.CashierBankDetails;
import com.lifepill.possystem.service.CashierService;
import com.lifepill.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("lifepill/v1/cashier")
@CrossOrigin
public class CashierController {

    @Autowired
    private CashierService cashierService;

    public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/save-without-image")
    public String saveCashierWithoutImage(@RequestBody CashierWithoutImageDTO cashierWithoutImageDTO) {
        cashierService.saveCashierWithoutImage(cashierWithoutImageDTO);
        return "saved";
    }

/*    @PostMapping("/save-with-image-local-and send-name-to-database")
    public String saveCashier(@ModelAttribute CashierDTO cashierDTO, @RequestParam("Profile_image") MultipartFile file) throws IOException{
        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        cashierDTO.setProfileImage(originalFilename);
        cashierService.saveCashier(cashierDTO);
        return "saved";
    }*/

//    @GetMapping(path = "/get-by-id-with-image/{id}")
//    public ResponseEntity<CashierDTO> getCashierByIdWithImage(@PathVariable(value = "id") int cashierId) {
//        CashierDTO cashierDTO = cashierService.getCashierByIdWithImage(cashierId);
//        return new ResponseEntity<CashierDTO>(cashierDTO, HttpStatus.OK);
//
//    }

    @PostMapping("/save-with-image")
    public String saveCashierWithImage(@ModelAttribute CashierDTO cashierDTO, @RequestParam("file") MultipartFile file) throws IOException {
        // Check if a file is provided
        if (!file.isEmpty()) {
            // Convert MultipartFile to byte array
            byte[] profileImage = file.getBytes();
            // Set the profile image in the cashierDTO
            cashierDTO.setProfileImage(profileImage);
        }
        // Save the cashier along with the profile photo
        cashierService.saveCashier(cashierDTO);
        return "saved";
    }

    @GetMapping("/profile-photo/{cashierId}")
    @Transactional
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable int cashierId) {
        // Retrieve the cashier entity by ID
        CashierDTO cashier = cashierService.getCashierById(cashierId);

        // Check if the cashier exists
        if (cashier != null && cashier.getProfileImage() != null) {
            // Return the profile image byte array with appropriate headers
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust content type based on image format
                    .body(cashier.getProfileImage());
        } else {
            // If the cashier or profile photo doesn't exist, return a not found response
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update")
    @Transactional
    public String updateCashier(@RequestBody CashierUpdateDTO cashierUpdateDTO) {
        String message = cashierService.updateCashier(cashierUpdateDTO);
        return message;
    }

    @PutMapping("/updateAccountDetails")
    @Transactional
    public String updateCashierAccountDetails(@RequestBody CashierUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO) {
        String message = cashierService.updateCashierAccountDetails(cashierUpdateAccountDetailsDTO);
        return message;
    }

/*    @PutMapping("/updateBankAccountDetails")
    @Transactional
    public String updateCashierBankAccountDetails(@RequestBody CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        String message = cashierService.updateCashierBankAccountDetails(cashierUpdateBankAccountDTO);
        return message;
    }*/

    @PutMapping("/updateBankAccountDetails/{cashierId}")
    @Transactional
    public String updateCashierBankAccountDetails(@PathVariable long cashierId, @RequestBody CashierUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        String message = cashierService.updateCashierBankAccountDetailsByCashierId(cashierId, cashierUpdateBankAccountDTO);
        return message;
    }


    @PutMapping("/updatePassword")
    @Transactional
    public String updateCashierPassword(@RequestBody CashierPasswordResetDTO cashierPasswordResetDTO) {
        String message = cashierService.updateCashierPassword(cashierPasswordResetDTO);
        return message;
    }

    @PutMapping("/updateRecentPin")
    @Transactional
    public String updateRecentPin(@RequestBody CashierRecentPinUpdateDTO cashierRecentPinUpdateDTO) {
        String message = cashierService.updateRecentPin(cashierRecentPinUpdateDTO);
        return message;
    }

    @GetMapping(path = "/get-by-id", params = "id")
    @Transactional
    public CashierDTO getCashierById(@RequestParam(value = "id") int cashierId) {
        CashierDTO cashierDTO = cashierService.getCashierById(cashierId);
        return cashierDTO;
    }

    @GetMapping("/view-image/{cashierId}")
    @Transactional
    public ResponseEntity<byte[]> viewImage(@PathVariable int cashierId) {
        byte[] imageData = cashierService.getImageData(cashierId);

        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image format
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete-cashier/{id}")
    public String deleteCashier(@PathVariable(value = "id") int cashierId) {
        String deleted = cashierService.deleteCashier(cashierId);
        return deleted;
    }

    @GetMapping(path = "/get-all-cashiers")
    public ResponseEntity<StandardResponse> getAllCashiers() {
        List<CashierDTO> allCashiers = cashierService.getAllCashiers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", allCashiers),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-cashiers-by-active-state/{status}")
    @Transactional
    public List<CashierDTO> getAllCashiersByActiveState(@PathVariable(value = "status") boolean activeState) {
        List<CashierDTO> allCashiers = cashierService.getAllCashiersByActiveState(activeState);
        return allCashiers;
    }

    @GetMapping(path = "/get-all-cashiers-bank-details")
    @Transactional
    public ResponseEntity<StandardResponse> getAllCashiersBankDetails() {
        List<CashierUpdateBankAccountDTO> allCashiersBankDetails = cashierService.getAllCashiersBankDetails();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", allCashiersBankDetails),
                HttpStatus.OK
        );
    }
}