package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class buzzSocialPlatform extends CommonMethods{

	@FindBy(xpath = "//a[@id='images-tab-label']")
	public WebElement addImg;
	
	@FindBy(xpath = "//input[@id='photofile']")
	public WebElement imgUpload;
 
	@FindBy(xpath = "//button[@id='imageUploadBtn']")
	public WebElement post;
	
	@FindBy(id = "65")
	public WebElement postBasedOnIndex;
	
	public buzzSocialPlatform() {
		PageFactory.initElements(driver, this);
	}
}
