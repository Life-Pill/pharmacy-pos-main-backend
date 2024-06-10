package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.LogoutRequestDTO;
import com.lifepill.possystem.dto.VerifyPinRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.CachedEmployerDetailsResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import com.lifepill.possystem.service.AuthService;
import com.lifepill.possystem.service.RedisService;
import com.lifepill.possystem.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lifepill/v1/session")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"}, allowCredentials = "true")
public class TempController {

    private final AuthService authService;
    private final RedisService redisService;
    /**
     * Handles temporary logout by caching the user's authentication details.
     *
     * @param logoutRequest The request body containing the username
     * @return ResponseEntity containing a success or error message
     */
    @PostMapping("/logout/temporary")
    public ResponseEntity<?> temporaryLogout(@RequestBody LogoutRequestDTO logoutRequest) {
        // Retrieve the employer details based on the username
        EmployerAuthDetailsResponseDTO employerDetails = authService.getEmployerDetails(logoutRequest.getUsername());

        // Cache the employer details and pin in Redis
        redisService.cacheEmployerDetails(employerDetails);

        //set user active status to false
        authService.setActiveStatus(logoutRequest.getUsername(), false);

        return ResponseEntity.ok(new StandardResponse(200, "Temporary logout successful", null));
    }

    /**
     * Handles permanent logout by removing the user's cached authentication details.
     *
     * @param logoutRequest The request body containing the username
     * @return ResponseEntity containing a success or error message
     */
    @PostMapping("/logout/permanent")
    public ResponseEntity<?> permanentLogout(@RequestBody LogoutRequestDTO logoutRequest) {
        // Remove the cached employer details from Redis
        redisService.removeEmployerDetails(logoutRequest.getUsername());

        //set user active status to false
        authService.setActiveStatus(logoutRequest.getUsername(), false);

        return ResponseEntity.ok(
                new StandardResponse(
                        200,
                        "Permanent logout successful",
                        null
                )
        );
    }

    /**
     * Handles user authentication using the cached pin and username.
     *
     * @param verifyPinRequest The request body containing the username and pin
     * @param response         HttpServletResponse to set the access token as a cookie
     * @return ResponseEntity containing either the authentication response and employer details or an error message
     */
    @PostMapping("/authenticate/cached")
    public ResponseEntity<?> authenticateWithCachedPin(@RequestBody VerifyPinRequestDTO verifyPinRequest, HttpServletResponse response) {
        // Retrieve the cached employer details from Redis
        EmployerAuthDetailsResponseDTO employerDetails = redisService.getEmployerDetails(verifyPinRequest.getUsername());

        if (employerDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new StandardResponse(401, "Invalid username or pin", null));
        }

        // Verify the pin
        if (employerDetails.getPin() != verifyPinRequest.getPin()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new StandardResponse(401, "Invalid pin", null));
        }

        AuthenticationResponseDTO authResponse = authService.authenticateWithCachedPin(verifyPinRequest.getUsername(), verifyPinRequest.getPin());

        // Set the token as a cookie in the HTTP response
        Cookie cookie = new Cookie("Authorization", authResponse.getAccessToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        authResponse.setMessage("Successfully logged in using cached pin.");

        // Create a map or a custom response object to return both authResponse and employerDetails
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("authenticationResponse", authResponse);
        responseData.put("employerDetails", employerDetails);

        return ResponseEntity.ok(responseData);
    }

    /**
     * Retrieves all cached employer details from Redis, including authentication response and employer details.
     *
     * @return A collection of CachedEmployerDetailsResponse representing the cached employer details and authentication responses.
     */
    @GetMapping("/get-all-cached-employers")
    public Collection<CachedEmployerDetailsResponseDTO> getAllCachedEmployers() {
        Collection<EmployerAuthDetailsResponseDTO> cachedEmployers = redisService.getAllCachedEmployerDetails();
        Collection<CachedEmployerDetailsResponseDTO> cachedEmployerDetailsResponses = new ArrayList<>();

        for (EmployerAuthDetailsResponseDTO employerDetails : cachedEmployers) {
            String username = employerDetails.getEmployerEmail();
            AuthenticationResponseDTO authResponse = authService.generateAuthenticationResponse(username);
            CachedEmployerDetailsResponseDTO response = new CachedEmployerDetailsResponseDTO(authResponse, employerDetails);
            cachedEmployerDetailsResponses.add(response);
        }

        return cachedEmployerDetailsResponses;
    }

    /**
     * Retrieves cached employer details by employer email, including authentication response and employer details.
     *
     * @param employerEmail The email of the employer.
     * @return The CachedEmployerDetailsResponse object representing the cached employer details and authentication response.
     */
    @GetMapping("/get-cached-employer/email/{employerEmail}")
    public ResponseEntity<CachedEmployerDetailsResponseDTO> getCachedEmployerByEmail(@PathVariable String employerEmail) {
        EmployerAuthDetailsResponseDTO employerDetails = redisService.getEmployerDetails(employerEmail);
        if (employerDetails != null) {
            AuthenticationResponseDTO authResponse = authService.generateAuthenticationResponse(employerEmail);
            CachedEmployerDetailsResponseDTO cachedEmployerDetails = new CachedEmployerDetailsResponseDTO(authResponse, employerDetails);
            return ResponseEntity.ok(cachedEmployerDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
