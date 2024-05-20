package com.lifepill.possystem.dto.responseDTO;

import com.lifepill.possystem.dto.ItemCategoryDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemGetResponseWithoutSupplierDetailsDTO {
        private ItemGetAllResponseDTO itemGetAllResponseDTO;
        private ItemCategoryDTO itemCategoryDTO;
}
