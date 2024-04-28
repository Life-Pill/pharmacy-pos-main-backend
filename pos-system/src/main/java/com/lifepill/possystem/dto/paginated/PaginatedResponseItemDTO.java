package com.lifepill.possystem.dto.paginated;

import com.lifepill.possystem.dto.responseDTO.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Paginated response item dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDTO {
    /**
     * The List.
     */
    List<ItemGetResponseDTO> list;
    private long dataCount;
}
