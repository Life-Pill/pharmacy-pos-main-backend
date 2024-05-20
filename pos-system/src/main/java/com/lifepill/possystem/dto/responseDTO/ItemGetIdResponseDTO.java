package com.lifepill.possystem.dto.responseDTO;

import com.lifepill.possystem.dto.ItemCategoryDTO;
import com.lifepill.possystem.dto.SupplierCompanyDTO;
import com.lifepill.possystem.dto.SupplierDTO;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemGetIdResponseDTO {
       private ItemGetAllResponseDTO itemGetAllResponseDTO;
        private ItemCategoryDTO itemCategoryDTO;
        private SupplierDTO supplierDTO;
        private SupplierCompanyDTO supplierCompanyDTO;
}
