package com.sun.pages;


import java.io.File;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.utils.DataTable;

/**
 * Base class for all the pages.
 *
 */
public abstract class Page {
	protected WebDriver browser;
	protected ExtentTest report;
	public IllinoisApplication application;	
	protected DataTable dataTable;	
	protected abstract boolean isValidPage();
	protected abstract void waitForPageLoad();
	public String ReportPath;
	/**
	 * Constructor for Page class 
	 * @param browser
	 * @param report
	 */
	protected Page(WebDriver browser,ExtentTest report,String ReportPath) {
		this.browser=browser;
		this.report=report;
		this.ReportPath = ReportPath;
		PageFactory.initElements(browser, this);
		waitForPageLoad();
		verifyApplicationInCorrectPage();
	}

	/**
	 * Verify Application in Correct Page. 
	 * @param Nil 
	 * @return Nil
	 */	

	private void verifyApplicationInCorrectPage() {
		if (!isValidPage()) {
			String stepName="Navigation to Page";
			String message="The application is not in the expected page , current page: " + 
					browser.getTitle() +" Page.";		
		}
	}

	/**
	 * Check if the element is present in the page
	 * @param element WebElement need to check
	 * @return True if present
	 */
	protected boolean isElementPresent(WebElement element){
		try{
			return element.isDisplayed();			
		}catch(NoSuchElementException ex){
			return false;
		}catch(StaleElementReferenceException ex2){
			return false;
		}
	}


	/**
	 * Check if the element is present in the page
	 * @param element WebElement need to check
	 * @return True if present
	 */
	public boolean isElementPresent(By by){
		try{
			return browser.findElement(by).isDisplayed();
		}catch(NoSuchElementException ex){
			return false;
		}catch(StaleElementReferenceException ex2){
			return false;
		}
	}


	/**
	 * Check if the element is present in the page and report error
	 * @param element
	 * @param errorMsg error message need to report if the element not present
	 */
	protected void isElementPresent(WebElement element,String stepName,String errorMsg) {
		if(!isElementPresent(element)){
			testStepFailed(errorMsg);
		}
	}

	/***
	 * Method to click on a link(WebElement button)
	 * @param : Element Name
	 ***/
	public void clickOn(WebElement we,String elem) {		
		try{
			waitForElement(we);
			if (isElementPresent(we)){
				we.click();				
				testStepPassed("Clicked on WebElement-"+ elem );	
			}}
		catch (Exception ex) {
			testStepFailed("Uanble to click on Element-"+ elem);
		} 
	}

	/**
	 * Method to click on a link(WebElement link)
	 * @param : Element Name
	 */
	protected void jsClick(WebElement we,String elem) {		
		try{			
			((JavascriptExecutor) browser).executeScript("arguments[0].click();",we);
			testStepPassed("Clicked on -"+ elem +"- Element");			
		}catch (RuntimeException ex) {
			testStepFailed("Uanble to click on -"+ elem +"- Element");
		} 
	}

	/***
	 * Method to enter text in a textbox
	 * 
	 * @param : Element Name
	 * @return :
	 ***/
	public void enterText(WebElement we,String text){
		try{
			waitForElement(we);
			if(isElementPresent(we)){
				we.clear();
				we.sendKeys(text);							
			}
		}
		catch (RuntimeException ex) {			
			testStepFailed("Unable to enter text in the text field->"+ text);
		} 
	}

	/***
	 * Method to clear text in a textbox
	 * 
	 * @param : Element Name
	 * @return :
	 ***/
	public void clearText(WebElement we){
		try{
			waitForElement(we);
			if(isElementPresent(we)){
				we.clear();				
			}
		}catch(RuntimeException ex){
			testStepFailed("Unable to clear text in the text field");
		}
	}


	/***
	 * Method to switch to child window
	 * @param : parentWindow
	 ***/
	public void navigateToNewWindow(String pageTitle) {
		boolean blnNavigate=false;
		try{			
			Set<String> handles = browser.getWindowHandles();
			if(handles.size()==1){
				sleep(7000);
				handles = browser.getWindowHandles();
			}
			if(handles.size()>1){
				for (String windowHandle : handles) {					
					String strActTitle = browser.switchTo().window(windowHandle).getTitle();
					if(strActTitle.contains(pageTitle)){
						blnNavigate = true;
						browser.manage().window().maximize();
						sleep(5000);				
						testStepPassed("Navigated to the page -"+pageTitle+"- successfully");	
						break;
					}					
				}
				if(!blnNavigate){
					testStepFailed("Unable to Navigate to the page -"+pageTitle);
				}
			}else{
				testStepFailed("Unable to Navigate to the page -"+pageTitle);
			}
		}
		catch(RuntimeException ex){
			testStepFailed("Unable to Navigate to the page -"+pageTitle);
		}
	}

