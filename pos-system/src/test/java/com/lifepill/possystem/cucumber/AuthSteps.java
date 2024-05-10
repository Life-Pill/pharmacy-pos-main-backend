package com.lifepill.possystem.cucumber;

import com.lifepill.possystem.controller.AuthController;
import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.service.AuthService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

public class AuthSteps {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private RegisterRequestDTO registerRequest;
    private AuthenticationRequestDTO authenticationRequest;
    private ResponseEntity<AuthenticationResponseDTO> registrationResponse;
    private ResponseEntity<?> authenticationResponse;
    private AuthenticationResponseDTO authResponse;

    @Given("a registration request with valid credentials")
    public void aRegistrationRequestWithValidCredentials() {
        // Create a register request with valid data
        registerRequest = new RegisterRequestDTO();
        // Set your valid credentials here
    }

    @When("the user sends a registration request")
    public void theUserSendsARegistrationRequest() {
        // Mock the service method to return a mock response
        authResponse = new AuthenticationResponseDTO();
        when(authService.register(registerRequest)).thenReturn(authResponse);

        // Call the controller method and store the response
        registrationResponse = authController.register(registerRequest);
    }

    @Then("the response status code should be (\\d+)")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        // Assert the response status code
        Assertions.assertEquals(HttpStatus.OK, registrationResponse.getStatusCode());

    }


    @And("the response should contain authentication details")
    public void theResponseShouldContainAuthenticationDetails() {
        // Assert the response body contains authentication details
        Assertions.assertEquals(authResponse, registrationResponse.getBody());
    }

    @Given("an authentication request with valid credentials")
    public void anAuthenticationRequestWithValidCredentials() {
        // Create an authentication request with valid data
        authenticationRequest = new AuthenticationRequestDTO();
        // Set your valid credentials here
    }

    @When("the user sends an authentication request")
    public void theUserSendsAnAuthenticationRequest() {
        // Mock the service method to return a mock response
        authResponse = new AuthenticationResponseDTO();
        when(authService.authenticate(authenticationRequest)).thenReturn(authResponse);

        // Call the controller method and store the response
        authenticationResponse = authController.authenticate(authenticationRequest, null);
    }

    @Given("an authentication request with incorrect credentials")
    public void anAuthenticationRequestWithIncorrectCredentials() {
        // Create an authentication request with incorrect data
        authenticationRequest = new AuthenticationRequestDTO();
        // Set your incorrect credentials here
    }

    @Then("the response should contain an error message")
    public void theResponseShouldContainAnErrorMessage() {
        // Assert the response body contains an error message
        Assertions.assertTrue(authenticationResponse.getBody().toString().contains("Authentication failed"));
    }
}