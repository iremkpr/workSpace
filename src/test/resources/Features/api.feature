@api
Feature: Api One Book
  This feature will fetch right endpoint

  @Book
  Scenario: One Book
     Given Add "9781449325862" query parameter for ISBN
    When I send the request
    Then I validate the status code 200
    And I validate the ISBN "9781449325862"

   @Books
  Scenario: All Books
    When Send request with All books end point
     And validate books are returned
 
 	@postUser
 	Scenario: Create a new user with valid data
 				Given Send body  with valid data
 				Then Validate response status code is 201
 				
 				
 	@token
 	Scenario: Generate token for existing user
 				Given Send the body to "/Account/v1/GenerateToken" endpoint		
 				Then Validate response status code is 200
 				And Validate that the token is exist on response body
	
	@getUser
	Scenario: Retrieve the User with path parameter
				Given Add the path parameter "UserId" on response and send it "/Account/v1/User/{UserId}" endpoint
				Then Validate response status code is 200 
				And Validate that the response body contains same userId 
				
				
				
	@APIhw03				
	Scenario: Get All Books Test
				When 	Send the request to the /BookStore/v1/Books end Point
				Then 	Validate that the Reponse Status code is 200
				And 	Validate that the Reponse Body contains 'books'
				Then 		Check that books array has 8 elements
				And 	Validate that the ISBN of the third book in the array is equal to "9781449337711"

				