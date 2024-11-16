package steps;

import io.restassured.specification.RequestSpecification;
import utils.ConfigsReader;
import utils.Constants;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

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
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		request = RestAssured.given().queryParam("ISBN", ISBN);
	}

	@When("I send the request")
	public void i_send_the_request() {
		response = request.when().get("/BookStore/v1/Book");
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

	@Given("Send body  with valid data")
	public void send_body_with_valid_data() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		response = RestAssured.given().header("Content-Type", "application/json")
				.body("{\n" + "  \"userName\": \"new123\",\n" + "  \"password\": \"iremkop10Aa*\"\n" + "}").when()
				.post("/Account/v1/User");
		/**
		 * { "userID": "7b0ceda1-dd34-4bee-9109-be85723ec88a", 
		 * 	 "username": "new123",
		 *   "books": [
		 * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6Im5ldzEyMyIsInBhc3N3b3JkIjoiaXJlbWtvcDEwQWEqIiwiaWF0IjoxNzMwNzMyMDM5fQ.ghR2zwzqBMb4I_j0FFHHOAqcL8nbrMrSexV8FsQOX2s
		 * ] }
		 */

		response.prettyPrint();
	}

	@Then("Validate response status code is {int}")
	public void validate_response_status_code_is(Integer statusCode) {
		// Write code here that turns the phrase above into concrete actions
		response.then().statusCode(statusCode);
	}
	@Given("Send the body to {string} endpoint")
	public void send_the_body_to_endpoint(String endpoint) {
	    RestAssured.baseURI="https://bookstore.toolsqa.com";
		response=RestAssured.given().header("Content-Type","application/json")
				.body("{\n" + "  \"userName\": \"new123\",\n" + "  \"password\": \"iremkop10Aa*\"\n" + "}")
								    .when().post("/Account/v1/GenerateToken");
		response.prettyPrint();
		//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6Im5ldzEyMyIsInBhc3N3b3JkIjoiaXJlbWtvcDEwQWEqIiwiaWF0IjoxNzMwNzMyMDM5fQ.ghR2zwzqBMb4I_j0FFHHOAqcL8nbrMrSexV8FsQOX2s
	}
	@Then("Validate that the token is exist on response body")
	public void validate_that_the_token_is_exist_on_response_body() {
	    // Write code here that turns the phrase above into concrete actions
		response.then().assertThat().body("token", Matchers.notNullValue());
	}

	public static String userId="7b0ceda1-dd34-4bee-9109-be85723ec88a";
	public static String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6Im5ldzEyMyIsInBhc3N3b3JkIjoiaXJlbWtvcDEwQWEqIiwiaWF0IjoxNzMwNzMyMDM5fQ.ghR2zwzqBMb4I_j0FFHHOAqcL8nbrMrSexV8FsQOX2s";
	public static String username="new123";

	@Given("Add the path parameter {string} on response and send it {string} endpoint")
	public void add_the_path_parameter_on_response_and_send_it_endpoint(String pathParam, String endpoint) {
	   RestAssured.baseURI="https://bookstore.toolsqa.com";
	   response=RestAssured.given().auth().oauth2(token).pathParam(pathParam, userId).when().get(endpoint);
	   response.prettyPrint();
	}
	@Then("Validate that the response body contains same userId")
	public void validate_that_the_response_body_contains_same_user_id() {
	    // Write code here that turns the phrase above into concrete actions
	    response.then().body("userId", Matchers.equalToIgnoringCase(userId));
	}
	@Test
	public void testingAPI() {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		RestAssured.baseURI=ConfigsReader.getProperty("baseURI");
		Response response=RestAssured.given().when().get("/BookStore/v1/Books");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}

}