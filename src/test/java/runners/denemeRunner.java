package runners;

import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
		
		features="features path",
		glue="steps Path",
		dryRun = true,
		tags="",
 		plugin = "report plugins",
		monochrome=true

		
		
		)

public class denemeRunner {

}
