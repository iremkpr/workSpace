package steps;

import java.time.Duration;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class buzz extends CommonMethods {
	@When("Click the buzz menu item")
	public void click_the_buzz_menu_item() {
	    dash.buzz.click();
	    String wind=driver.getWindowHandle();
	    Set<String> windSet=driver.getWindowHandles();
	    for(String str:windSet) {
	    	if(!str.equals(wind)) {
	    		driver.switchTo().window(str);
	    		break;
	    	}
	    }
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(25));
	    wait.until(ExpectedConditions.visibilityOf(buzz.addImg));

	}
	@When("Click the upload images and add image")
	public void click_the_upload_images_and_add_image() {
		buzz.addImg.click();
		String filepath=System.getProperty("user.dir")+"/screenshots/dat.png";
		buzz.imgUpload.sendKeys(filepath);
 	}
	@When("Click the Post button")
	public void click_the_post_button() {
		buzz.post.click();

	}
	@Then("Validate that the user share a new post")
	public void validate_that_the_user_share_a_new_post() throws InterruptedException {

		Assert.assertTrue(buzz.postBasedOnIndex.isDisplayed());
		Thread.sleep(3000);
	}



}
