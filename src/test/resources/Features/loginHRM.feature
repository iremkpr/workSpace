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
			
	@loginWthExamples
 Scenario Outline: User try to login HRM site with scenario Outline Examples
    Given I fill  valid userName "<uName>" 
    And I fill valid password "<password>"
    When I click the Login button
    Then I validate the user succesfully logged in "<accountName>"
		Examples:
			|uName |  password  | accountName |
			|Admin2|Neotech@123 |Admin Neotech|
			|Admin3|Neotech@123 |	admin3 notch|	
			
			
	@loginWthDataTable
	Scenario: User Login the system with Data Table
			Given Fill the UserName and Password valid data
					 |UserName|Password|
					 |Admin2|Neotech@123|
 			Then Validate that the user Account Name matched with expexted Value
						|accountName|
						|Admin Neotech|
			
			
	@excelDataLogin
	Scenario: Login to the system with using excel data		
					And Get the valid data from "Employee" excel sheet and fill the username, password text boxes
   				#When I click the Login button
					#Then Validate the userName matched with expected value