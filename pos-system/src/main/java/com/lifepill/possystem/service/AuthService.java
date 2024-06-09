package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;

public interface AuthService {

    AuthenticationResponseDTO register(RegisterRequestDTO registerRequest);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
    EmployerAuthDetailsResponseDTO getEmployerDetails(String username);

    AuthenticationResponseDTO generateAuthenticationResponse(String employerEmail);

    AuthenticationResponseDTO authenticateWithCachedPin(String username, int pin);

    void setActiveStatus(String username, boolean b);
}