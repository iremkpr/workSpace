package steps;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

public class employeeProfile extends CommonMethods {

	@When("Update existing birth date with {string}")
	public void update_existing_birth_date_with(String date) throws InterruptedException {
		String[] dateArray = date.split("/");

		int[] arr = new int[dateArray.length];
		for (int i = 0; i < dateArray.length; i++) {
			arr[i] = Integer.parseInt(dateArray[i]);
		}


		// click Icon
		profile.birthDate.click();

		// selection of month
		int month = arr[1];
		String monthString = numberDayToString(month);
		profile.dateMonthIcon.click();
		for (WebElement w : profile.dateMonths) {

			if (w.getText().equals(monthString)) {
				w.click();
				break;
			}
		}

		
		// selection of year
		profile.dateYearIcon.click();
		String year = dateArray[2];
		for (WebElement w : profile.dateYears) {
			String value=w.getText().trim();
 			if(value.equals(year)) {
				w.click();
				break;
			}
		}
			
		//day selection
		int day = arr[0];
		for(WebElement w:profile.days) {
			String elementText=w.getText().trim();
			int actual=Integer.parseInt(elementText);
			if(actual==day) {
				w.click();
				break;
			}
		}
		Thread.sleep(3000);
	}

	public static String numberDayToString(int month) {
		String m = null;
		if (month == 01) {
			m = "January";
		} else if (month == 02) {
			m = "February";
		}
		return m;
	}
	@When("Update Marital status as {string}")
	public void update_marital_status_as(String MaritalStatu) throws InterruptedException {
	
		profile.merriedStatusIcon.click();
		for(WebElement w:profile.merriedStatus) {
	    	if(w.getText().equals(MaritalStatu)) {
	    		w.click();
	    		break;
	    	}
	    }
	    Thread.sleep(3000);
	}
	@When("Update Gender status as {string}")
	public void update_gender_status_as(String gender) {
	    profile.genderIcon.click();
	    for(WebElement w: profile.genders) {
	    	if(w.getText().equals(gender)) {
	    		w.click();
	    		break;
	    	}
	    }
	}
	@When("Click the smocker checkbox")
	public void click_the_smocker_checkbox() {
	    // Write code here that turns the phrase above into concrete actions
	    profile.smokerCheckBox.click();
	}
	@When("Update Race information as {string}")
	public void update_race_information_as(String race) {
	    
		profile.raceIcon.click();
		
		for(WebElement w: profile.races) {
	    	if(w.getText().equals(race)) {
	    		w.click();
	    		break;
	    	}
	    }
		
		
	}
	@Then("Save the updated personal details")
	public void save_the_updated_personal_details() {
	    // Write code here that turns the phrase above into concrete actions
	    profile.personalDetailSave.click();
	    
	    Assert.assertTrue(profile.succesSaveMessage.isDisplayed());
	}

	@When("Select {string} from checkbox")
	public void select_from_checkbox(String activity) {
		for(WebElement w: profile.activities) {
	    	if(w.getText().equals(activity)) {
	    		w.click();
	    		break;
	    	}
	    }
		profile.preferencSave.click();
	}
	@When("Attach a file")
	public void attach_a_file() {
	    profile.attachmant.click();
 		String filePath=System.getProperty("user.dir")+"/screenshots/passed/a.png";
		profile.attachmantBrowse.sendKeys(filePath);
	}

}
