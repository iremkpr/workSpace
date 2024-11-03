package steps;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigsReader;
import utils.Constants;

public class loginHRM extends CommonMethods {
	@Given("I fill the  valid userName {string}")
	public void i_fill_the_valid_user_name(String name) {
		login.uName.sendKeys(name);
	}

	@Given("I fill the valid password {string}")
	public void i_fill_the_valid_password(String pass) {
		login.password.sendKeys(pass);
	}

	@When("I click the Login button")
	public void i_click_the_save_button() {
		login.loginbutton.click();
	}

	@Then("I validate the user succesfully logged in")
	public void i_validate_the_user_succesfully_logged_in() {
		// Write code here that turns the phrase above into concrete actions
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(login.accountName));
		String name = login.accountName.getText();
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		String expectedName=ConfigsReader.getProperty("accountName");
		Assert.assertTrue("USER NOT LOGGED IN", name.equals(expectedName));
		
	}
	@Then("I validate the warning")
	public void i_validate_the_warning() {
	    // Write code here that turns the phrase above into concrete actions
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(login.reLgnUName));
		assertTrue(login.warning.isDisplayed(),"Warning is not exist! TEST FAILED");
		
	}




}
