@addEmp
Feature: Add Employee Feature

  Background: 
    Given Login to the HRM site
    And Open the Add Employee page

  @validAddEmp
  Scenario: Add Employee with first and last name
    When Fill the valid firstName "2Rene" lastName  "3Ortega" and Location "Canadian Development Center"
    And Click the save button
    Then validate user "2Rene 3Ortega" added Succesfully

  @empDetails
  Scenario Outline: Add Employee and create login Credentials
    When Fill the valid "<firstName>" "<lastName>"   and "<Location>"
    And Click the Create Login Details button
    And Fill username "<username>" password "<password>"
    And Click the save button
    Then validate the user "<AcctName>" added succesfully

    Examples: 
      | firstName | lastName | Location                    | username  | password     | AcctName   |
      | Irma      | Sugha    | Canadian Development Center | irmaSugha | irmikella01* | Irma Sugha |

	@addEmpWithoutId
  Scenario Outline: Add Employee without employee id
    When Fill the valid "<firstName>" "<lastName>"   and "<Location>"
    And Delete existing id
    And Click the save button
    Then validate the user "<AcctName>" added succesfully

    Examples: 
      | firstName | lastName | Location                    |   AcctName   |
      | AIrma     | Sugha    | Canadian Development Center | AIrma Sugha |
