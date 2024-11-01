package steps;


import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class apiTest {
	public Response response;
	public RequestSpecification request;
 	@Given("Add {string} query parameter for ISBN")
	public void add_query_parameter_for_isbn(String ISBN) {
 		  RestAssured.baseURI="https://bookstore.toolsqa.com";
 		  request=RestAssured.given().queryParam("ISBN", ISBN);
	}
	@When("I send the request")
	public void i_send_the_request() {
		response=request.when().get("/BookStore/v1/Book");
		response.prettyPrint();
	}
	@Then("I validate the status code {int}")
	public void i_validate_the_status_code(Integer int1) {
	    response.then().assertThat().statusCode(int1);
	}
	@Then("I validate the ISBN {string}")
	public void i_validate_the_isbn(String isbn) {
	    response.then().assertThat().body("isbn", Matchers.equalTo(isbn));
	}
	@When("Send request with All books end point")
	public void send_request_with_all_books_end_point() {
	    // Write code here that turns the phrase above into concrete actions
 	}
	@When("validate books are returned")
	public void validate_books_are_returned() {
	    // Write code here that turns the phrase above into concrete actions
 	}




}