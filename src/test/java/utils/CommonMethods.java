package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import TestBase.PageInitializer;

public class CommonMethods extends PageInitializer {
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
		}
		public static void sendText(String xpath, String text) {
			WebElement element=driver.findElement(By.xpath(xpath));
			element.clear();
			element.sendKeys(text);
		}
		/**
		 * This method checks if a radio/check box is enabled? and then cliks on the element for the input value
		 * 
		 * @param elementList
		 * @param value
		 */
		public static void clickRadioOrCheckbox(List<WebElement> elementList, String value) {
			for(WebElement el:elementList) {
				String elementValue=el.getAttribute("value").trim();
				if(elementValue.equals(value) && el.isEnabled()) { // is it what I want and clickable? checking
					el.click();
					break;//If you find just break and get out of the loop
				}
				
			}
		}
		public static void click(WebElement element) {
			waitForClickability(element);
			element.click();
		}
		/**
		 * This method allows us to call Thread.sleep() for a certain amount of seconds
		 * @param Seconds
		 */
		public static void wait(int Seconds){
			try {
				Thread.sleep(Seconds*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * This method checks whether a visible text is found in a drop-down and select it.
		 * @param element
		 * @param visibleText
		 */
		public static void selectDropdown(WebElement element, String visibleText) {
		
			try {
				Select sel= new Select(element);
				sel.selectByVisibleText(visibleText);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void selectDropdown(WebElement element, int index) {
			
			try {
				Select sel= new Select(element);
				sel.selectByIndex(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static Integer countDropDownOptions(WebElement element) {

			Select sel= new Select(element);
			return sel.getOptions().size();
		
		}
		public static void acceptAlert (){
			try {
				Alert alert= driver.switchTo().alert();
				alert.accept();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
		public static void dismissAlert (){
			try {
				Alert alert= driver.switchTo().alert();
				alert.dismiss();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
		public static String getAlertText() {
			String alertText=null;
			try {
				Alert alert=driver.switchTo().alert();
				alertText=alert.getText();
				
				
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
			return alertText;
		}
		public static void sendAlertText(String text) {
			try {
				Alert alert=driver.switchTo().alert();
				alert.sendKeys(text);
				
				
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}
		/**
		 * This method switching the frame as  id or name
		 * @param nameOrId
		 */
		public static void switchToFrame(String nameOrId) {
			try {
				driver.switchTo().frame(nameOrId);
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		}
		/**
		 * This method switching the frame as index
		 * @param index
		 */
		public static void switchToFrame(int index) {
			try {
				driver.switchTo().frame(index);
			} catch (Exception e) {
				e.printStackTrace();	
				
			}
		}
		public static void switchToFrame(WebElement element) {
			try {
				driver.switchTo().frame(element);
			} catch (NoSuchFrameException e) {
				e.printStackTrace();
			}
		}
		public static void switchToChildWindow() {
			String mainWindow= driver.getWindowHandle();
			Set<String> handles= driver.getWindowHandles();
			for(String handle:handles) {
				if(!mainWindow.equals(handle)) {
					driver.switchTo().window(handle);
					break;
				}
			}
		}
		public static WebDriverWait getWaitObject() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
			return wait;
		}

		/**
		 * Waits for the element to be clickable
		 * 
		 * @param element
		 * @return
		 */
		public static WebElement waitForClickability(WebElement element) {
			return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
		}

		/**
		 * Waits for the element to be visible
		 * 
		 * @param element
		 * @return
		 */
		public static WebElement waitForVisibility(WebElement element) {
			return getWaitObject().until(ExpectedConditions.visibilityOf(element));
		}
		public static JavascriptExecutor getJSObject()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js;
			//in one line 
			//return (JavascriptExecutor) driver;
		}
		public static void jsClick(WebElement element)
		{
			getJSObject().executeScript("arguments[0].click()", element);
		}
		
		/**
		 * This method will scroll the page until a specific element is in view.
		 * 
		 * @param element
		 */
		public static void scrollToElement(WebElement element)
		{
			getJSObject().executeScript("arguments[0].scrollIntoView(true)", element);
		}
		
		public static void  scrollUp(int pixel)
		{
			getJSObject().executeScript("window.scrollBy(0, -"+pixel+")");
		}
		
		/**
		 * This method scrolls the page down based on the pixel parameter.
		 * 
		 * @param pixel
		 */
		public static void scrollDown(int pixel)
		{
			getJSObject().executeScript("window.scrollBy(0,"+pixel+")");
		}
		
		public static void selectCalenderDate(List<WebElement> elements, String date) {
			for(WebElement day: elements) {
				if(day.getText().equals(date)) {
					if(day.isEnabled()) {
						click(day);
						break;
					}
					else {
						System.out.println("Day is not enabled! ");
					}
					
				}
			}
		}
		public static void screenshot() throws IOException {
			TakesScreenshot ts=(TakesScreenshot) driver; // ITS KINDA YOUR CAMERA
			//You took a screenshot but didnt save anywhere

			/**
			 * SAVE YOUR SCREENSHOT 
			 */
			File sourceFile=ts.getScreenshotAs(OutputType.FILE);
			Files.copy(sourceFile, new File("screenshots/HRM/screenshot02.png"));
			
		}
		public static String getTimeStamp() {
			Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

			return sdf.format(date);
		}

		public static byte[] takeScreenshot(String fileName) {

			TakesScreenshot ts = (TakesScreenshot) driver;

			// If you don't want the screenshot to be saved, you can comment this part
			////////////////////////////////
			// Create a file and store it in our computer
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);

			String destination = Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp() + ".png";

			try {
				FileUtils.copyFile(sourceFile, new File(destination));
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
			return picBytes;
		}

}
