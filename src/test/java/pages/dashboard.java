package pages;

 import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class dashboard extends CommonMethods{

	@FindBy(id = "account-name")
	public WebElement accountName;

	@FindBy(xpath = "//span[text()='PIM']")
	public WebElement pim;

	@FindBy(xpath = "//span[text()='Add Employee']")
	public WebElement addEmp;
	 
	@FindBy(xpath="//i[text()='keyboard_arrow_down']")
	public WebElement userNameOpenDown;
	
	@FindBy(id="logoutLink")
	public WebElement logOut;
	
	@FindBy(xpath="//span[text()='Employee List']")
	public WebElement empList;
	
	@FindBy(xpath = "//span[text()='Buzz']")
	public WebElement buzz;
	
	public dashboard() {
		PageFactory.initElements(driver, this);
	}
}
