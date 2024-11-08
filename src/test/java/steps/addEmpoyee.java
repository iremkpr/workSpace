package steps;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigsReader;
import utils.Constants;

public class addEmpoyee extends CommonMethods{
	@Given("Login to the HRM site")
	public void login_to_the_hrm_site() {
	   ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
	   login.uName.sendKeys(ConfigsReader.getProperty("username"));
	   login.password.sendKeys(ConfigsReader.getProperty("password"));
	   login.loginbutton.click();
	   waitForVisibility(dash.accountName);
		
	}
	@Given("Open the Add Employee page")
	public void open_the_add_employee_page() {
	   dash.pim.click();
	   dash.addEmp.click();
	   waitForVisibility(addEmp.fName);

	}
	@When("Fill the valid firstName {string} lastName  {string} and Location {string}")
	public void fill_the_valid_first_name_last_name_and_location(String firstName, String lastName, String location) {
	    // Write code here that turns the phrase above into concrete actions
		addEmp.fName.sendKeys(firstName);
		addEmp.lName.sendKeys(lastName);
		Select select=new Select(addEmp.locations);
		select.selectByVisibleText(location);
 
	}
	@When("Click the save button")
	public void click_the_save_button() {
	    // Write code here that turns the phrase above into concrete actions
		addEmp.save.click();
	}
	@Then("validate user {string} added Succesfully")
	public void validate_user_added_succesfully(String fullName) {
	   waitForVisibility(profile.fullName);
 	   assertTrue(profile.fullName.getText().equals(fullName), "User fullname didnt matched!");
	}
	@When("Fill the valid {string} {string}   and {string}")
	public void fill_the_valid_and( String firstName, String lastName, String location) {
	    // Write code here that turns the phrase above into concrete actions
		addEmp.fName.sendKeys(firstName);
		addEmp.lName.sendKeys(lastName);
		Select select=new Select(addEmp.locations);
		select.selectByVisibleText(location);
	}
	@When("Click the Create Login Details button")
	public void click_the_create_login_details_button() {
	    JavascriptExecutor js=(JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click()", addEmp.loginDetails);
	    waitForVisibility(addEmp.username);
	}
	@When("Fill username {string} password {string}")
	public void fill_username_password(String username, String password) {
		addEmp.username.sendKeys(username);
		addEmp.password.sendKeys(password);
		addEmp.confirmPassword.sendKeys(password);


	}
	@Then("validate the user {string} added succesfully")
	public void validate_the_user_added_succesfully(String fullName) {
	    // Write code here that turns the phrase above into concrete actions
		  waitForVisibility(profile.fullName);
	 	   assertTrue(profile.fullName.getText().equals(fullName), "User fullname didnt matched!");
	}
	@When("Delete existing id")
	public void delete_existing_id() {
	    // Write code here that turns the phrase above into concrete actions
	    addEmp.employeeId.clear();
	}

	@When("Fill valid firstName lastName and Location")
	public void fill_valid_first_name_last_name_and_location(io.cucumber.datatable.DataTable dataTable) {
	    
		List<Map<String,String>> listOfMap=dataTable.asMaps();
		for(Map<String,String> m:listOfMap) {
			String firstName=m.get("firstName");
			String lastName=m.get("lastName");
			String location=m.get("Location");
			addEmp.fName.sendKeys(firstName);
			addEmp.lName.sendKeys(lastName);
			Select locs=new Select(addEmp.locations);
			locs.selectByVisibleText(location);
		}
	}
	@Then("validate the user added Succesfully")
	public void validate_the_user_added_succesfully(io.cucumber.datatable.DataTable dataTable) {
		 waitForVisibility(profile.fullName);

	    List<Map<String,String>> listOfMaps=dataTable.asMaps();
	    for(Map<String,String> m :listOfMaps) {
	    	String uName=m.get("userName");
	    	assertTrue(profile.fullName.getText().equalsIgnoreCase(uName));
	    }
	}






}
