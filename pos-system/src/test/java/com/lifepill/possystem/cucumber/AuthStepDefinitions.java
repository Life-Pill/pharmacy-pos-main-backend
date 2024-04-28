package com.lifepill.possystem.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.lifepill.possystem.controller.AuthController;
import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.AuthenticationException;
import com.lifepill.possystem.service.AuthService;
import com.lifepill.possystem.util.StandardResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthStepDefinitions {

    @Mock
    private AuthService authService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private AuthController authController;

    private ResponseEntity<?> responseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("a register request with valid data")
    public void aRegisterRequestWithValidData() {
        // Set up mock behavior for successful registration
        RegisterRequestDTO registerRequest = new RegisterRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        when(authService.register(registerRequest)).thenReturn(authResponse);
    }

    @When("the register endpoint is called")
    public void theRegisterEndpointIsCalled() {
        // Call the register endpoint
        responseEntity = authController.register(new RegisterRequestDTO());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Verify the response status code
        Assertions.assertEquals(HttpStatus.valueOf(expectedStatusCode), responseEntity.getStatusCode());
    }

    @Then("the authentication response should be returned")
    public void theAuthenticationResponseShouldBeReturned() {
        // Verify that the authentication response is returned
        Assertions.assertNotNull(responseEntity.getBody());
    }

    @When("the test endpoint is called")
    public void theTestEndpointIsCalled() {
        // Call the test endpoint
        String response = authController.test();
        Assertions.assertEquals("Authenticated", response);
    }

    @Given("an authentication request with valid data")
    public void anAuthenticationRequestWithValidData() {
        // Set up mock behavior for successful authentication
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        EmployerAuthDetailsResponseDTO employerDetails = new EmployerAuthDetailsResponseDTO();
        when(authService.authenticate(request)).thenReturn(authResponse);
        when(authService.getEmployerDetails(request.getEmployerEmail())).thenReturn(employerDetails);
    }

    @When("the authenticate endpoint is called")
    public void theAuthenticateEndpointIsCalled() {
        // Call the authenticate endpoint
        responseEntity = authController.authenticate(new AuthenticationRequestDTO(), response);
    }

    @Then("the authentication response and employer details should be returned")
    public void theAuthenticationResponseAndEmployerDetailsShouldBeReturned() {
        // Verify that the authentication response and employer details are returned
        Assertions.assertNotNull(responseEntity.getBody());
    }

    @Then("the access token should be set as a cookie")
    public void theAccessTokenShouldBeSetAsACookie() {
        // Verify that the access token is set as a cookie
        verify(response, times(1)).addCookie(any(Cookie.class));
    }

    @Given("an authentication request with invalid data")
    public void anAuthenticationRequestWithInvalidData() {
        // Set up mock behavior for failed authentication
        AuthenticationRequestDTO request = new AuthenticationRequestDTO();
        when(authService.authenticate(request)).thenThrow(new AuthenticationException("Invalid credentials"));
    }

    @Then("the response status code should be UNAUTHORIZED")
    public void theResponseStatusCodeShouldBeUNAUTHORIZED() {
        // Verify that the response status code is UNAUTHORIZED
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Then("an error message should be returned")
    public void anErrorMessageShouldBeReturned() {
        // Verify that an error message is returned
        Assertions.assertNotNull(responseEntity.getBody());
    }



}
