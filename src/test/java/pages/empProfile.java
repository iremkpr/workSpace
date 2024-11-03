package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class empProfile extends CommonMethods{

	@FindBy(id="pim.navbar.employeeName")
	public WebElement fullName;
	
	public empProfile() {
		PageFactory.initElements(driver, this);
	}
}
