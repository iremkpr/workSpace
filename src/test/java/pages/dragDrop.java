package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class dragDrop extends CommonMethods{

	@FindBy(xpath="//a[text()=' BANK ']")
	public WebElement bank;
	
	@FindBy(xpath="//ol[@id='bank']//li")
	public WebElement debtAcct;

	@FindBy(xpath="//a[text()=' SALES ']")
	public WebElement sales;
	
	@FindBy(xpath="//ol[@id='loan']//li")
	public WebElement creditAcct;

	@FindBy(xpath="//a[text()=' 5000 ']")
	public WebElement bank5000;
	 
	@FindBy(xpath="//ol[@id='amt7']//li")
	public WebElement debtAmount;
	
	@FindBy(xpath="//a[text()=' 5000']")
	public WebElement credit5000;

	@FindBy(xpath="//ol[@id='amt8']//li")
	public WebElement creditAmount;
	
	@FindBy(xpath="//a[text()='Perfect!']")
	public WebElement perfectText;

	
 	public dragDrop() {
		PageFactory.initElements(driver, this);
	}
}
