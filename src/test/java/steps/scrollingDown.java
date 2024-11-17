package steps;

 
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class scrollingDown extends CommonMethods{
	@Given("Open the browser and navigate it https:\\/\\/the-internet.herokuapp.com\\/ site")
	public void open_the_browser_and_navigate_it_https_the_internet_herokuapp_com_site() {
	     
	    
	}
	@When("Click on the {string} link")
	public void click_on_the_link(String string) {
	   driver.findElement(By.xpath("//a[@href='/infinite_scroll']")).click();
	}
	@When("Scroll down by {int} pixels {int} times with waiting {int} second between each scroll to the tag h3")
	public void scroll_down_by_pixels_times_with_waiting_second_between_each_scroll_to_the_tag_h3(Integer pixels, Integer times, Integer wait) throws InterruptedException {
	    
		JavascriptExecutor js=(JavascriptExecutor) driver;
		 
		for(int i=0;i<times;i++) {
			js.executeScript("window.scrollBy(0,"+pixels+")");
			Thread.sleep(1000);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		}
	}
	@Then("Validate page scrolled Down")
	public void validate_page_scrolled_down() {
	    JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
	    WebElement h3=driver.findElement(By.xpath("//h3"));
	    jsExecutor.executeScript("arguments[0].scrollIntoView(true)",h3 );
	    String wind=driver.getTitle();
	    System.out.println(wind);
	    Assert.assertEquals("The Internet",wind);
 	}
	@Then("Close the browser")
	public void close_the_browser() {
	    // Write code here that turns the phrase above into concrete actions
 	}


}
