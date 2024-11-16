package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;

public class employeeList extends CommonMethods{
	
	@FindBy(xpath="//table[@id='employeeListTable']//tbody//tr/td[3]")
	public List<WebElement> employeeNames;
	
	public employeeList() {
		PageFactory.initElements(driver, this);
	}
}
