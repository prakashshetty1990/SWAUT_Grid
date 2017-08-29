package com.sun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.sun.utils.DataTable;

/**
 * Represents default page of the application
 * 
 */
public class IllinoisLifeInsurePage extends Page {



	@FindBy(xpath="//*[@id='LICustomerHeader1_btnQuote']")
	private WebElement btnGetQuote;

	@FindBy(id="rptPNT20YDiv")
	private WebElement btnPreferredNonTobacco20;

	@FindBy(id="rptPNT30YDiv")
	private WebElement btnPreferredNonTobacco30;

	@FindBy(id="rptSNT20YDiv")
	private WebElement btnStandardNonTobacco20;

	@FindBy(id="rptSNT30YDiv")
	private WebElement btnStandardNonTobacco30;

	@FindBy(id="rptST20YDiv")
	private WebElement btnStandardTobacco20;

	@FindBy(id="rptST30YDiv")
	private WebElement btnStandardTobacco30;

	@FindBy(xpath="//*[@id='LICustomerHeader1_stateDrpDwn']")
	private WebElement weState;

	@FindBy(xpath="//*[@id='LICustomerHeader1_txtAge']")
	private WebElement weAge;

	@FindBy(xpath="//*[@id='LICustomerHeader1_protectionSlnDrpDwn']")
	private WebElement weProtectionSoln;

	@FindBy(xpath="//*[@id='LICustomerHeader1_txtProtectAmt']")
	private WebElement weProtectionAmount;

	@FindBy(xpath="//*[@id='LICustomerHeader1_rdoBtnGender_0']")
	private WebElement rbMale;

	@FindBy(xpath="//*[@id='LICustomerHeader1_rdoBtnGender_1']")
	private WebElement rbFemale;

	@FindBy(xpath="//*[@id='LICustomerHeader1_rdoBtnNicotineYesNo_0']")
	private WebElement rbNicotineNo;

	@FindBy(xpath="//*[@id='LICustomerHeader1_rdoBtnNicotineYesNo_1']")
	private WebElement rbNicotineYes;

	@FindBy(xpath="//*[@id='LICustomerHeader1_btnQuote']")
	private WebElement btnGetMyQoute;

	@FindBy(id="issueAgeInfoDiv")
	private WebElement weAgeIssue;

	@FindBy(xpath="//*[@id='rptTable']")
	private WebElement tblROPterm; 


	protected static String HOME_PAGE_TITLE = "Your 5G QUOTE Information";

	public IllinoisLifeInsurePage(WebDriver browser, ExtentTest report,String ReportPath) {
		super(browser, report, ReportPath);		
	}

