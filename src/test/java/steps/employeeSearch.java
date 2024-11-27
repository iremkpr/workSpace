package steps;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class employeeSearch extends CommonMethods{
	public WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	
	@Given("Open the Employee List page")
	public void open_the_employee_list_page() {
	    // Write code here that turns the phrase above into concrete actions
 		dash.pim.click();
		dash.empList.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(empList.employeeNames));
		
	}
	@When("Search Employee {string} by name and click")
	public void search_employee_by_name_and_click(String expectedName) {
	   
		for(WebElement w:empList.employeeNames) {
			String actualName=w.getText();
			if(actualName.equals(expectedName)) {
				w.click();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOf(profile.fullName));
 	}
	@Then("Validate that user detail page opened {string}")
	public void validate_that_user_detail_page_opened(String fullName) {
	    Assert.assertTrue("User FullName doesnt matched",profile.fullName.getText().equals(fullName));
 	}




}
