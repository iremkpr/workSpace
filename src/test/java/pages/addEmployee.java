package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class addEmployee extends CommonMethods{
	
	@FindBy(xpath = "//input[@id='first-name-box']")
	public WebElement fName;

	@FindBy(xpath = "//input[@id='last-name-box']")
	public WebElement lName;

	@FindBy(xpath="//select[@id='location']")
	public WebElement locations;

	@FindBy(id = "modal-save-button")
	public WebElement save;

	@FindBy(id="hasLoginDetails")
	public WebElement loginDetails;
	
	@FindBy(id="username")
	public WebElement username;

	@FindBy(id="password")
	public WebElement password;

	@FindBy(id="confirmPassword")
	public WebElement confirmPassword;
	
	@FindBy(id="employeeId")
	public WebElement employeeId;
	
	
	public addEmployee() {
		PageFactory.initElements(driver, this);
	}
}