	/***
	 * Method to switch to parent window
	 * @param : parentWindow
	 ***/
	public void navigatoToParentWindow(String parentWindow) {
		browser.switchTo().window(parentWindow);
	}

	public void jsmoveToElement(WebElement elem){

		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		JavascriptExecutor js = (JavascriptExecutor) browser;
		js.executeScript(mouseOverScript, elem);

	}

	/***
	 * Method to close a webpage
	 * @return      : 
	 ***/
	public void closeCurrentPage(){
		String str=browser.getTitle();
		try {
			browser.close();
			Set<String> windows=browser.getWindowHandles();
			for(String window:windows){
				browser.switchTo().window(window);
			}
			sleep(5000);
			testStepPassed("Closed the current page with title->"+str);
		} catch (Exception e) {
			testStepFailed("Unable to Close the current page with title->"+str);
		}
	}


	//*****************************************************************************************************************//
	//Alert pop ups
	//*****************************************************************************************************************//
	/***
	 * Method to accept and close alert and return the text within the alert
	 * 
	 * @param : 
	 * @return :
	 ***/
	public String closeAlertAndReturnText(){
		String alertMessage=null;
		try{		
			WebDriverWait wait = new WebDriverWait(browser, 40);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = browser.switchTo().alert();
			alertMessage=alert.getText();
			alert.accept();
		}catch(Exception Ex){
			testStepFailed("Exception Caught, Message is->"+Ex.getMessage());
		}
		return alertMessage;
	}



