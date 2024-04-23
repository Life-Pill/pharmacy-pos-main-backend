package com.lifepill.possystem.controller;
import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/lifepill/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO registerRequest
    ) {
        AuthenticationResponseDTO authResponse = authService.register(registerRequest);
        return  ResponseEntity.ok(authResponse);
    }

/*    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }*/

    @GetMapping("/test")
    public String test(){
        return "Authenticated";
    }

   @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO request,
            HttpServletResponse response // Inject HttpServletResponse
    ) {
        AuthenticationResponseDTO authResponse = authService.authenticate(request);

        // Set the token as a cookie in the HTTP response
        Cookie cookie = new Cookie("Authorization", authResponse.getAccessToken());
        cookie.setHttpOnly(true); // Ensure the cookie is only accessible via HTTP
        cookie.setMaxAge(24 * 60 * 60); // Set the cookie's expiration time in seconds (24 hours in this case)
        cookie.setPath("/"); // Set the cookie's path to root ("/") to make it accessible from any path
        response.addCookie(cookie);
        System.out.println(cookie);

        return ResponseEntity.ok(authResponse);
    }

}