package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class empProfile extends CommonMethods{

	@FindBy(id="pim.navbar.employeeName")
	public WebElement fullName;
	
	@FindBy(xpath="//img[@class='circle profile-photo valign tooltipped']")
	public WebElement photoIcon;
	
	@FindBy(xpath = "//input[@id='employeePicture']/..")
	public WebElement photoAdd;
	
	@FindBy(xpath = "//input[@id='employeePicture']")
	public WebElement photoAddInput;
	
	public empProfile() {
		PageFactory.initElements(driver, this);
	}
}
