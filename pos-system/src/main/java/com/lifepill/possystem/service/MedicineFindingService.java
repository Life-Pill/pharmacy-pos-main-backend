package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.responseDTO.MedicineGetResponseDTO;


import java.util.List;

public interface MedicineFindingService {
    List<MedicineGetResponseDTO> getItemByName(String itemName);
}
