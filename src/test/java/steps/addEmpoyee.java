package steps;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

 import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigsReader;
import utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import org.apache.commons.io.FileUtils;
import com.google.common.io.Files;

public class addEmpoyee extends CommonMethods {
	@Given("Login to the HRM site")
	public void login_to_the_hrm_site() {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		login.uName.sendKeys(ConfigsReader.getProperty("username"));
		login.password.sendKeys(ConfigsReader.getProperty("password"));
		login.loginbutton.click();
		waitForVisibility(dash.accountName);

	}

	@Given("Open the Add Employee page")
	public void open_the_add_employee_page() {
		dash.pim.click();
		dash.addEmp.click();
		waitForVisibility(addEmp.fName);

	}

	@When("Fill the valid firstName {string} lastName  {string} and Location {string}")
	public void fill_the_valid_first_name_last_name_and_location(String firstName, String lastName, String location) {
		// Write code here that turns the phrase above into concrete actions
		addEmp.fName.sendKeys(firstName);
		addEmp.lName.sendKeys(lastName);
		Select select = new Select(addEmp.locations);
		select.selectByVisibleText(location);

	}

	@When("Click the save button")
	public void click_the_save_button() {
		// Write code here that turns the phrase above into concrete actions
		addEmp.save.click();
	}

	@Then("validate user {string} added Succesfully")
	public void validate_user_added_succesfully(String fullName) {
		waitForVisibility(profile.fullName);
		assertTrue(profile.fullName.getText().equals(fullName), "User fullname didnt matched!");
	}

	@When("Fill the valid {string} {string}   and {string}")
	public void fill_the_valid_and(String firstName, String lastName, String location) {
		// Write code here that turns the phrase above into concrete actions
		addEmp.fName.sendKeys(firstName);
		addEmp.lName.sendKeys(lastName);
		Select select = new Select(addEmp.locations);
		select.selectByVisibleText(location);
	}