	//*****************************************************************************************************************//
	//waits
	//*****************************************************************************************************************//

	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */
	protected Boolean waitForElement(By by) {
		try {
			new WebDriverWait(browser,30).
			until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */

	protected Boolean waitForElement(WebElement we) {
		try {
			new WebDriverWait(browser, 30).until(ExpectedConditions
					.visibilityOf(we));
			return true;
		} catch (RuntimeException ex) {
			return false;
		}    	
	}

	/**
	 * Method to wait for Alert present in the page
	 * @param 
	 */

	protected Boolean waitForAlert(){
		try{
			new WebDriverWait(browser, 60).until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception Ex){
			return false;
		}
	}


	/***
	 * Method to get current time in minutes
	 * @param : Element Name
	 * @return :
	 * Modified By :
	 ***/
	public int getTimeInMin (String time) {
		//String time=new SimpleDateFormat("HH:mm").format(new Date());
		String[] splitTime=time.split(":");
		int hr=Integer.parseInt(splitTime[0]);
		int mn=Integer.parseInt(splitTime[1].substring(0,2));
		if(hr>12){
			hr=hr-12;
		}
		int timStamp=(hr*60)+mn;
		return timStamp;
	}


	/***
	 * Method to check for an alert for 5 seconds
	 * @param       : Element Name
	 * @return      : 
	 * Modified By  :  
	 ***/

	public boolean isAlertPresent(){
		try{
			WebDriverWait wait = new WebDriverWait(browser, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception e){			
			return false;
		}
	}


	/***
	 * Method to wait for the any of 2 elements to be displayed
	 * @param       : we1,we2
	 * @return      : 
	 * @author      : Prakash Shetty
	 * Modified By  :  
	 ***/


	public boolean waitForAnyElement(WebElement we1,WebElement we2){
		try{
			for(int i=0;i<60;i++){
				if(isElementPresent(we1)||isElementPresent(we2)){
					break;
				}else{
					sleep(1000);
				}
			}
			return true;
		}catch(Exception Ex){
			return false;
		}
	}


	/**
	 * method to make a thread sleep for customized time in milliseconds
	 * @param milliseconds
	 */
	protected void sleep(int milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Method to wait for the any of 2 elements to be displayed
	 * @param       : By,By
	 * @return      : 
	 * @author      : Prakash Shetty
	 * Modified By  :  
	 ***/


	public boolean waitForAnyElement(By we1,By we2){
		try{
			for(int i=0;i<80;i++){
				if(isElementPresent(we1)||isElementPresent(we2)){
					break;
				}else{
					sleep(1000);
				}
			}
			return true;
		}catch(Exception Ex){
			return false;
		}
	}


	/***
	 * Method to hover over an element
	 * @param       : weMainMenuElement,weSubMenuElement
	 * @return      : 
	 * @author      : Prakash Shetty
	 * Modified By  :  
	 ***/

	public void clickOnSubMenu(WebElement weMain,WebElement weSub ){
		String strElem=null;
		try{
			Actions action = new Actions(browser);
			action.moveToElement(weMain).click().perform();
		}catch(Exception Ex){
			testStepFailed("Unable to hover Over main menu Item");
		}
		try{
			waitForElement(weSub);
			strElem = weSub.getText();
			weSub.click();
		}catch(Exception ex){}
		sleep(2000);
		Set<String> handles = browser.getWindowHandles();
		if(handles.size()>1){
			testStepPassed("Clicked on Link Life Insurance successfully");							
		}else{
			jsClick(weSub, strElem);
		}

	}

	/***
	 * Method to Select value from dropdown by visible text
	 * @param       : we,strElemName,strVisibleText
	 * @return      : 
	 * @author      : Prakash Shetty
	 * Modified By  :  
	 ***/

	public void selectByVisisbleText(WebElement we,String strElemName,String strVisibleText){
		try{
			Select sel = new Select( we);
			sel.selectByVisibleText(strVisibleText);
			testStepPassed("selected value -"+strVisibleText +" from dropdown->"+strElemName);
		}catch(Exception Ex){
			testStepFailed("Unable to select value from the dropdown "+Ex.getMessage());
		}
	}



	public void selectByVisisbleValue(WebElement we,String strElemName,String strVisibleValue){
		try{
			Select sel = new Select( we);
			sel.selectByValue(strVisibleValue);
			testStepPassed("selected value -"+strVisibleValue +" from dropdown->"+strElemName);
		}catch(Exception Ex){
			testStepFailed("Unable to select value from the dropdown "+Ex.getMessage());
		}
	}




	//*********************** Extent Report **************************************//

	public void testReporter(String color, String reportDesc){	    

		switch (color.toLowerCase()){
		case "green": report.log(LogStatus.PASS,"<font color=green>" + reportDesc + "</font><br/>");break;
		case "blue":  report.log(LogStatus.INFO,"<font color=blue>" + reportDesc + "</font><br/>");break;
		case "orange":  report.log(LogStatus.WARNING,"<font color=orange>" +  reportDesc + "</font><br/>");break;
		case "red":  report.log(LogStatus.FAIL,"<font color=red>" + reportDesc + "</font><br/>");break;
		case "white":  report.log(LogStatus.INFO,reportDesc);break;
		}

	}

	public void testStepPassed(String errMessage){	   
		testReporter("Green", errMessage);
	}

	public  void testStepInfo(String errMessage){		  
		testReporter("Blue", errMessage);
	}


	public void embedScreenshot(String colour, String pathAndFile){   
		report.log(LogStatus.INFO, "Manual Verification Point: " + report.addScreenCapture(pathAndFile+ ".png"));    
	}


	public void takeScreenshot(String strFileName){	            		  
		Random rn = new Random();
		int result = rn.nextInt(100 - 1 + 1) + 1;		  
		strFileName = strFileName + result;
		captureScreenShot(strFileName);
		embedScreenshot("orange", "./Screenshots" + "/" + strFileName);
	}


	public void captureScreenShot(String filename){		  
		File scrFile = null;
		String scrPath = ReportPath + "/Screenshots";
		File file = new File(scrPath);
		file.mkdir();
		try{	      
			scrFile = (File) ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(scrPath + "/" + filename + ".png"));
		}catch (Exception e){
			testReporter("Red", e.toString()); return;

		}finally{
			if (scrFile == null) {
				System.out.println("This WebDriver does not support screenshots");
				return;
			}
		}
	}

	public void testStepFailed(String errMessage){			  		 		  
		Random rn = new Random();
		int failureNo = rn.nextInt(100 - 1 + 1) + 1;		  		  		  
		testReporter("Red", errMessage);		  
		captureScreenShot("TestFailure" + failureNo);

		String pathAndFile = "./Screenshots/TestFailure" + failureNo+ ".png";
		report.log(LogStatus.FAIL, "Check ScreenShot Below: " + report.addScreenCapture(pathAndFile));	   
	}
}
