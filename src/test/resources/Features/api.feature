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
 