package com.lifepill.possystem.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CachedEmployerDetailsResponseDTO {
    private AuthenticationResponseDTO authenticationResponse;
    private EmployerAuthDetailsResponseDTO employerDetails;
}