	@When("Click the Create Login Details button")
	public void click_the_create_login_details_button() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", addEmp.loginDetails);
		waitForVisibility(addEmp.username);
	}

	@When("Fill username {string} password {string}")
	public void fill_username_password(String username, String password) {
		addEmp.username.sendKeys(username);
		addEmp.password.sendKeys(password);
		addEmp.confirmPassword.sendKeys(password);

	}

	@Then("validate the user {string} added succesfully")
	public void validate_the_user_added_succesfully(String fullName) {
		// Write code here that turns the phrase above into concrete actions
		waitForVisibility(profile.fullName);
		assertTrue(profile.fullName.getText().equals(fullName), "User fullname didnt matched!");
	}

	@When("Delete existing id")
	public void delete_existing_id() {
		// Write code here that turns the phrase above into concrete actions
		addEmp.employeeId.clear();
	}

	@When("Fill valid firstName lastName and Location")
	public void fill_valid_first_name_last_name_and_location(io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> listOfMap = dataTable.asMaps();
		for (Map<String, String> m : listOfMap) {
			String firstName = m.get("firstName");
			String lastName = m.get("lastName");
			String location = m.get("Location");
			addEmp.fName.sendKeys(firstName);
			addEmp.lName.sendKeys(lastName);
			Select locs = new Select(addEmp.locations);
			locs.selectByVisibleText(location);
		}
	}

	@Then("validate the user added Succesfully")
	public void validate_the_user_added_succesfully(io.cucumber.datatable.DataTable dataTable) {
		waitForVisibility(profile.fullName);

		List<Map<String, String>> listOfMaps = dataTable.asMaps();
		for (Map<String, String> m : listOfMaps) {
			String uName = m.get("userName");
			assertTrue(profile.fullName.getText().equalsIgnoreCase(uName));
		}
	}

	@When("I add the valid datas from {string} excel sheet")
	public void i_add_the_valid_datas_from_excel_sheet(String sheetName) throws IOException, InterruptedException {
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata/last.xlsx";
		FileInputStream fis = new FileInputStream(path);
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheet = book.getSheet(sheetName);

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		for (int row = 1; row < rowCount; row++) {
			Map<String, String> m = new LinkedHashMap<>();
			for (int col = 0; col < colCount; col++) {
				String key = sheet.getRow(0).getCell(col).toString();
				String value = sheet.getRow(row).getCell(col).toString();
				m.put(key, value);
			}
			listOfMaps.add(m);
		}

		for (Map<String, String> m : listOfMaps) {

			addEmp.fName.sendKeys(m.get("FirstName"));
			addEmp.lName.sendKeys(m.get("LastName"));
			Select select = new Select(addEmp.locations);
			select.selectByVisibleText("Canadian Development Center");
			addEmp.save.click();

			waitForVisibility(profile.fullName);
			String fulName = m.get("FirstName") + " " + m.get("LastName");
			Assert.assertTrue(profile.fullName.getText().equals(fulName));

			dash.addEmp.click();
			waitForVisibility(addEmp.fName);
		}

		book.close();

	}

	@When("I add the valid datas via  {string} excel sheet")
	public void i_add_the_valid_datas_via_excel_sheet(String sheetName) throws IOException, InterruptedException {
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata/last.xlsx";
		FileInputStream fis = new FileInputStream(path);
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheet = book.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		List<Map<String, String>> listOfMaps = new ArrayList<>();

		for (int row = 1; row < rowCount; row++) {
			Map<String, String> map = new LinkedHashMap<>();
			for (int col = 0; col < colCount; col++) {
				String key = sheet.getRow(0).getCell(col).toString();
				String value = sheet.getRow(row).getCell(col).toString();
				map.put(key, value);
			}
			listOfMaps.add(map);
		}

		for (Map<String, String> m : listOfMaps) {
			addEmp.fName.sendKeys(m.get("FirstName"));
			addEmp.lName.sendKeys(m.get("LastName"));
			Select select = new Select(addEmp.locations);
			select.selectByVisibleText("Canadian Development Center");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", addEmp.loginDetails);

			addEmp.username.sendKeys(m.get("Username"));
			addEmp.password.sendKeys(m.get("Password"));
			addEmp.confirmPassword.sendKeys(m.get("Password"));
			Thread.sleep(1000);
			addEmp.save.click();
			waitForVisibility(profile.fullName);

			dash.addEmp.click();
			waitForVisibility(addEmp.fName);
		}

		book.close();

	}

	@When("Click user profile picture icon and update the picture")
	public void click_user_profile_picture_icon_and_update_the_picture() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(profile.photoIcon));
		profile.photoIcon.click();
		wait.until(ExpectedConditions.visibilityOf(profile.photoAdd));
		String filePath = System.getProperty("user.dir") + "/screenshots/failed/pp.png";
		profile.photoAddInput.sendKeys(filePath);
	}

	@Then("Validate that the user profile photo updated")
	public void validate_that_the_user_profile_photo_updated() {

	}

	@Test
	public static void irmikella0() {
		/*
		 * Amazon Page Title Verification: Open chrome browser Go to
		 * “https://www.amazon.com/”<title>Amazon.com. Spend less. Smile more.</title>
		 * Verify Title “Amazon.com: Online Shopping for Electronics, Apparel,
		 * Computers, Books, DVDs & more” is displayed End the Session (close the
		 * browser).
		 */
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Amazon.com. Spend less. Smile more"));
		driver.quit();
	}

	@Test
	public static void testSel() {
		/*
		 * Opening Page on Firefox Browser Open Firefox browser Navigate to
		 * “https://www.redfin.com/” Verify that you have navigated to
		 * (https://www.redfin.com/) End the Session (close the browser).
		 */
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.redfin.com/");
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("https://www.redfin.com/"));
		driver.quit();
	}

	@Test
	public static void new2() {
		/*
		 * Open chrome browser Go to "https://demo.guru99.com/test/newtours/" Click on
		 * Register Link Fill out ALL required info Click Submit User successfully
		 * registered (Create the script using different locators)
		 */
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/");
		WebElement register = driver.findElement(By.xpath("//a[text()='REGISTER']"));
		register.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
		WebElement name = driver.findElement(By.xpath("//input[@name='firstName']"));
		name.sendKeys("irem");

		WebElement lastN = driver.findElement(By.xpath("//input[@name='lastName']"));
		lastN.sendKeys("Gumus");

		WebElement phone = driver.findElement(By.xpath("//input[@name='phone']"));
		phone.sendKeys("1234567");

		driver.findElement(By.xpath("//input[@name='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' sign-in ']")));
		WebElement signedUp = driver.findElement(By.xpath("//a[text()=' sign-in ']"));

		Assert.assertTrue(signedUp.isDisplayed());
	}

	@Test
	public static void facelogin() {
		/*
		 * Open chrome browser Go to "https://www.facebook.com/" Click on Sign Up link
		 * Enter first name Enter last name Enter email address Re-enter email address
		 * Click on Sign Up button
		 */

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("irem.koprek.7");
		WebElement password = driver.findElement(By.id("pass"));
		password.sendKeys("irmikella01*");
		WebElement login = driver.findElement(By.xpath("//button[@name='login']"));
		login.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Friends']")));
		WebElement element = driver.findElement(By.xpath("//span[text()='Friends']"));
		Assert.assertTrue(element.isDisplayed());
		driver.quit();
	}

	@Test
	public static void signingUp() {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		driver.findElement(By.name("firstname")).sendKeys("Elif");
		driver.findElement(By.name("lastname")).sendKeys("Polat");

		Select select = new Select(driver.findElement(By.id("month")));
		select.selectByVisibleText("Nov");
		Select select2 = new Select(driver.findElement(By.id("day")));
		select2.selectByVisibleText("2");
		Select select3 = new Select(driver.findElement(By.id("year")));
		select3.selectByVisibleText("1997");

		List<WebElement> genders = driver.findElements(By.xpath("//span[@data-name='gender_wrapper']//label"));
		for (WebElement g : genders) {
			if (g.getText().equals("Female")) {
				g.click();
				break;
			}
		}
		driver.findElement(By.name("reg_email__")).sendKeys("crazyboy_040@gmail.com");
		driver.findElement(By.name("reg_passwd__")).sendKeys("iremkop19Aa*");
		driver.findElement(By.name("websubmit")).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1));
		fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("code_in_cliff")));
		Assert.assertTrue(driver.findElement(By.id("code_in_cliff")).isDisplayed());
	}

	@Test
	public static void loginTest2() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		WebElement forgetPassword = driver.findElement(By.xpath("//a[text()='Forgot password?']"));
		forgetPassword.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identify_email")));
		Assert.assertTrue(driver.findElement(By.id("identify_email")).isDisplayed());
		driver.findElement(By.id("identify_email")).sendKeys("iremk9nyc@gmail.com");
		driver.findElement(By.id("did_submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='No Search Results']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='No Search Results']")).isDisplayed());
		driver.quit();
	}

	@Test
	public static void hw42024() {
		/*
		 * Using xPath ONLY Open chrome browser Go to
		 * "http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx"
		 * Enter valid username Clear username and enter again valid username Leave
		 * password field empty Click on login button Verify error message with text
		 * "Invalid Login or Password." is displayed.
		 */

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");

		WebElement uName = driver.findElement(By.id("ctl00_MainContent_username"));
		uName.sendKeys("Tester");

		WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
		password.sendKeys("test");

		WebElement login = driver.findElement(By.id("ctl00_MainContent_login_button"));
		login.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Web Orders']")));
		Assert.assertTrue(driver.findElement(By.id("ctl00_logout")).isDisplayed());
	}

	/*
	 * Radio Button Practice Open chrome browser Go to
	 * "https://demoqa.com/radio-button" Verify if all radio buttons are displayed
	 * and clickable Click on all radio buttons Verify radio buttons has been
	 * clicked successfully Quit browser
	 */
	@Test
	public static void radio() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/radio-button");
		List<WebElement> radioElements = driver.findElements(By.xpath("//div[@id='app']//input"));
		List<WebElement> labelElements = driver.findElements(By.xpath("//div[@id='app']//label"));
		String select = "noRadio";
		String selected = "No";
		for (int i = 0; i < radioElements.size(); i++) {
			WebElement rLabel = labelElements.get(i);
			String value = rLabel.getAttribute("for");

			if (value.equals(select) && radioElements.get(i).isEnabled()) {
				rLabel.click();
				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You have selected ']//span")).getText()
						.equals(selected));
				break;

			}

		}

	}

	/*
	 * Radio Button Practice Open chrome browser Go to
	 * "https://demoqa.com/radio-button" Verify if all radio buttons are displayed
	 * and clickable Click on all radio buttons Verify radio buttons has been
	 * clicked successfully Quit browser
	 */

	@Test
	public static void radios2() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/radio-button");
		List<WebElement> radioInputs = driver.findElements(By.xpath("//div[@id='app']//input"));
		List<WebElement> radioLabels = driver.findElements(By.xpath("//div[@id='app']//label"));
		for (int i = 0; i < radioInputs.size(); i++) {
			String clicked = radioLabels.get(i).getText();
			if (radioInputs.get(i).isEnabled() && radioLabels.get(i).isDisplayed()) {
				radioLabels.get(i).click();
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//p//span")).getText().equals(clicked));
			}
		}
	}

	@Test
	public static void niwe() {
		/*
		 * Open Chrome browser Go to https://hrm.neotechacademy.com/ Enter valid
		 * username and password Click on login button Then verify that
		 * "span with id account-name" has the text "Jacqueline White". Quit the browser
		 */
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://hrm.neotechacademy.com/");
		WebElement uName = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));
		WebElement loginButtn = driver.findElement(By.xpath("//button[@type='submit']"));
		uName.sendKeys("Admin");
		password.sendKeys("Neotech@123");
		loginButtn.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement accountName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='account-name']")));
		Assert.assertTrue(accountName.getText().equals("Jacqueline White"));
		driver.quit();
	}

	@Test
	public static void invalidLog() {
		/*
		 * Open Chrome browser Go to https://hrm.neotechacademy.com/ Enter valid
		 * username Leave password field empty Verify error message with text
		 * "Password cannot be empty" is displayed
		 */

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://hrm.neotechacademy.com/");
		WebElement uName = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));
		WebElement loginButtn = driver.findElement(By.xpath("//button[@type='submit']"));
		uName.sendKeys("Admin");
		password.sendKeys("Neotech@12");
		loginButtn.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement toastMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-message']")));
		Assert.assertTrue(toastMessage.getText().contains("Invalid Credentials"));
	}
	/*
	 * Amazon Department List Verification Open chrome browser Go to
	 * "http://amazon.com/" Verify how many department options available. Print each
	 * department Select Computers Quit browser
	 */

	@Test
	public static void newTestAMaz() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.com/");
		Select select = new Select(driver.findElement(By.id("searchDropdownBox")));
		List<WebElement> opts = select.getOptions();
		System.out.println("how many department options available=" + opts.size());

		for (WebElement e : opts) {
			System.out.println(e.getText());
		}
		select.selectByVisibleText("Computers");
		driver.quit();
	}

	/*
	 * Alert text verification Open chrome browser Go to https://demoqa.com/ Click
	 * on "Alerts, Frame & Windows" link Click on "Alerts" links on the left side
	 * Click on button to see Alert Verify alert box with text
	 * "You clicked a button" is present Click on 3rd button in the page Verify
	 * alert box with text "Do you confirm action?" is present and click "confirm"
	 * Click on 4 th button in the page and enter your name and click ok. Quit
	 * browser
	 */

	@Test
	public static void alertinBo() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//div[@class='category-cards']/div[3]")));
		WebElement buttonAlert = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='category-cards']/div[3]")));
		buttonAlert.click();
		driver.findElement(By.xpath("//span[text()='Alerts']")).click();

		driver.findElement(By.xpath("//button[@id='alertButton']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.id("timerAlertButton")));
		driver.findElement(By.id("timerAlertButton")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();

		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.id("confirmButton")));
		driver.findElement(By.id("confirmButton")).click();
		alert.accept();
		Assert.assertTrue(driver.findElement(By.id("confirmResult")).getText().contains("Ok"));

		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.id("promtButton")));
		driver.findElement(By.id("promtButton")).click();
		alert.sendKeys("neee");
		alert.accept();
		Assert.assertTrue(driver.findElement(By.id("promptResult")).getText().contains("neee"));
		driver.quit();
	}

	/*
	 * Open chrome browser Go to "https://semantic-ui.com/modules/dropdown.html"
	 * Scroll down to "Multiple Selection" Click on drop down and select "CSS" and
	 * "HTML" Verify value has been selected Deselect 1 of the options from the
	 * dropdown Quit browser
	 */

	@Test
	public static void challeng() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://semantic-ui.com/modules/dropdown.html");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//h4[text()='Multiple Selection']")));
		driver.findElement(By.xpath("//a[@id='multiple-selection']/..//i[@class='dropdown icon']")).click();

		List<WebElement> optsElements = driver.findElements(By.xpath("//div[@class='menu transition visible']"));
		for (WebElement e : optsElements) {
			String value = e.getText();
			if (value.equals("HTML")) {
				e.click();
				Assert.assertTrue(driver.findElement(By.xpath("//a[1][@class='ui label transition visible']")).getText()
						.equals("HTML"));
			}
			if (value.equals("CSS")) {
				e.click();
				Assert.assertTrue(driver.findElement(By.xpath("//a[2][@class='ui label transition visible']")).getText()
						.equals("CSS"));

			}
		}

		driver.quit();
	}

	@Test
	public static void windowss() throws InterruptedException {
		/*
		 * 1) Launch the browser and open the site "http://demo.guru99.com/popup.php" 2)
		 * Click on link "Click Here". When the user clicks on the "Click Here" link,
		 * new child window opens. 3) On the Child Window, Enter your email ID and
		 * submit. 4) Then Access Credentials will be Displayed on submitted page. You
		 * will see the child window is open in new tab. 5) Close the Child window on
		 * which credentials are displayed.Switch to the parent window. 6) Quit all
		 * browsers
		 */
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/popup.php");

		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		String mainW = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String s : windows) {
			if (!s.equals(mainW)) {
				driver.switchTo().window(s);
			}
		}
		driver.findElement(By.name("emailid")).sendKeys("neotech12@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Access details to demo site.']")).isDisplayed());
		driver.close();
		driver.switchTo().window(mainW);
		Thread.sleep(3000);
		driver.quit();
	}

	/*
	 * 1) Launch the browser and open the site
	 * "https://neotech.vercel.app/iframe/multi2" 2) Verify on the page header
	 * "Not a Friendly Topic" displayed 3) Click on the Inner Frame Check box 4)
	 * Choose Dog from Animals dropdown 5) Quit the browser
	 */
	@Test
	public static void newChal() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://neotech.vercel.app/iframe/multi2");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Not a Friendly Topic']")).isDisplayed());
		driver.switchTo().frame("nested1");
		driver.switchTo().frame("child-frame");
		driver.findElement(By.id("inner-frame-check-box")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("animals");

		Select select = new Select(driver.findElement(By.id("animal-select")));
		select.selectByVisibleText("Dog");

		driver.quit();

	}

	/*
	 * Open chrome browser Go to "https://neotech.vercel.app/" Click on the
	 * "Interactions" link Then click on "Waits" Click on enable button Enter
	 * "Hello" and verify text is entered successfully Close the browser
	 */
	@Test
	public static void vercelAp() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://neotech.vercel.app/");
		driver.findElement(By.xpath("//div[@id='menu-interactions']")).click();
		driver.findElement(By.xpath("//a[@id='waits']//div[@role='button']")).click();
		// WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1));

		WebElement enable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnEnable")));
		if (enable.isEnabled()) {
			enable.click();
		}

		WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form//input")));
		if (inputElement.isEnabled()) {
			inputElement.sendKeys("Hello");
		}
		Thread.sleep(4000);
		driver.quit();
	}

	/*
	 * Add Employee Open chrome browser Go to "https://hrm.neotechacademy.com/"
	 * Login into the application Click on PIM > Add Employee Add Employee Log out
	 * Quit the browser
	 */

	@Test
	public static void addNews() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://hrm.neotechacademy.com/");
		WebElement uName = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));
		WebElement loginButtn = driver.findElement(By.xpath("//button[@type='submit']"));
		uName.sendKeys("Admin");
		password.sendKeys("Neotech@123");
		loginButtn.click();
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		driver.findElement(By.id("menu_pim_addEmployee")).click();

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1));
		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name-box")));
		name.sendKeys("Yezid0");
		driver.findElement(By.id("last-name-box")).sendKeys("LAdsD");
		Select select = new Select(driver.findElement(By.xpath("//select[@id='location']")));
		select.selectByVisibleText("France Regional HQ");
		driver.findElement(By.id("modal-save-button")).click();
		WebElement employeeName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("pim.navbar.employeeName")));
		Assert.assertTrue(employeeName.isDisplayed());

	}

	/*
	 * TC: Update Customer Information
	 * 
	 * 1) Open chrome browser 2) Go to
	 * "http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx"
	 * 3) Login to the application 4) Verify customer "Susan McLaren" is present in
	 * the table 5) Click on customer details 6) Update customers last name and
	 * credit card info 7) Verify updated customers name and credit card number is
	 * displayed in table 8) Close browser
	 **/

	@Test
	public static void newUpdate() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");

		WebElement uName = driver.findElement(By.id("ctl00_MainContent_username"));
		uName.sendKeys("Tester");

		WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
		password.sendKeys("test");
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1));
		WebElement login = driver.findElement(By.id("ctl00_MainContent_login_button"));
		login.click();

		List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//table[@class='SampleTable']//tr"))));
		;

		for (int i = 1; i < rows.size(); i++) {
			Thread.sleep(1000);
			String name = rows.get(i).findElement(By.xpath(".//td[2]")).getText();
			if (name.equals("Susan McLaren")) {
				Assert.assertTrue(name.equals("Susan McLaren"));
				WebElement editElement = wait
						.until(ExpectedConditions.elementToBeClickable(rows.get(i).findElement(By.xpath(".//td[13]"))));
				editElement.click();
				break;
			}
		}

		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_MainContent_fmwOrder_txtName")));
		name.clear();
		name.sendKeys("Susan Bob4");
		WebElement cCard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_MainContent_fmwOrder_TextBox6")));
		cCard.clear();
		cCard.sendKeys("991222114000");
		driver.findElement(By.xpath("//label[@for='ctl00_MainContent_fmwOrder_cardList_1']")).click();

		WebElement update = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_MainContent_fmwOrder_UpdateButton")));
		update.click();
 
 		 wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//table[@class='SampleTable']//tr//td[2]"))));
 		 
 		List<WebElement> updatedRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//table[@class='SampleTable']//tr"))));

 		 for(int i=1;i<rows.size();i++) {
 			 String updatedName=updatedRows.get(i).findElement(By.xpath(".//td[2]")).getText();
 			 if(updatedName.contains("Susan")) {
 				 Assert.assertTrue(updatedName.equals("Susan Bob4"));
 			 }
 		 }
 		 
	}

	
	@Test
	public static void deletenEWtEST() {
		
		/*Open chrome browser
2) Go to "http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx"
3) Login to the application
4) Click on the checkbox of all orders with product FamilyAlbum
5) Delete Selected orders
6) Verify the orders have been deleted
7) Quit the browser*/

		//Driver setup
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
		
		
		//LOGIN
		WebElement uName = driver.findElement(By.id("ctl00_MainContent_username"));
		uName.sendKeys("Tester");
		WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
		password.sendKeys("test");
		WebElement login = driver.findElement(By.id("ctl00_MainContent_login_button"));
		login.click();
		
		
		List<WebElement> rows=driver.findElements(By.xpath("//table[@class='SampleTable']//tr"));
		
		for(int i=1;i<rows.size();i++) {
			String productOfRow=rows.get(i).findElement(By.xpath(".//td[3]")).getText();
			if(productOfRow.equals("FamilyAlbum")) {
				WebElement checkbox=rows.get(i).findElement(By.xpath(".//input[@type='checkbox']"));
				checkbox.click();
			}
		}
		
		
		driver.findElement(By.name("ctl00$MainContent$btnDelete")).click();
		
	
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		List<WebElement> products=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='SampleTable']//tr//td[3]")));
		
		for(WebElement p:products) {
 			Assert.assertTrue(!p.getText().equals("FamilyAlbum"));
		}
		driver.quit();
		
	}
	
	
	//div[@class='row-form aa-flightSearchForm-datesRow']//button[@class='ui-datepicker-trigger']
	@Test
	public static void airlines() {
		/*
	    Open chrome browser
		Go to https://www.aa.com/homePage.do
		Enter From and To
		Select departure as Nov	 14 of 2025
		Select arrival as Nov 22 of 2025
		Click on search
		Close browser*/
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.aa.com/homePage.do");
		
		driver.findElement(By.xpath("//div[@class='row-form normalLayout']//input[@id='reservationFlightSearchForm.originAirport']")).sendKeys("NYC");
		driver.findElement(By.xpath("//div[@class='row-form normalLayout']//input[@id='reservationFlightSearchForm.destinationAirport']")).sendKeys("MIA");
	
		 
		JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//div[@class='row-form aa-flightSearchForm-datesRow']/div[1]//button")));
		driver.findElement(By.xpath("//div[@class='row-form aa-flightSearchForm-datesRow']/div[1]//button")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		
		WebElement month=wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//span[@class='ui-datepicker-month']"))));
		
		while(!month.getText().equals("November")) {
		//next month icon
			driver.findElement(By.xpath("//a[@aria-label='Next Month']")).click();
			month=driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//span[@class='ui-datepicker-month']"));
		}
		
		List<WebElement> days=driver.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//table[@class='ui-datepicker-calendar']//tr//td//a"));
		for(WebElement d:days) {
			if(d.getText().equals("14")) {
				d.click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//div[@class='row-form aa-flightSearchForm-datesRow']/div[2]//button")).click();
		WebElement returnMonth=wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//span[@class='ui-datepicker-month']"))));
		while(!returnMonth.getText().equals("November")) {
			//next month icon
				driver.findElement(By.xpath("//a[@aria-label='Next Month']")).click();
				month=driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//span[@class='ui-datepicker-month']"));
		}
		 
		List<WebElement> Returndays=driver.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//table[@class='ui-datepicker-calendar']//tr//td//a"));
		for(WebElement d:Returndays) {
			if(d.getText().equals("22")) {
				d.click();
				break;
			}
		}
	}
	
	
	/*Open chrome browser
Go to https://demo.guru99.com/test/drag_drop.html
Drag the following blocks in different ways
Drag BANK to DEBIT SIDE
Drag SALES to CREDIT SIDE
Drag 5000 to DEBIT SIDE
Drag 5000 to CREDIT SIDE
Close the browser
*/
	
	@Test
	public static void actIngen(){
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.guru99.com/test/drag_drop.html");
		
		driver.manage().window().maximize();
		List<WebElement> buttons=driver.findElements(By.xpath("//div[@id='products']//ul//a"));
		Actions actions=new Actions(driver);
		
		int i=1;
		for(WebElement e:buttons) {
			
			String button=e.getText();
			if(button.equals("BANK")) {
				actions.dragAndDrop(e, driver.findElement(By.xpath("//ol[@id='bank']//li"))).perform();
			}
			else if(button.equals("SALES")) {
				actions.dragAndDrop(e, driver.findElement(By.xpath("//ol[@id='loan']//li"))).perform();

			}
			else if(button.equals("5000")){
				if(i==1) {
					actions.dragAndDrop(e, driver.findElement(By.xpath("//ol[@id='amt7']//li"))).perform();
					i++;
				}
				else {
					actions.dragAndDrop(e,driver.findElement(By.xpath("//ol[@id='amt8']//li"))).perform();
				}
			}
			
		}
		driver.quit();
		
	}
	/*Go to https://hrm.neotechacademy.com/
Log in using our custom methods
Go to PIM menu
Add an employee
Go to Employee List
Get the list of the employees (Using tables - tr and td) 
Loop to search for the employee you added
When you find the employee - click on it.
Take a screenshot*/
	
	@Test
	public static void skrinshat() throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://hrm.neotechacademy.com/");
	
		
		WebElement uName = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));
		WebElement loginButtn = driver.findElement(By.xpath("//button[@type='submit']"));
		uName.sendKeys("Admin");
		password.sendKeys("Neotech@123");
		loginButtn.click();
		
		driver.findElement(By.xpath("//li[@id='menu_pim_viewPimModule']")).click();
		driver.findElement(By.xpath("//span[text()='Employee List']")).click();
	
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		List<WebElement> names= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='employeeListTable']//tr//td[3]")));
		FluentWait<WebDriver> wait2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(1));

		
		for(WebElement n:names) {
			if(n.getText().equalsIgnoreCase("Odis Adalwin")) {
 				n.click();
 				
 				break;
				
			}
		}
 
	  	wait2.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@id='firstName']"))));

		TakesScreenshot ss=(TakesScreenshot) driver;
		File source=ss.getScreenshotAs(OutputType.FILE);
		String destination = Constants.SCREENSHOT_FILEPATH + source + getTimeStamp() + ".png";
		FileUtils.copyFile(source, new File(destination));

		
		
		
		driver.quit();
	}
	
	/*Upload a file:
http://webdriveruniversity.com/File-Upload/index.html

*/
	@Test
	public static void newFile() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://webdriveruniversity.com/File-Upload/index.html");
		
		String filePath=System.getProperty("user.dir")+"/screenshots/s.png";
		driver.findElement(By.id("myFile")).sendKeys(filePath);
		driver.findElement(By.id("submit-button")).click();
		Alert alert=driver.switchTo().alert();
		alert.accept();
		driver.quit();
	}
	
	
	
}
 