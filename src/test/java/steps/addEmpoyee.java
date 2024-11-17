package steps;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigsReader;
import utils.Constants;

public class addEmpoyee extends CommonMethods {
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
		Select select = new Select(addEmp.locations);
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
	public void fill_the_valid_and(String firstName, String lastName, String location) {
		// Write code here that turns the phrase above into concrete actions
		addEmp.fName.sendKeys(firstName);
		addEmp.lName.sendKeys(lastName);
		Select select = new Select(addEmp.locations);
		select.selectByVisibleText(location);
	}

	@When("Click the Create Login Details button")
	public void click_the_create_login_details_button() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
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

		List<Map<String, String>> listOfMap = dataTable.asMaps();
		for (Map<String, String> m : listOfMap) {
			String firstName = m.get("firstName");
			String lastName = m.get("lastName");
			String location = m.get("Location");
			addEmp.fName.sendKeys(firstName);
			addEmp.lName.sendKeys(lastName);
			Select locs = new Select(addEmp.locations);
			locs.selectByVisibleText(location);
		}
	}

	@Then("validate the user added Succesfully")
	public void validate_the_user_added_succesfully(io.cucumber.datatable.DataTable dataTable) {
		waitForVisibility(profile.fullName);

		List<Map<String, String>> listOfMaps = dataTable.asMaps();
		for (Map<String, String> m : listOfMaps) {
			String uName = m.get("userName");
			assertTrue(profile.fullName.getText().equalsIgnoreCase(uName));
		}
	}

	@When("I add the valid datas from {string} excel sheet")
	public void i_add_the_valid_datas_from_excel_sheet(String sheetName) throws IOException, InterruptedException {
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata/last.xlsx";
		FileInputStream fis = new FileInputStream(path);
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheet = book.getSheet(sheetName);

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		for (int row = 1; row < rowCount; row++) {
			Map<String, String> m = new LinkedHashMap<>();
			for (int col = 0; col < colCount; col++) {
				String key = sheet.getRow(0).getCell(col).toString();
				String value = sheet.getRow(row).getCell(col).toString();
				m.put(key, value);
			}
			listOfMaps.add(m);
		}

		for (Map<String, String> m : listOfMaps) {

			addEmp.fName.sendKeys(m.get("FirstName"));
			addEmp.lName.sendKeys(m.get("LastName"));
			Select select = new Select(addEmp.locations);
			select.selectByVisibleText("Canadian Development Center");
			addEmp.save.click();

			waitForVisibility(profile.fullName);
			String fulName = m.get("FirstName") + " " + m.get("LastName");
			Assert.assertTrue(profile.fullName.getText().equals(fulName));

			dash.addEmp.click();
			waitForVisibility(addEmp.fName);
		}

		book.close();

	}

	@When("I add the valid datas via  {string} excel sheet")
	public void i_add_the_valid_datas_via_excel_sheet(String sheetName) throws IOException, InterruptedException {
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata/last.xlsx";
		FileInputStream fis = new FileInputStream(path);
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheet = book.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		List<Map<String, String>> listOfMaps = new ArrayList<>();

		for (int row = 1; row < rowCount; row++) {
			Map<String, String> map = new LinkedHashMap<>();
			for (int col = 0; col < colCount; col++) {
				String key = sheet.getRow(0).getCell(col).toString();
				String value = sheet.getRow(row).getCell(col).toString();
				map.put(key, value);
			}
			listOfMaps.add(map);
		}

		for (Map<String, String> m : listOfMaps) {
			addEmp.fName.sendKeys(m.get("FirstName"));
			addEmp.lName.sendKeys(m.get("LastName"));
			Select select = new Select(addEmp.locations);
			select.selectByVisibleText("Canadian Development Center");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", addEmp.loginDetails);

			addEmp.username.sendKeys(m.get("Username"));
			addEmp.password.sendKeys(m.get("Password"));
			addEmp.confirmPassword.sendKeys(m.get("Password"));
			Thread.sleep(1000);
			addEmp.save.click();
			waitForVisibility(profile.fullName);

			dash.addEmp.click();
			waitForVisibility(addEmp.fName);
		}

		book.close();

	}
	@When("Click user profile picture icon and update the picture")
	public void click_user_profile_picture_icon_and_update_the_picture() throws InterruptedException {
	    
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
	    wait.until(ExpectedConditions.visibilityOf(profile.photoIcon));
	    profile.photoIcon.click();
	    wait.until(ExpectedConditions.visibilityOf(profile.photoAdd));
	    String filePath=System.getProperty("user.dir")+"/screenshots/failed/pp.png";
	    profile.photoAddInput.sendKeys(filePath);
	}
	@Then("Validate that the user profile photo updated")
	public void validate_that_the_user_profile_photo_updated() {
	   
	}

}
