package steps;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.CommonMethods;

public class news extends CommonMethods {

	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	
	@Given("Click the Anouncements Menu Item")
	public void click_the_anouncements_menu_item() {
		news.announcements.click();
	}

	@Given("Click the News Inner Menu Item")
	public void click_the_news_inner_menu_item() {
		news.newsElement.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(news.newsTitles));

	}

	@Given("Click Dinner Dance news")
	public void click_dinner_dance_news() {
		for(WebElement e:news.newsTitles) {
			String name=e.getText();
			if(name.contains("Dinner Dance")) {
				e.click();
				break;
			}
		}
	}

	@Then("Validate that the news contains {string} message")
	public void validate_that_the_news_contains_message(String expected) {
		String actual=news.danceMessage.getText();
		Assert.assertTrue(expected.equals(actual));
	}

	@Then("Click the I acknowledge that I have read this news. checkbox")
	public void click_the_i_acknowledge_that_i_have_read_this_news_checkbox() {
		news.checkbox.click();
		wait.until(ExpectedConditions.visibilityOf(news.yesAcknowladge));
		
	}

	@Then("Accept the pop-up")
	public void accept_the_pop_up() {
		news.yesAcknowladge.click();
	}

	@Then("Validate that the Succesfully Acknowladge")
	public void validate_that_the_succesfully_acknowladge() {
		Assert.assertTrue(news.toastMessage.isDisplayed());

	}

}
