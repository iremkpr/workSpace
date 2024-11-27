package pages;

import java.util.List;

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
	
	@FindBy(xpath="//input[@id='emp_birthday']/..//i")
	public WebElement birthDate;
	
	@FindBy(xpath="//div[@class='select-wrapper picker__select--month']//input")
	public WebElement dateMonthIcon;
	
	@FindBy(xpath="//div[@class='select-wrapper picker__select--month']//ul/li/span")
	public List<WebElement> dateMonths;
	
	@FindBy(xpath="//div[@class='select-wrapper picker__select--year']//input")
	public WebElement dateYearIcon;
	
	@FindBy(xpath="//div[@class='select-wrapper picker__select--year']//ul/li/span")
	public List<WebElement> dateYears;
	
	
	@FindBy(xpath="//label[@for='emp_birthday']/..//table//tbody//tr//td/div[contains(@class,'picker__day picker__day--infocus')]")
	public List<WebElement> days;
	
	@FindBy(xpath="//div[@id='emp_marital_status_inputfileddiv']//input")
	public WebElement merriedStatusIcon;

	@FindBy(xpath="//div[@id='emp_marital_status_inputfileddiv']//ul//li/span")
	public List<WebElement> merriedStatus;
	
	@FindBy(xpath="//div[@id='emp_gender_inputfileddiv']//input")
	public WebElement genderIcon;
	

	@FindBy(xpath="//div[@id='emp_gender_inputfileddiv']//ul//li/span")
	public List<WebElement> genders;
	
	@FindBy(xpath = "//label[@for='smoker']")
	public WebElement smokerCheckBox;
	
	@FindBy(xpath = "//div[@id='eeo_race_ent_inputfileddiv']//input")
	public WebElement raceIcon;
	
	@FindBy(xpath="//div[@id='eeo_race_ent_inputfileddiv']//ul//li/span")
	public List<WebElement> races;
	
	@FindBy(xpath = "//div[@id='personal_details_tab']//button[@type='submit']")
	public WebElement personalDetailSave;
	
	@FindBy(xpath = "//div[@class='toast-message']")
	public WebElement succesSaveMessage;
	
	@FindBy(xpath="//div[@ng-model=\"model['14']\"]//div//label")
	public List<WebElement> activities;
	
	@FindBy(xpath = "//form[@name='customFieldsForm_1']//button")
	public WebElement preferencSave;

	@FindBy(xpath = "//a[text()='Add']")
	public WebElement attachmant;
	
	@FindBy(xpath = "//input[@id='filename']")
	public WebElement attachmantBrowse;
	//form[@name='oxdModalForm']//span/input
	public empProfile() {
		PageFactory.initElements(driver, this);
	}
}
