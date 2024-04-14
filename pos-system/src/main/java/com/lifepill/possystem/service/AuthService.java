package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;

public interface AuthService {

    public AuthenticationResponseDTO register(RegisterRequestDTO registerRequest);

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
}
