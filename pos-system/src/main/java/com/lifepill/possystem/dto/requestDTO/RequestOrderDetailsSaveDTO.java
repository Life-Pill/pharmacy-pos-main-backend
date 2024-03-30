package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {

    private String itemName;
    private Double qty;
    private Double totalAmount;
    private long items;
}
