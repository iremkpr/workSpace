
@employeeSearch
Feature: User search feature
  	Background:     
  	Given Login to the HRM site
  	 And Open the Employee List page
  	
  # this scenario has exception bcs cant do 5000 differenciation	
  @employeeSearch500problemly
  Scenario Outline: 
  			When Search Employee "<Name>" by name and click 
  			Then Validate that user detail page opened "<Name>"
  			
  			Examples: 
  								|Name|
									|2Rene 3Ortega|
									|Irma Sugha  |
									|Amir Khan |