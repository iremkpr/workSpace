package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class loginPage extends CommonMethods{
	
	@FindBy(id="txtUsername")
	public WebElement uName;
	
	@FindBy(id="txtPassword")
	public WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginbutton;
	
	@FindBy(id = "account-name")
	public WebElement accountName;
	
	//new
	public loginPage(){
		PageFactory.initElements(driver,this);
	}
	
}
