package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.*;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.service.EmployerService;
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
import java.util.List;

@RestController
@RequestMapping("lifepill/v1/cashier")
@CrossOrigin
public class EmployerController {

    @Autowired
    private EmployerService cashierService;

    public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

/*    @PostMapping("/save-without-image")
    public String saveCashierWithoutImage(@RequestBody EmployerWithoutImageDTO cashierWithoutImageDTO) {
        cashierService.saveCashierWithoutImage(cashierWithoutImageDTO);
        return "saved";
    }*/

/*    @PostMapping("/save-with-image-local-and send-name-to-database")
    public String saveCashier(@ModelAttribute EmployerDTO cashierDTO, @RequestParam("Profile_image") MultipartFile file) throws IOException{
        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        cashierDTO.setProfileImage(originalFilename);
        cashierService.saveCashier(cashierDTO);
        return "saved";
    }*/

/*    @GetMapping(path = "/get-by-id-with-image/{id}")
    public ResponseEntity<EmployerDTO> getCashierByIdWithImage(@PathVariable(value = "id") int cashierId) {
        EmployerDTO cashierDTO = cashierService.getCashierByIdWithImage(cashierId);
        return new ResponseEntity<EmployerDTO>(cashierDTO, HttpStatus.OK);

    }*/

    @PostMapping("/save-with-image")
    public String saveCashierWithImage(@ModelAttribute EmployerDTO employerDTO, @RequestParam("file") MultipartFile file) throws IOException {
        // Check if a file is provided
        if (!file.isEmpty()) {
            // Convert MultipartFile to byte array
            byte[] profileImage = file.getBytes();
            // Set the profile image in the employerDTO
            employerDTO.setProfileImage(profileImage);
        }
        // Save the cashier along with the profile photo
        cashierService.saveEmployer(employerDTO);
        return "saved";
    }

    @GetMapping("/profile-photo/{employerId}")
    @Transactional
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable int employerId) {
        // Retrieve the cashier entity by ID
        EmployerDTO cashier = cashierService.getEmployerById(employerId);

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


   /* @PutMapping("/update")
    @Transactional
    public String updateCashier(@RequestBody EmployerUpdateDTO cashierUpdateDTO) {
        String message = cashierService.updateCashier(cashierUpdateDTO);
        return message;
    }*/

    // need to add both place cashier ID
   @PutMapping("/update/{employerId}")
   @Transactional
   public String updateCashier(@PathVariable Long employerId, @RequestBody EmployerAllDetailsUpdateDTO cashierAllDetailsUpdateDTO) {
       String message = cashierService.updateEmployer(employerId, cashierAllDetailsUpdateDTO);
       return message;
   }


    @PutMapping("/updateAccountDetails")
    @Transactional
    public String updateCashierAccountDetails(@RequestBody EmployerUpdateAccountDetailsDTO cashierUpdateAccountDetailsDTO) {
        String message = cashierService.updateEmployerAccountDetails(cashierUpdateAccountDetailsDTO);
        return message;
    }

/*    @PutMapping("/updateBankAccountDetails")
    @Transactional
    public String updateCashierBankAccountDetails(@RequestBody EmployerUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        String message = cashierService.updateCashierBankAccountDetails(cashierUpdateBankAccountDTO);
        return message;
    }*/

    @PutMapping("/updateBankAccountDetails/{employerId}")
    @Transactional
    public String updateCashierBankAccountDetails(@PathVariable long employerId, @RequestBody EmployerUpdateBankAccountDTO cashierUpdateBankAccountDTO) {
        String message = cashierService.updateEmployerBankAccountDetailsByCashierId(employerId, cashierUpdateBankAccountDTO);
        return message;
    }


    @PutMapping("/updatePassword")
    @Transactional
    public String updateCashierPassword(@RequestBody EmployerPasswordResetDTO cashierPasswordResetDTO) {
        String message = cashierService.updateEmployerPassword(cashierPasswordResetDTO);
        return message;
    }

    @PutMapping("/updateRecentPin")
    @Transactional
    public String updateRecentPin(@RequestBody EmployerRecentPinUpdateDTO cashierRecentPinUpdateDTO) {
        String message = cashierService.updateRecentPin(cashierRecentPinUpdateDTO);
        return message;
    }

    @GetMapping(path = "/get-by-id", params = "id")
    @Transactional
    public EmployerDTO getCashierById(@RequestParam(value = "id") int employerId) {
        EmployerDTO employerDTO = cashierService.getEmployerById(employerId);
        return employerDTO;
    }

    @GetMapping("/view-image/{employerId}")
    @Transactional
    public ResponseEntity<byte[]> viewImage(@PathVariable int employerId) {
        byte[] imageData = cashierService.getImageData(employerId);

        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image format
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete-employerId/{id}")
    public String deleteCashier(@PathVariable(value = "id") int employerId) {
        String deleted = cashierService.deleteEmployer(employerId);
        return deleted;
    }

    @GetMapping(path = "/get-all-cashiers")
    public ResponseEntity<StandardResponse> getAllCashiers() {
        List<EmployerDTO> allEmployer = cashierService.getAllEmployer();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", allEmployer),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-cashiers-by-active-state/{status}")
    @Transactional
    public List<EmployerDTO> getAllCashiersByActiveState(@PathVariable(value = "status") boolean activeState) {
        List<EmployerDTO> allemployer = cashierService.getAllEmployerByActiveState(activeState);
        return allemployer;
    }

    @GetMapping(path = "/get-all-cashiers-bank-details")
    @Transactional
    public ResponseEntity<StandardResponse> getAllCashiersBankDetails() {
        List<EmployerUpdateBankAccountDTO> allCashiersBankDetails = cashierService.getAllEmployerBankDetails();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SUCCESS", allCashiersBankDetails),
                HttpStatus.OK
        );
    }

    @GetMapping("/byRole/{role}")
    public ResponseEntity<List<EmployerDTO>> getAllCashiersByRole(@PathVariable Role role) {
        List<EmployerDTO> cashiers = cashierService.getAllEmployerByRole(role);
        return new ResponseEntity<>(cashiers, HttpStatus.OK);
    }
}