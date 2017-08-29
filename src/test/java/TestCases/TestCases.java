package TestCases;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.pages.IllinoisApplication;
import com.sun.pages.IllinoisLifeInsurePage;
import com.sun.utils.ReportPath;
import com.sun.utils.SunAutoTest;

public class TestCases extends SunAutoTest {

	private IllinoisApplication illinoisApplication;
	private IllinoisLifeInsurePage illinoisLifeInsurePage;	
	public ArrayList<String> testCaseDataSets = new ArrayList();

	@BeforeClass
	@Parameters({"selenium.browser"})
	public void setUp(String browserName){
		ReportPath reportPath = new ReportPath();
		ReportPath=reportPath.getReportPath(browserName);			
		extent = new ExtentReports(ReportPath+"/Results.html", true);				
	}

	@BeforeMethod
	@Parameters({"selenium.machinename","selenium.host", "selenium.port", "selenium.browser", "selenium.os", "selenium.browserVersion", "selenium.osVersion"})
	public void openApplication(String machineName,String host,String port,String browserName,String os,String browserVersion,String osVersion,Method method){				
		browser=openBrowser(machineName, host, port, browserName, os, browserVersion, osVersion);		
		String testCaseName = method.getName();
		parent = extent.startTest(testCaseName, "<font size=4 color=black>" +testCaseName+ "</font><br/>");
		setupData(testCaseName);					
	}


	@Test
	public void TC_001(){	

		String strName = new Exception().getStackTrace()[0].getMethodName();
	
		testCaseDataSets = dataTable.updateTestDataSet(strName);
		for (String testDataSet : testCaseDataSets) {
			int testCaseDataRow = dataTable.returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			illinoisApplication = new IllinoisApplication(browser, report, ReportPath);
			illinoisLifeInsurePage = illinoisApplication.openRelevantApplication();
			illinoisLifeInsurePage.getMyQuote(dataTable);					
			illinoisLifeInsurePage.verifyPathProtectorreturnOfPremiumTerms(dataTable);
			
			testStepInfoEnd(testDataSet);
		}
	}


	@Test
	public void TC_002(){	

		String strName = new Exception().getStackTrace()[0].getMethodName();	
		testCaseDataSets = dataTable.updateTestDataSet(strName);

		for (String testDataSet : testCaseDataSets) {
			int testCaseDataRow = dataTable.returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			illinoisApplication = new IllinoisApplication(browser, report, ReportPath);
			illinoisLifeInsurePage = illinoisApplication.openRelevantApplication();
			illinoisLifeInsurePage.getMyQuote(dataTable);					
			illinoisLifeInsurePage.verifyPathProtectorreturnOfPremiumTerms(dataTable);
			
			testStepInfoEnd(testDataSet);
		}
	
	}


	@Test
	public void TC_003(){	

		String strName = new Exception().getStackTrace()[0].getMethodName();
	
		testCaseDataSets = dataTable.updateTestDataSet(strName);

		for (String testDataSet : testCaseDataSets) {
			int testCaseDataRow = dataTable.returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			illinoisApplication = new IllinoisApplication(browser, report, ReportPath);
			illinoisLifeInsurePage = illinoisApplication.openRelevantApplication();
			illinoisLifeInsurePage.getMyQuote(dataTable);					
			illinoisLifeInsurePage.verifyPathProtectorreturnOfPremiumTerms(dataTable);			
			
			testStepInfoEnd(testDataSet);
		}

	}


	@Test
	public void TC_004(){	

		String strName = new Exception().getStackTrace()[0].getMethodName();
		testCaseDataSets = dataTable.updateTestDataSet(strName);

		for (String testDataSet : testCaseDataSets) {
			int testCaseDataRow = dataTable.returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			illinoisApplication = new IllinoisApplication(browser, report, ReportPath);
			illinoisLifeInsurePage = illinoisApplication.openRelevantApplication();
			illinoisLifeInsurePage.getMyQuote(dataTable);					
			illinoisLifeInsurePage.verifyPathProtectorreturnOfPremiumTerms(dataTable);		
			
			testStepInfoEnd(testDataSet);
		}

	}



	@Test
	public void TC_005(){	

		String strName = new Exception().getStackTrace()[0].getMethodName();

		testCaseDataSets = dataTable.updateTestDataSet(strName);

		for (String testDataSet : testCaseDataSets) {
			int testCaseDataRow = dataTable.returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			illinoisApplication = new IllinoisApplication(browser, report, ReportPath);
			illinoisLifeInsurePage = illinoisApplication.openRelevantApplication();
			illinoisLifeInsurePage.getMyQuote(dataTable);					
			illinoisLifeInsurePage.verifyPathProtectorreturnOfPremiumTerms(dataTable);

			testStepInfoEnd(testDataSet);
		}

	}


	@AfterMethod
	public void closeAppication() throws Exception{		
		illinoisApplication.close();
		extent.endTest(parent);
		extent.flush();

	}

	public void testStepInfoStart(String testDataSet) {
		report = extent.startTest(testDataSet);
		report.log(LogStatus.INFO, "########### Start of Test Case Data Set: "+testDataSet + " ###########");	    
	}


	public void testStepInfoEnd(String testDataSet) {
		report.log(LogStatus.INFO, "########### End of Test Case Data Set: "+testDataSet + " ###########");
		parent.appendChild(report);
	}
}
