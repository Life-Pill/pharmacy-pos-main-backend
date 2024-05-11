Feature: Supplier Management

  Scenario: Retrieve all suppliers
    Given the API is running
    When I request all suppliers
    Then the response status code should be 200
    And the response should contain a list of suppliers

  Scenario: Save a new supplier
    Given the API is running
    And I have a new supplier to save
    When I save the supplier
    Then the response status code should be 201
    And the response should contain the saved supplier

  Scenario: Update an existing supplier
    Given the API is running
    And there exists a supplier with ID 1
    And I have updated details for the supplier
    When I update the supplier with ID 1
    Then the response status code should be 200
    And the response should contain the updated supplier

  Scenario: Retrieve a supplier by ID
    Given the API is running
    And there exists a supplier with ID 1
    When I request the supplier with ID 1
    Then the response status code should be 200
    And the response should contain the supplier details

  Scenario: Delete a supplier by ID
    Given the API is running
    And there exists a supplier with ID 1
    When I delete the supplier with ID 1
    Then the response status code should be 204
    And the supplier with ID 1 should be deleted
