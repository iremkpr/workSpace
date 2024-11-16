package steps;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class demoGuru extends CommonMethods{
	/**
	 * Open chrome browser
	Go to https://demo.guru99.com/test/drag_drop.html
	Drag the following blocks in different ways
	Drag BANK to DEBIT SIDE
	Drag SALES to CREDIT SIDE
	Drag 5000 to DEBIT SIDE
	Drag 5000 to CREDIT SIDE
	Close the browser

	 */
	@Given("Open the https:\\/\\/demo.guru99.com\\/test\\/drag_drop.htm web site")
	public void open_the_https_demo_guru99_com_test_drag_drop_htm_web_site() {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("Drag {string} to {string}")
	public void drag_to(String drag, String drop) {
	    // Write code here that turns the phrase above into concrete actions
	   Actions actions=new Actions(driver);
	   
	   for(WebElement w:demoGuru.draggables) {
		   String dragElementName=w.getText();
		   if(dragElementName.equals(drag)) {
			  if(drop.equals("DEBIT SIDE")) {
				  
				  actions.dragAndDrop(w,demoGuru.debtAct).perform();
				  break;
			  }
			  else if(drop.equals("CREDIT SIDE")) {
				  actions.dragAndDrop(w,demoGuru.loan).perform();
				  break;
			  }
		   }
	   }
	   
	   
	}
	@When("Drag {int} to {string}")
	public void drag_to(Integer drag, String drop) throws InterruptedException {
		   Actions actions=new Actions(driver);
   
		for(WebElement w:demoGuru.draggables) {
			
			   String dragElementName=w.getText();
			   if(dragElementName.equals(drag.toString())) {
 					  if(drop.equals("DEBIT SIDE")) {
						  actions.dragAndDrop(w, demoGuru.debtAmout).perform();
						  break;
 					  }
					  else if(drop.equals("CREDIT SIDE")) {
						  actions.dragAndDrop(w, demoGuru.loanAmout).perform();
						  break;
					  }
			   }
		   }
		driver.manage().window().maximize();
		Thread.sleep(5000);
		Assert.assertTrue(demoGuru.perfectDisplay.isDisplayed());
	}
	   
	}







