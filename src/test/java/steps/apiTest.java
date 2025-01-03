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

//	----------------------
	public Response r2;
	
	@When("Send the request to the \\/BookStore\\/v1\\/Books end Point")
	public void send_the_request_to_the_book_store_v1_books_end_point() {
	   RestAssured.baseURI="https://bookstore.toolsqa.com";
	   r2=RestAssured.when().get("/BookStore/v1/Books");
	   r2.prettyPeek();
	}
	@Then("Validate that the Reponse Status code is {int}")
	public void validate_that_the_reponse_status_code_is(Integer status) {
	    r2.then().assertThat().statusCode(status);
	}
	@Then("Validate that the Reponse Body contains {string}")
	public void validate_that_the_reponse_body_contains(String value) {
	    // Write code here that turns the phrase above into concrete actions
	   r2.then().assertThat().body("books", Matchers.notNullValue());
	}
	@Then("Check that books array has {int} elements")
	public void check_that_books_array_has_elements(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
 	   r2.then().assertThat().body("books.size()",Matchers.equalTo(8));
	}
	@Then("Validate that the ISBN of the third book in the array is equal to {string}")
	public void validate_that_the_isbn_of_the_third_book_in_the_array_is_equal_to(String ISBN) {
	   r2.then().assertThat().body("books[2].isbn", Matchers.equalTo(ISBN));
	 //  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

	}

	@Test
	public static void hw4API() {
/*CReated user 
 * "userID": "792091b8-581e-4177-8ea1-1ca71964375d",
    "username": "IremMeri",
    password= iremkop10Aa*
 * Try to generate token using a POST request.
Check swagger to see what GenerateToken request needs.
Check the status to be 200
Check the body to contain "token"*/

		RestAssured.baseURI="https://bookstore.toolsqa.com";
		Response responseNew=RestAssured.given().header("Content-Type","application/json")
												.body("{\n"
														+ "  \"userName\": \"IremMeri\",\n"
														+ "  \"password\": \"iremkop10Aa*\"\n"
														+ "}")
												.when().post("/Account/v1/GenerateToken");
		responseNew.prettyPrint();
		responseNew.then().assertThat().statusCode(200);
		responseNew.then().assertThat().body("token", Matchers.notNullValue());
		responseNew.then().assertThat().body("status", Matchers.equalTo("Success"));
		String token=responseNew.body().jsonPath().getString("token");
		System.out.println(token);
		responseNew.then().assertThat().statusCode(200);
	boolean c=	responseNew.body().jsonPath().getString("token").isEmpty();
	System.out.println(c);
	}

	@Test
	public static void deleteAccHW5() {
		/*Delete the Account using this endpoint /Account/v1/User/{UUID}
Check swagger to see what Delete Account request needs.*/
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		Response newResponse=RestAssured.given().auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IklyZW1NZXJpIiwicGFzc3dvcmQiOiJpcmVta29wMTBBYSoiLCJpYXQiOjE3MzQzNzU3NDl9.ovCTvvB_BhzN_FniJ0MchX6OjTRYOFCzCzxrgZ3ww7w").pathParam("UserId", "792091b8-581e-4177-8ea1-1ca71964375d")
										.when().delete("/Account/v1/User/{UserId}");
		newResponse.then().assertThat().statusCode(200);
	}

	@Test
	public void createNewUser1() {
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		Response response=RestAssured.given().header("Content-Type","application/json")
									 		 .body("{\n"
									 		 		+ "  \"userName\": \"deneme02\",\n"
									 		 		+ "  \"password\": \"adana01Aa*\"\n"
									 		 		+ "}")
									 .when().post("/Account/v1/User");		 
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
		response.then().assertThat().body("userID", Matchers.notNullValue());
		String id=response.body().jsonPath().getString("userID");
		System.out.println(id);
	}
	//token=== eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRlbmVtZTAyIiwicGFzc3dvcmQiOiJhZGFuYTAxQWEqIiwiaWF0IjoxNzM1MjYwNDUzfQ.zFbLfBD1koHH9QqcexyajdWsDFP2VORS16Gs0Te-xBU
	//uName= deneme02
	//password= adana01Aa*
	//id= 4d84955e-b2d7-4131-8ed0-9af4e2bea62a
	@Test
	public void tokenForUser() {
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		Response response=RestAssured.given().header("Content-Type","application/json")
											 .body("{\n"
											 		+ "  \"userName\": \"deneme02\",\n"
											 		+ "  \"password\": \"adana01Aa*\"\n"
											 		+ "}")
									 .when().post("/Account/v1/GenerateToken");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().body("token", Matchers.notNullValue());

		String token=response.body().jsonPath().getString("token");
		System.out.println(token);
		
 	}
	//9781449325862
	//9781449331818
	//9781449337711
	@Test
	public void publishBooks() {
		 RestAssured.baseURI="https://bookstore.toolsqa.com";	
		 String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRlbmVtZTAyIiwicGFzc3dvcmQiOiJhZGFuYTAxQWEqIiwiaWF0IjoxNzM1MjYwNDUzfQ.zFbLfBD1koHH9QqcexyajdWsDFP2VORS16Gs0Te-xBU";
		 Response response=RestAssured.given().auth().oauth2(token).header("Content-Type","application/json").body("{\n"
		 		+ "  \"userId\": \"4d84955e-b2d7-4131-8ed0-9af4e2bea62a\",\n"
		 		+ "  \"collectionOfIsbns\": [\n"
		 		+ "    {\n"
		 		+ "      \"isbn\": \"9781491950296\"\n"
		 		+ "    },\n"
		 		+ "    {\n"
		 		+ "      \"isbn\": \"9781593275846\"\n"
		 		+ "    }\n"
		 		+ "  ]\n"
		 		+ "}").post("/BookStore/v1/Books");
		 response.prettyPrint();
		 response.then().assertThat().statusCode(201);
		 response.then().assertThat().body("books.isbn[0]", Matchers.equalTo("9781491950296"));
 	}
	@Test
	public void getMyUser() {
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRlbmVtZTAyIiwicGFzc3dvcmQiOiJhZGFuYTAxQWEqIiwiaWF0IjoxNzM1MjYwNDUzfQ.zFbLfBD1koHH9QqcexyajdWsDFP2VORS16Gs0Te-xBU";

		Response response=RestAssured.given().auth().oauth2(token).pathParam("UUID", "4d84955e-b2d7-4131-8ed0-9af4e2bea62a")
								 	.when().get("/Account/v1/User/{UUID}");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	@Test
	public void deleteBooksUser() {
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRlbmVtZTAyIiwicGFzc3dvcmQiOiJhZGFuYTAxQWEqIiwiaWF0IjoxNzM1MjYwNDUzfQ.zFbLfBD1koHH9QqcexyajdWsDFP2VORS16Gs0Te-xBU";

		Response response=RestAssured.given().auth().oauth2(token).header("Content-Type","application/json").body("{\n"
				+ "  \"isbn\": \"9781449325862\",\n"
				+ "  \"userId\": \"4d84955e-b2d7-4131-8ed0-9af4e2bea62a\"\n"
				+ "}").when().delete("/BookStore/v1/Book");
		response.prettyPrint();
		response.then().assertThat().statusCode(204);
		
	}
	@Test
	public void putUpdate() {
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRlbmVtZTAyIiwicGFzc3dvcmQiOiJhZGFuYTAxQWEqIiwiaWF0IjoxNzM1MjYwNDUzfQ.zFbLfBD1koHH9QqcexyajdWsDFP2VORS16Gs0Te-xBU";
		Response response=RestAssured.given().auth().oauth2(token).pathParam("ISBN", "9781449331818")
									 .header("Content-Type","application/json")
									 .body("{\n"
									 		+ "  \"userId\": \"4d84955e-b2d7-4131-8ed0-9af4e2bea62a\",\n"
									 		+ "  \"isbn\": \"9781449325862\"\n"
									 		+ "}")
									 .when().put("/BookStore/v1/Books/{ISBN}");
			response.prettyPrint();
			response.then().assertThat().statusCode(200);
	}
	@Test
	public void deleteToTheAllBooks() {
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRlbmVtZTAyIiwicGFzc3dvcmQiOiJhZGFuYTAxQWEqIiwiaWF0IjoxNzM1MjYwNDUzfQ.zFbLfBD1koHH9QqcexyajdWsDFP2VORS16Gs0Te-xBU";
		Response response=RestAssured.given().auth().oauth2(token).queryParam("UserId","4d84955e-b2d7-4131-8ed0-9af4e2bea62a")
													.when().delete("/BookStore/v1/Books");
		response.prettyPrint();
		response.then().assertThat().statusCode(204);
		
	}
	@Test
	public void getAllBooksNew() {
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		Response response=RestAssured.when().get("/BookStore/v1/Books");
		response.prettyPrint();
	}
}