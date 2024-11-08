package steps;

import static org.testng.Assert.assertTrue;

//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

 import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import org.apache.poi.ss.usermodel.Sheet;
  
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
		String expectedName = ConfigsReader.getProperty("accountName");
		Assert.assertTrue("USER NOT LOGGED IN", name.equals(expectedName));

	}

	@Then("I validate the warning")
	public void i_validate_the_warning() {
		// Write code here that turns the phrase above into concrete actions
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(login.reLgnUName));
		assertTrue(login.warning.isDisplayed(), "Warning is not exist! TEST FAILED");

	}

	@Given("I fill  valid userName {string}")
	public void i_fill_valid_user_name(String userName) {

		login.uName.sendKeys(userName);

	}

	@Given("I fill valid password {string}")
	public void i_fill_valid_password(String password) {
	
		login.password.sendKeys(password);
	
	}

	@Then("I validate the user succesfully logged in {string}")
	public void i_validate_the_user_succesfully_logged_in(String uName) {
			
		String accountName=dash.accountName.getText();
		Assert.assertTrue("AccountName does not matched", accountName.equals(uName));
	}
	@Given("Fill the UserName and Password valid data")
	public void fill_the_user_name_and_password_valid_data(io.cucumber.datatable.DataTable dataTable) {
	    
		List<Map<String, String>> maps=dataTable.asMaps();
		for(Map<String,String> m:maps) {
			String userName=m.get("UserName");
			String password=m.get("Password");
			login.uName.sendKeys(userName);
			login.password.sendKeys(password);
			login.loginbutton.click();
		}
	}
	@Then("Validate that the user Account Name matched with expexted Value")
	public void validate_that_the_user_account_name_matched_with_expexted_value(io.cucumber.datatable.DataTable dataTable) {
	   List<Map<String,String>> maps=dataTable.asMaps();
	   for(Map<String,String> m:maps) {
		  String expectedName= m.get("accountName");
		  String actualName=dash.accountName.getText();
		  Assert.assertEquals(expectedName, actualName);
	   }
	}
	@Given("Get the valid data from {string} excel sheet and fill the username, password text boxes")
	public void get_the_valid_data_from_excel_sheet_and_fill_the_username_password_text_boxes(String sheetName) throws IOException {
	    String filePath= System.getProperty("user.dir")+"/src/test/resources/testdata/Excel-3.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheet = book.getSheet(sheetName);

		int rowCount=sheet.getPhysicalNumberOfRows();
		int colCount=sheet.getRow(0).getLastCellNum();

		List<Map<String, String>> excelList=new ArrayList<>();

		for(int row=1; row<rowCount; row++) {
			Map<String, String> rowMap=new LinkedHashMap<>();
			for(int col=0; col<colCount;col++) {
				String key=sheet.getRow(0).getCell(col).toString();
				String value=sheet.getRow(row).getCell(col).toString(); 		
				rowMap.put(key, value);
			}
			excelList.add(rowMap);
		} 
			
		
		for(Map<String, String> m:excelList) {
			String uName=m.get("Username");
			String password=m.get("Password");
			login.uName.sendKeys(uName);
			login.password.sendKeys(password);
			login.loginbutton.click();
			String acctName=m.get("FirstName")+" "+m.get("LastName");
			Assert.assertTrue(dash.accountName.getText().equals(acctName));
			dash.userNameOpenDown.click();
			dash.logOut.click();
			
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(login.uName));
		}
		
		book.close();
	}




}
