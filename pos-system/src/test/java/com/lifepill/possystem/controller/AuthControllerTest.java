package com.lifepill.possystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.AuthenticationException;
import com.lifepill.possystem.service.AuthService;
import com.lifepill.possystem.util.StandardResponse;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    //testAuthenticate_Success
    @Test
    void testAuthenticate_Success() {
        // Prepare test data
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setAccessToken("dummyAccessToken"); // Set dummy access token for testing

        // Mock the service method to return the authentication response
        when(authService.authenticate(request)).thenReturn(authResponse);

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.authenticate(request, response);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody()); // Ensure response body is not null

        // Verify that the access token is set as a cookie in the response
        verify(response, times(1)).addCookie(any());

        // Assert that the authentication response is correctly set in the response body
        assertEquals(authResponse, ((Map<String, Object>) responseEntity.getBody()).get("authenticationResponse"));
    }


    // testAuthenticate_Failure
    @Test
    void testAuthenticate_Failure() {
        // Prepare test data
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setAccessToken(null); // Set null access token to simulate authentication failure

        // Mock the service method to throw an AuthenticationException
        when(authService.authenticate(request)).thenThrow(new AuthenticationException("Incorrect username or password"));

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.authenticate(request, response);

        // Assertions
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode()); // Check for correct HTTP status code
        assertNotNull(responseEntity.getBody()); // Ensure response body is not null

        // Verify that no cookie is added to the response
        verify(response, never()).addCookie(any());

        // Assert the error message in the response body
        assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    // testCookieSetOnAuthenticate
    @Test
    void testCookieSetOnAuthenticate() {
        // Prepare test data
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setAccessToken("dummyAccessToken"); // Set dummy access token for testing

        // Mock the service method to return the authentication response
        when(authService.authenticate(request)).thenReturn(authResponse);

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.authenticate(request, response);

        // Verify that the access token is set as a cookie in the response
        verify(response, times(1)).addCookie(any());
    }

   // testEmployerDetailsRetrievalOnAuthenticate
   @Test
   void testEmployerDetailsRetrievalOnAuthenticate() {
       // Prepare test data
       AuthenticationRequestDTO request = new AuthenticationRequestDTO();
       // Set the employer email for which details will be retrieved
       request.setEmployerEmail("employer@example.com");
       AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
       authResponse.setAccessToken("access_token"); // Set a valid access token

       // Mock the service method to return authentication response
       when(authService.authenticate(request)).thenReturn(authResponse);

       // Mock the service method to return employer details
       EmployerAuthDetailsResponseDTO employerDetailsResponse = new EmployerAuthDetailsResponseDTO();
       // Set employer details data for testing
       employerDetailsResponse.setEmployerFirstName("John Doe");
       employerDetailsResponse.setRole(Role.MANAGER);
       when(authService.getEmployerDetails(request.getEmployerEmail())).thenReturn(employerDetailsResponse);

       // Call the controller method
       ResponseEntity<?> responseEntity = authController.authenticate(request, response);

       // Assertions
       assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); // Check for correct HTTP status code
       assertNotNull(responseEntity.getBody()); // Ensure response body is not null

       // Verify that a cookie is added to the response
       verify(response, times(1)).addCookie(any());

       // Assert the authentication response in the response body
       assertEquals(authResponse, ((Map<String, Object>) responseEntity.getBody()).get("authenticationResponse"));

       // Assert the employer details in the response body
       assertEquals(employerDetailsResponse, ((Map<String, Object>) responseEntity.getBody()).get("employerDetails"));
   }


    // testInvalidCredentialsOnAuthenticate
    @Test
    void testInvalidCredentialsOnAuthenticate() {
        // Prepare test data
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setAccessToken(null); // Set null access token to simulate authentication failure

        // Mock the service method to throw an AuthenticationException
        when(authService.authenticate(request)).thenThrow(new AuthenticationException("Incorrect username or password"));

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.authenticate(request, response);

        // Assertions
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode()); // Check for correct HTTP status code
        assertNotNull(responseEntity.getBody()); // Ensure response body is not null

        // Verify that no cookie is added to the response
        verify(response, never()).addCookie(any());

        // Assert the error message in the response body
        assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

   // testErrorResponseOnAuthenticate
    @Test
    void testErrorResponseOnAuthenticate() {
        // Prepare test data
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setAccessToken(null); // Set null access token to simulate authentication failure

        // Mock the service method to throw an AuthenticationException
        when(authService.authenticate(request)).thenThrow(new AuthenticationException("Incorrect username or password"));

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.authenticate(request, response);

        // Assertions
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode()); // Check for correct HTTP status code
        assertNotNull(responseEntity.getBody()); // Ensure response body is not null

        // Verify that no cookie is added to the response
        verify(response, never()).addCookie(any());

        // Assert the error message in the response body
        assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

   // testAuthenticationExceptionHandling
    @Test
    void testAuthenticationExceptionHandling() {
        // Prepare test data
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();

        // Mock the service method to throw an AuthenticationException
        when(authService.authenticate(request)).thenThrow(new AuthenticationException("Incorrect username or password"));

        // Call the controller method
        ResponseEntity<?> responseEntity = authController.authenticate(request, response);

        // Assertions
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode()); // Check for correct HTTP status code
        assertNotNull(responseEntity.getBody()); // Ensure response body is not null

        // Verify that no cookie is added to the response
        verify(response, never()).addCookie(any());

        // Assert the error message in the response body
        assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

   // testTestEndpoint
    @Test
    void testTestEndpoint() {
        // Call the controller method
        String response = authController.test();

        // Assert the response message
        assertEquals("Authenticated", response);
    }

   // testRegisterEndpoint
    @Test
    void testRegisterEndpoint() {
        // Prepare test data
        RegisterRequestDTO registerRequest = new RegisterRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();

        // Mock the service method to return the authentication response
        when(authService.register(registerRequest)).thenReturn(authResponse);

        // Call the controller method
        ResponseEntity<AuthenticationResponseDTO> responseEntity = authController.register(registerRequest);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); // Check for correct HTTP status code
        assertEquals(authResponse, responseEntity.getBody()); // Ensure the response body is correct
    }

}
