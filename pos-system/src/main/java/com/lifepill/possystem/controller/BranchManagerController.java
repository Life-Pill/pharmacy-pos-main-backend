package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.UpdateManagerDTO;
import com.lifepill.possystem.dto.requestDTO.ChangeManagerDTO;
import com.lifepill.possystem.dto.responseDTO.ChangeManagerResponseDTO;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.service.EmployerService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing branch-related operations by branch managers.
 */
@RestController
@RequestMapping("lifepill/v1/branch-manager")
@AllArgsConstructor
public class BranchManagerController {

    private EmployerService employerService;

    /**
     * Retrieves all cashiers (employers) associated with a specific branch managed by the branch manager.
     *
     * @param branchId The ID of the branch for which cashiers are to be retrieved.
     * @return A ResponseEntity containing a list of EmployerDTO objects representing the cashiers.
     */
    @GetMapping("/employer/by-branch-manager/{branchId}")
    public ResponseEntity<List<EmployerDTO>> getAllCashiersByBranchId(@PathVariable int branchId) {
        List<EmployerDTO> employerDTOS = employerService.getAllEmployerByBranchId(branchId);
        return new ResponseEntity<>(employerDTOS, HttpStatus.OK);
    }
    /**
     * Retrieves all employers associated with a specific branch and role.
     *
     * @param branchId The ID of the branch for which employers are to be retrieved.
     * @param role The role of the employers to be retrieved.
     * @return A ResponseEntity containing a list of EmployerDTO objects representing the employers.
     */
    @GetMapping("/employer/by-branch/role/{branchId}")
    public ResponseEntity<StandardResponse> getEmployersByBranchIdAndRole(
            @PathVariable long branchId,
            @RequestParam Role role
    ) {
        List<EmployerDTO> employerDTOS = employerService.getEmployersByBranchIdAndRole(branchId, role);
        return new ResponseEntity<>(
                new StandardResponse(200, "Get Role by branch Id", employerDTOS),
                HttpStatus.OK);
    }

    /**
     * Retrieves all managers associated with a specific branch.
     *
     * @param branchId The ID of the branch for which managers are to be retrieved.
     * @return A ResponseEntity containing a list of EmployerDTO objects representing the managers.
     */
    @GetMapping("/managers/by-branch/{branchId}")
    public ResponseEntity<StandardResponse> getAllManagersByBranchId(@PathVariable long branchId) {
        try {
            List<EmployerDTO> managers = employerService.getAllManagersByBranchId(branchId);
            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Branch Manager retrieve successfully",
                            managers
                    ),
                    HttpStatus.OK
            );
        } catch (NotFoundException e) {
            return new ResponseEntity<>(
                    new StandardResponse(404, "No any managers found for that Id", null),
                    HttpStatus.OK);
        }
    }

    /**
     * Updates or creates a branch manager for a specific branch.
     *
     * @param branchId The ID of the branch for which the manager is to be updated or created.
     * @param updateManagerDTO The details of the employer to be updated or created as manager.
     * @return A ResponseEntity containing the updated or created EmployerDTO.
     */
    @PutMapping("/managers/by-branch/{branchId}")
    public ResponseEntity<StandardResponse> updateOrCreateBranchManager(
            @PathVariable long branchId, @RequestBody UpdateManagerDTO updateManagerDTO) {
        EmployerDTO updatedOrCreatedManager = employerService.updateOrCreateBranchManager(branchId, updateManagerDTO);
        updatedOrCreatedManager.setBranchId(branchId);

        return new ResponseEntity<>(
                new StandardResponse(200, "Update Branch Manager", updatedOrCreatedManager),
                HttpStatus.OK);
    }

    @PostMapping("/change-manager")
    public ResponseEntity<StandardResponse> changeBranchManager(@RequestBody ChangeManagerDTO changeManagerDTO) {
         ChangeManagerResponseDTO changeManagerResponseDTO = employerService.changeBranchManager(changeManagerDTO);

         return new ResponseEntity<>(
                 new StandardResponse(200, "Branch Manager changed successfully", changeManagerResponseDTO),
                 HttpStatus.OK);
    }

}
