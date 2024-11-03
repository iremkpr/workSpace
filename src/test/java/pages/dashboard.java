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
	 
	
	
	
	public dashboard() {
		PageFactory.initElements(driver, this);
	}
}
