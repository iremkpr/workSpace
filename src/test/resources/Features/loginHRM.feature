@Login
Feature: Login to HRM feature
  

  @validLogin
  Scenario: User login to HRM site with valid data
    Given I fill the  valid userName "Admin" 
    And I fill the valid password "Neotech@123"
    When I click the Login button
    Then I validate the user succesfully logged in
 

 @invalidData
 Scenario Outline: User try to login HRM site with invalid data
    Given I fill the  valid userName "<uName>" 
    And I fill the valid password "<password>"
    When I click the Login button
		Then I validate the warning
		Examples:
			|uName|password|
			|ands|asdfsd|
			|asdfdg|aesdfdgg|