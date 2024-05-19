package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.Item;
import com.lifepill.possystem.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The type Request order details save dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {
    private String name; // TODO: Change name to itemName
    private Double amount; // TODO: Change amount to itemAmount
    private long id; // TODO: Change id to itemId

}
