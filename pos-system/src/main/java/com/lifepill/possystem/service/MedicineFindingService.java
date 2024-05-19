package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;
import org.springframework.stereotype.Service;


import java.util.List;

public interface MedicineFindingService {
    List<MedicineGetResponseDTO> getItemByName(String itemName);
}
