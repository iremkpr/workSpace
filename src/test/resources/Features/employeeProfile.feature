@empProfile
Feature: Employee Profile information update
  User should be able to update profile informations

  Background: 
    Given Login to the HRM site
  	 And Open the Employee List page

  @EmpBirthDate
  Scenario Outline: User should be able to update informations with valid data
  		When Search Employee "<Name>" by name and click 
      And Update existing birth date with "<BirthDate>" 
      And Update Marital status as "<MaritalStatu>"
      And Update Gender status as "<Gender>"
      And Click the smocker checkbox
      And Update Race information as "<Race>"
      Then Save the updated personal details 
      When Select "<Activity>" from checkbox
      And Attach a file
      
 			Examples: 
 						|Name|BirthDate|MaritalStatu|Gender|Race|Activity|
 						|Brody Alan|04/01/1997|Married|Female|White|Dancing|
   