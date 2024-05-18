## features/medicine_finding.feature
#
#Feature: Medicine Finding
#
#  Scenario: Find medicine by name successfully
#    Given a list of existing medicines
#    When the client requests to find medicines by name "Paracetamol"
#    Then the response should contain a list of medicines with the name "Paracetamol"
#    And the response should have a success status code
#
#  Scenario: Find medicine by name when no medicine is found
#    Given a list of existing medicines
#    When the client requests to find medicines by name "NonExistingMedicine"
#    Then the response should contain an error message "No item found with the name NonExistingMedicine"
#    And the response should have an error status code