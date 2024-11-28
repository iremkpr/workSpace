package pages;

import java.util.List;

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
	@FindBy(xpath = "//button[@id='postSubmitBtn']")
	public WebElement statuPost;
	
	@FindBy(id = "65")
	public WebElement postBasedOnIndex;
	
	@FindBy(xpath="//textarea[@id='createPost_content']")
	public WebElement statuUpdate;
	
	@FindBy(xpath = "//div[@id='postContent_57']")
	public WebElement postContentWithIndex;
	
	@FindBy(xpath = "//div[@id='rightBarHeadingMl']")
	public WebElement likedPosts;
	
	@FindBy(xpath="//div[@id='ml_componentContainer']//div[@class='birthdayUserName']")
	public List<WebElement> LikedPostNames;
	
	@FindBy(xpath = "//div[@class='modal hide modalPopUP in']//div[@id='postBody']//div[@id='postEmployeeName']//a")
	public WebElement sharedPostName;
	
	public buzzSocialPlatform() {
		PageFactory.initElements(driver, this);
	}
}
