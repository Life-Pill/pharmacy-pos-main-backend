package com.lifepill.possystem.dto.requestDTO.CashierUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierRecentPinUpdateDTO {
    private long cashierId;
    private int pin;
}
