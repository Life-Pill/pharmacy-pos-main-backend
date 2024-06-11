package com.lifepill.possystem.dto.responseDTO;

import com.lifepill.possystem.dto.UpdateManagerDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChangeManagerResponseDTO {
    private long branchId;
    private UpdateManagerDTO newManager;
    private UpdateManagerDTO formerManager;
}
