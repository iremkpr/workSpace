package steps;

import TestBase.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks {
	@Before
	public void starts() throws InterruptedException {
		BaseClass.setUp();
	}
	@After
	public void ends(Scenario scenario) {
		
		byte[] picture;
		if(scenario.isFailed()) {
			picture = CommonMethods.takeScreenshot("failed/" + scenario.getName());

		}
		else {
			picture = CommonMethods.takeScreenshot("passed/" + scenario.getName());

		}
		
		scenario.attach(picture, "image/png", scenario.getName());

		
		BaseClass.tearDown();
	}
}
