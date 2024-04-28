Feature: Authentication Feature

  Scenario: Successful registration
    Given a register request with valid data
    When the register endpoint is called
    Then the response status code should be 200
    And the authentication response should be returned

  Scenario: Testing the authentication endpoint
    When the test endpoint is called
    Then the response should be "Authenticated"

  Scenario: Successful authentication
    Given an authentication request with valid credentials
    When the authenticate endpoint is called
    Then the response status code should be 200
    And the authentication response should be returned
    And the access token should be set as a cookie

  Scenario: Authentication failure due to invalid credentials
    Given an authentication request with invalid credentials
    When the authenticate endpoint is called
    Then the response status code should be 401
    And the error message "Authentication failed: Incorrect username or password" should be returned

  Scenario: Setting access token as a cookie on authentication
    Given an authentication request with valid credentials
    When the authenticate endpoint is called
    Then the access token should be set as a cookie in the response

  Scenario: Retrieving employer details on authentication
    Given an authentication request with a valid employer email
    When the authenticate endpoint is called
    Then the response status code should be 200
    And the authentication response should be returned
    And the employer details should be included in the response body

  Scenario: Authentication exception handling
    Given an authentication request
    When the authenticate endpoint is called and an authentication exception occurs
    Then the response status code should be 401
    And the error message "Authentication failed: Incorrect username or password" should be returned
