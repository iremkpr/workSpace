package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class demoGuru99 extends CommonMethods{
	@FindBy(xpath = "//div[@class='ui-widget-content']//ul/li/a")
	public List<WebElement> draggables;
	
	@FindBy(xpath = "//ol[@id='bank']/li")
	public WebElement debtAct;
	
	@FindBy(xpath = "//ol[@id='loan']/li")
	public WebElement loan;
	
	
	@FindBy(xpath = "//ol[@id='amt7']/li")
	public WebElement debtAmout ;

	@FindBy(xpath = "//ol[@id='amt8']/li")
	public WebElement loanAmout ;
	
	@FindBy(xpath = "//div[@class='table4_result']//a")
	public WebElement perfectDisplay;
	
 	public demoGuru99(){
		PageFactory.initElements(driver,this);
	}
}
