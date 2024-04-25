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

    private String name;
    private Double amount;
    private Double totalAmount;
    private long id;

}
