package com.lifepill.possystem.dto.requestDTO;

import com.lifepill.possystem.entity.enums.Role;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChangeManagerDTO {
    private long formerManagerId;
    private long branchId;
    private long newManagerId;
    private Role currentManagerNewRole;
    private Role newManagerRole;
}
