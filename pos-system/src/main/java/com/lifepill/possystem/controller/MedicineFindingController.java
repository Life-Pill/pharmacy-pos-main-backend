package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;
import com.lifepill.possystem.service.ItemService;
import com.lifepill.possystem.service.MedicineFindingService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Medicine finding controller.
 */
@RestController
@RequestMapping("/lifepill/v1/medicine-finding")
@AllArgsConstructor
public class MedicineFindingController {

    private MedicineFindingService medicineFindingService;

    /**
     * Find medicine by name response entity.
     *
     * @param itemName the item name
     * @return the response entity
     */
    @GetMapping(path = "/find-medicine",params = "itemName")
    public ResponseEntity<StandardResponse> findMedicineByName(
            @RequestParam(value = "itemName") String itemName
    ){
      List<MedicineGetResponseDTO> medicineGetResponseDTOS = medicineFindingService.getItemByName(itemName);
        return new ResponseEntity<>(
                new StandardResponse(200, "Successfully fetched", medicineGetResponseDTOS),
                HttpStatus.OK
        );
    }
}
