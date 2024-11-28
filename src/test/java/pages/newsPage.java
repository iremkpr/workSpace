package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class newsPage extends CommonMethods{

	@FindBy(xpath = "//li[@id='menu_news_More']//span[text()='Announcements']")
	public WebElement announcements;
	@FindBy(xpath = "//li[@id='menu_news_More']//span[text()='News']")
	public WebElement newsElement;

	@FindBy(xpath = "//div[@id='newsContent']//div[@id='header']")
	public List<WebElement> newsTitles;
	
	@FindBy(xpath = "//li[@id='articleId_43']//p[3]")
	public WebElement danceMessage;

	@FindBy(xpath="//label[@for='news_43']")
	public WebElement checkbox;
	
	@FindBy(xpath = "//a[text()='yes, acknowledge']")
	public WebElement yesAcknowladge;
	
	@FindBy(xpath = "//div[@class='toast-message']")
	public WebElement toastMessage;
	
	public newsPage() {
		PageFactory.initElements(driver, this);
	}
}
