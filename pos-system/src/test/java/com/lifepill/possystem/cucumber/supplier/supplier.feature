#Feature: Supplier Management
#
#  Scenario: Get all suppliers
#    Given there are suppliers in the system
#    When the user requests to get all suppliers
#    Then the system should return a list of suppliers
#
#  Scenario: Get a supplier by ID
#    Given there is a supplier with ID 1
#    When the user requests to get the supplier with ID 1
#    Then the system should return the supplier with ID 1
#
#  Scenario: Save a new supplier
#    Given there is no supplier with email "test@example.com"
#    When the user requests to save a new supplier with email "test@example.com"
#    Then the system should save the new supplier
#
#  Scenario: Update an existing supplier
#    Given there is a supplier with ID 1
#    When the user requests to update the supplier with ID 1
#    Then the system should update the supplier with ID 1
#
#  Scenario: Delete a supplier
#    Given there is a supplier with ID 1
#    When the user requests to delete the supplier with ID 1
#    Then the system should delete the supplier with ID 1
#
#  Scenario: Get all suppliers when no suppliers exist
#    Given there are no suppliers in the system
#    When the user requests to get all suppliers
#    Then the system should return an empty list of suppliers
#
#  Scenario: Save a new supplier with a duplicate email
#    Given there is a supplier with email "test@example.com"
#    When the user requests to save a new supplier with email "test@example.com"
#    Then the system should throw an EntityDuplicationException
#
#  Scenario: Update a non-existing supplier
#    Given there is no supplier with ID 1
#    When the user requests to update the supplier with ID 1
#    Then the system should throw a NotFoundException
#
#  Scenario: Delete a non-existing supplier
#    Given there is no supplier with ID 1
#    When the user requests to delete the supplier with ID 1
#    Then the system should throw a NotFoundException