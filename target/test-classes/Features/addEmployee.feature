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
      | Amir      | Khan     | Canadian Development Center | AmirKhan  | irmikella01* | Amir Khan  |

  @addEmpWithoutId
  Scenario Outline: Add Employee without employee id
    When Fill the valid "<firstName>" "<lastName>"   and "<Location>"
    And Delete existing id
    And Click the save button
    Then validate the user "<AcctName>" added succesfully

    Examples: 
      | firstName | lastName | Location                    | AcctName    |
      | AIrma     | Sugha    | Canadian Development Center | AIrma Sugha |

  @dataTable
  Scenario: Add Employee with first and last name using DataTable
    When Fill valid firstName lastName and Location
      | firstName | lastName | Location                    |
      | Rene      | Ortega   | Canadian Development Center |
    And Click the save button
    Then validate the user added Succesfully
      | userName    |
      | Rene Ortega |

  @AddEmpEXCEL
  Scenario: Add new Employee from Excel file
    When I add the valid datas from "Student" excel sheet

  @addEmpDetailEXCEL
  Scenario: Add Employee and Create login Credentials from Excel file
    When I add the valid datas via  "Employee" excel sheet

  @addEmployeePhoto
  Scenario: Add new Employee and update the user profile picture
    When Fill the valid firstName "First" lastName  "Things First" and Location "Canadian Development Center"
    And Click the save button
    When Click user profile picture icon and update the picture
    Then Validate that the user profile photo updated
