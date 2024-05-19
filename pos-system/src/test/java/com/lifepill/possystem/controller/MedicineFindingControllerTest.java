package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;
import com.lifepill.possystem.service.MedicineFindingService;
import com.lifepill.possystem.util.StandardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MedicineFindingControllerTest {

    @InjectMocks
    private MedicineFindingController medicineFindingController;

    @Mock
    private MedicineFindingService medicineFindingService;

    private List<MedicineGetResponseDTO> medicineGetResponseDTOS;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        medicineGetResponseDTOS = new ArrayList<>();
        medicineGetResponseDTOS.add(new MedicineGetResponseDTO());
    }

    @Test
    void testFindMedicineByName() {
        String itemName = "Paracetamol";
        when(medicineFindingService.getItemByName(itemName)).thenReturn(medicineGetResponseDTOS);

        ResponseEntity<StandardResponse> response = medicineFindingController.findMedicineByName(itemName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getBody().getCode());
        assertEquals("Successfully fetched", response.getBody().getMessage());
        assertEquals(medicineGetResponseDTOS, response.getBody().getData());
    }
}