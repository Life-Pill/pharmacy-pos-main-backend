package com.lifepill.possystem.cucumber;
import com.lifepill.possystem.controller.AuthController;
import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.AuthenticationException;
import com.lifepill.possystem.service.AuthService;
import com.lifepill.possystem.util.StandardResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AuthControllerSteps {

    @Mock
    private AuthService authService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private AuthController authController;

    private RegisterRequestDTO registerRequest;
    private AuthenticationRequestDTO authenticationRequest;
    private ResponseEntity<?> responseEntity;

    @Given("a registration request")
    public void aRegistrationRequest() {
        registerRequest = new RegisterRequestDTO();
    }

    @When("the registration request is processed")
    public void theRegistrationRequestIsProcessed() {
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        when(authService.register(registerRequest)).thenReturn(authResponse);
        responseEntity = authController.register(registerRequest);
    }

    @Then("a successful authentication response is returned")
    public void aSuccessfulAuthenticationResponseIsReturned() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @When("I test the authentication endpoint")
    public void iTestTheAuthenticationEndpoint() {
        String response = authController.test();
        Assert.assertEquals("Authenticated", response);
    }

    @Given("an authentication request")
    public void anAuthenticationRequest() {
        authenticationRequest = new AuthenticationRequestDTO();
    }

    @Then("a successful authentication response is returned")
    public void aSuccessfulAuthenticationResponseIsReturned() {
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(authResponse, responseEntity.getBody());
    }

    @Given("an authentication request with incorrect credentials")
    public void anAuthenticationRequestWithIncorrectCredentials() {
        authenticationRequest = new AuthenticationRequestDTO();
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    @Given("an authentication request with employer email")
    public void anAuthenticationRequestWithEmployerEmail() {
        authenticationRequest = new AuthenticationRequestDTO();
        authenticationRequest.setEmployerEmail("employer@example.com");
    }

    @Then("the response should contain authentication response and employer details")
    public void theResponseShouldContainAuthenticationResponseAndEmployerDetails() {
        AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
        authResponse.setAccessToken("access_token");
        EmployerAuthDetailsResponseDTO employerDetailsResponse = new EmployerAuthDetailsResponseDTO();
        employerDetailsResponse.setEmployerFirstName("John Doe");
        employerDetailsResponse.setRole(Role.MANAGER);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
        Assert.assertEquals(authResponse, responseBody.get("authenticationResponse"));
        Assert.assertEquals(employerDetailsResponse, responseBody.get("employerDetails"));
    }

    @Given("an authentication request with invalid credentials")
    public void anAuthenticationRequestWithInvalidCredentials() {
        authenticationRequest = new AuthenticationRequestDTO();
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }

    @Then("an unauthorized status is returned with an appropriate error message")
    public void anUnauthorizedStatusIsReturnedWithAnAppropriateErrorMessage() {
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals("Authentication failed: Incorrect username or password",
                ((StandardResponse) responseEntity.getBody()).getMessage());
    }
}
