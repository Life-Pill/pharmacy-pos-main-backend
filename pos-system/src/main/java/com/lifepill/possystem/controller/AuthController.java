package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import com.lifepill.possystem.exception.AuthenticationException;
import com.lifepill.possystem.service.AuthService;
import com.lifepill.possystem.service.RedisService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for handling authentication-related requests.
 */
@RestController
@RequestMapping("/lifepill/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;
    private final RedisService redisService;

    /**
     * Handles user registration.
     *
     * @param registerRequest The request body containing registration details
     * @return ResponseEntity containing the authentication response
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO registerRequest
    ) {
        AuthenticationResponseDTO authResponse = authService.register(registerRequest);
        return ResponseEntity.ok(authResponse);
    }

    /**
     * Test endpoint.
     *
     * @return A simple message indicating authentication success.
     */
    @GetMapping("/test")
    public String test() {
        return "Authenticated";
    }

    /**
     * Authenticates user and returns an access token.
     *
     *
     *
     * @param request  The request body containing authentication details
     * @param response HttpServletResponse to set the access token as a cookie
     * @return ResponseEntity containing either the authentication response and employer details or an error message
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequestDTO request,
            HttpServletResponse response // Inject HttpServletResponse
    ) {
        try {
            AuthenticationResponseDTO authResponse = authService.authenticate(request);
            EmployerAuthDetailsResponseDTO employerDetails = authService
                    .getEmployerDetails(request.getEmployerEmail());

            // Cache the authentication data in Redis
            redisService.cacheEmployerDetails(employerDetails);

            // Set the token as a cookie in the HTTP response
            Cookie cookie = new Cookie("Authorization", authResponse.getAccessToken());
            cookie.setHttpOnly(true); // Ensure the cookie is only accessible via HTTP
            cookie.setMaxAge(24 * 60 * 60); // Set the cookie's expiration time in seconds (24 hours)
            cookie.setPath("/"); // Set the cookie's path to root ("/") to make it accessible from any path
            response.addCookie(cookie);

            authResponse.setMessage("Successfully logged in.");

            // Create a map or a custom response object to return both authResponse and employerDetails
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("authenticationResponse", authResponse);
            responseData.put("employerDetails", employerDetails);

            return ResponseEntity.ok(responseData);
        } catch (AuthenticationException e) {
            // Authentication failed due to incorrect username or password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new StandardResponse(
                                    401,
                                    "Authentication failed: Incorrect username or password",
                                    null
                            )
                    );
        }
    }

    /**
     * Handles preflight requests for CORS.
     */
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptionsRequest() {
        return ResponseEntity.ok().build();
    }

}