	@Override
	protected boolean isValidPage() {
		if (browser.getTitle().trim().contains(HOME_PAGE_TITLE)) {
			return true;
		}
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		try{
			new WebDriverWait(browser,60).
			until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='LICustomerHeader1_btnQuote']")));				
		}catch(Exception e){
			System.out.println(e.getMessage());
			testStepFailed("Home Page is not displayed");
		}
	}


	public void getMyQuote(DataTable dataTable){	
		Select sel = new Select( weState);
		sel.selectByVisibleText(dataTable.retrieve("State"));

		testStepInfo("Selected State as ->"+dataTable.retrieve("State"));

		enterText(weAge,dataTable.retrieve("Age"));

		testStepInfo("Entered Age as ->"+dataTable.retrieve("Age"));

		Select sel1 = new Select( weProtectionSoln);		
		sel1.selectByVisibleText(dataTable.retrieve("ProtectionSol"));

		testStepInfo("Selected Protection Solution as ->"+dataTable.retrieve("ProtectionSol"));

		enterText(weProtectionAmount,dataTable.retrieve("ProtectionAmount"));  

		testStepInfo("Entered Protection Amount as ->"+dataTable.retrieve("ProtectionAmount"));

		if(dataTable.retrieve("Gender").equals("Male")){			
			clickOn(rbMale,"Male Radio Button");			
		}else{			
			clickOn(rbFemale,"FeMale Radio Button");			
		}

		testStepInfo("Selected Gender as->"+dataTable.retrieve("Gender"));

		if(dataTable.retrieve("TobaccoUse").contains("No")){			
			clickOn(rbNicotineNo,"Tobacco Use No");			
		}else{			
			clickOn(rbNicotineYes,"Tobacco Use Yes");			
		}

		testStepInfo("Selected Tobacco Use as ->"+dataTable.retrieve("TobaccoUse"));
		clickOn(btnGetMyQoute,"Get My Qoute Button");
		testStepInfo("Clicked on Get My Qoute Button");				
	}


	public void verifyPathProtectorreturnOfPremiumTerms(DataTable dataTable){
		if(Integer.parseInt(dataTable.retrieve("Age"))<18||Integer.parseInt(dataTable.retrieve("Age"))>75){
			waitForElement(weAgeIssue);
			if(isElementPresent(weAgeIssue)){
				testStepPassed("Expected error message is displayed "+ weAgeIssue.getText());
			}else{
				testStepFailed("Expected error message is not displayed");
			}
			takeScreenshot("Pass");
		}else{
			waitForElement(tblROPterm);
			if(isElementPresent(tblROPterm)){				
				String strPreferredNonTobacco20 = btnPreferredNonTobacco20.getText();
				if(strPreferredNonTobacco20.trim().equals(dataTable.retrieve("PreferredNonTobacco20"))){
					testStepPassed("Premium displayed is as expected->"+strPreferredNonTobacco20);
				}else{
					testStepFailed("Expected->"+dataTable.retrieve("PreferredNonTobacco20")+" but actual is->"+strPreferredNonTobacco20);					
				}

				String strPreferredNonTobacco30 = btnPreferredNonTobacco30.getText();
				if(strPreferredNonTobacco30.trim().equals(dataTable.retrieve("PreferredNonTobacco30"))){
					testStepPassed("Premium displayed is as expected->"+strPreferredNonTobacco30);					
				}else{
					testStepFailed("Expected->"+dataTable.retrieve("PreferredNonTobacco30")+" but actual is->"+strPreferredNonTobacco30);
				}


				String strStandardNonTobacco20 = btnStandardNonTobacco20.getText();
				if(strStandardNonTobacco20.trim().equals(dataTable.retrieve("StandardNonTobacco20"))){
					testStepPassed("Premium displayed is as expected->"+strStandardNonTobacco20);
				}else{
					testStepFailed("Expected->"+dataTable.retrieve("StandardNonTobacco20")+" but actual is->"+strStandardNonTobacco20);			
				}

				String strStandardNonTobacco30 = btnStandardNonTobacco30.getText();
				if(strStandardNonTobacco30.trim().equals(dataTable.retrieve("StandardNonTobacco30"))){
					testStepPassed("Premium displayed is as expected->"+strStandardNonTobacco30);
				}else{
					testStepFailed("Expected->"+dataTable.retrieve("StandardNonTobacco30")+" but actual is->"+strStandardNonTobacco30);
				}

				String strStandardTobacco20 = btnStandardTobacco20.getText();
				if(strStandardTobacco20.trim().equals(dataTable.retrieve("StandardTobacco20"))){
					testStepPassed("Premium displayed is as expected->"+strStandardTobacco20);				
				}else{
					testStepFailed("Expected->"+dataTable.retrieve("StandardTobacco20")+" but actual is->"+strStandardTobacco20);				
				}

				String strStandardTobacco30 = btnStandardTobacco30.getText();
				if(strStandardTobacco30.trim().equals(dataTable.retrieve("StandardTobacco30"))){
					testStepPassed("Premium displayed is as expected->"+strStandardTobacco30);					
				}else{
					testStepFailed("Expected->"+dataTable.retrieve("StandardTobacco30")+" but actual is->"+strStandardTobacco30);					
				}
				takeScreenshot("Pass");
			}else{
				testStepFailed("Premium Table is not displayed as expected");			
			}
		}
	}
}
