package com.lifepill.possystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import com.lifepill.possystem.exception.AuthenticationException;
import com.lifepill.possystem.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        RegisterRequestDTO registerRequest = new RegisterRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        when(authService.register(registerRequest)).thenReturn(authResponse);

        ResponseEntity<AuthenticationResponseDTO> responseEntity = authController.register(registerRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(authResponse, responseEntity.getBody());
    }

    @Test
    void testTest() {
        String response = authController.test();
        assertEquals("Authenticated", response);
    }

    //testRegister_Success
    @Test
    void testRegister_Success() {
        RegisterRequestDTO registerRequest = new RegisterRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        when(authService.register(registerRequest)).thenReturn(authResponse);

        ResponseEntity<AuthenticationResponseDTO> responseEntity = authController.register(registerRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(authResponse, responseEntity.getBody());
    }
}
