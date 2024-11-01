@DB
Feature: Database Testing

  Scenario: Getting top three customers with highest credit limit
    Given I am connected to the database
    When I get the top three customers by credit limit
    Then I print their names and credit limits
    And I close the database connection
    
