
	//get pathname of current file
	var loc = window.location.pathname;

		//remove / from last index
	var dir1 = loc.substring(0, loc.lastIndexOf('/'));

	var dir = dir1.substring(0, dir1.lastIndexOf('/'));


	function validateemail()  
	{  
		 var x=document.myform.ToEmail.value;  
		 var atposition=x.indexOf("@");  
		 var dotposition=x.lastIndexOf(".");  
		 if (atposition<1 || dotposition<atposition+2 || dotposition+2>=x.length)
		 {  
			alert("Please enter a valid e-mail address");  
			return false;  
		 }  
	}
	
	//Run Application
	function launchApp(){
		//Get Test Configuration excel
		var TstConfigExcelLoc = dir+"/run.bat";
		//remove / from first index
		if (TstConfigExcelLoc.charAt(0) == "/") TstConfigExcelLoc = TstConfigExcelLoc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		MyObject = new ActiveXObject("WScript.Shell");

				 MyObject.run(TstConfigExcelLoc,1,true);
//		shell.Run("C:\\Users\\shivarajr\\Desktop\\Automation UI Framework\\SWAUT_AutismBat\\trigger.bat",1,True);

	}


//Run Application
	function LaunchHub(){
		//Get Test Configuration excel
		var Batch_file_loc = dir+"/Hub.bat";
		//remove / from first index
		if (Batch_file_loc.charAt(0) == "/") Batch_file_loc = Batch_file_loc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		MyObject = new ActiveXObject("WScript.Shell");

				 MyObject.run(Batch_file_loc,1,true);
//		shell.Run("C:\\Users\\shivarajr\\Desktop\\Automation UI Framework\\SWAUT_AutismBat\\trigger.bat",1,True);

	}

	
	
	function LaunchIENode(){
		//Get Test Configuration excel
		var Batch_file_loc = dir+"/Node_For_IE.bat";
		//remove / from first index
		if (Batch_file_loc.charAt(0) == "/") Batch_file_loc = Batch_file_loc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		MyObject = new ActiveXObject("WScript.Shell");

				 MyObject.run(Batch_file_loc,1,true);
//		shell.Run("C:\\Users\\shivarajr\\Desktop\\Automation UI Framework\\SWAUT_AutismBat\\trigger.bat",1,True);

	}
	
	
	
	

//Run Application
	function LaunchChromeNode(){
		//Get Test Configuration excel
		var Batch_file_loc = dir+"/Node_For_Chrome.bat";
		//remove / from first index
		if (Batch_file_loc.charAt(0) == "/") Batch_file_loc = Batch_file_loc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		MyObject = new ActiveXObject("WScript.Shell");

				 MyObject.run(Batch_file_loc,1,true);
//		shell.Run("C:\\Users\\shivarajr\\Desktop\\Automation UI Framework\\SWAUT_AutismBat\\trigger.bat",1,True);

	}


//Run Application
	function LaunchFirefoxNode(){
		//Get Test Configuration excel
		var Batch_file_loc = dir+"/NodeForGeckodriver.bat";
		//remove / from first index
		if (Batch_file_loc.charAt(0) == "/") Batch_file_loc = Batch_file_loc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		MyObject = new ActiveXObject("WScript.Shell");

				 MyObject.run(Batch_file_loc,1,true);
//		shell.Run("C:\\Users\\shivarajr\\Desktop\\Automation UI Framework\\SWAUT_AutismBat\\trigger.bat",1,True);

	}





//to return blank value if there is a undefined
	function returnvalue(str){
		var result;
		if( str == undefined ){
			result = "";
		}else{
			result =str;
		}
		return result;
	}



	function GetCellData() {
		//Get Test Configuration excel
			
		var TstConfigExcelLoc = dir+"/src/test/resources/TestConfiguration.xls";

		//remove / from first index
		if (TstConfigExcelLoc.charAt(0) == "/") TstConfigExcelLoc = TstConfigExcelLoc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);
		var Excel;
		Excel = new ActiveXObject("Excel.Application"); 
		Excel.Visible = false;
		var workBook;
		workBook = Excel.Workbooks.Open(TstConfigExcelLoc);
		if(workBook.ActiveSheet.Cells(3,3).Value=="Mobile"){
			TesConfigDetails.Testing_on_yes.checked = true; 	
			Displayfield();
		}else{
			TesConfigDetails.Testing_on_no.checked = true;
			Hidefield();
		}
		TesConfigDetails.PlatFormName.value = returnvalue(workBook.ActiveSheet.Cells(4,3).Value);
		TesConfigDetails.url.value = returnvalue(workBook.ActiveSheet.Cells(5,3).Value);
		TesConfigDetails.Browser.value = returnvalue(workBook.ActiveSheet.Cells(6,3).Value);
		TesConfigDetails.IpAddress.value = returnvalue(workBook.ActiveSheet.Cells(8,3).Value);
		TesConfigDetails.PortNumber.value = returnvalue(workBook.ActiveSheet.Cells(9,3).Value);
		TesConfigDetails.TestDataFile.value = returnvalue(workBook.ActiveSheet.Cells(10,3).Value);
		TesConfigDetails.PageLoadWaitTime.value = returnvalue(workBook.ActiveSheet.Cells(12,3).Value);
		TesConfigDetails.AppiumTimeOut.value = returnvalue(workBook.ActiveSheet.Cells(13,3).Value);
		TesConfigDetails.ElementLoadWaitTime.value = returnvalue(workBook.ActiveSheet.Cells(14,3).Value);
		TesConfigDetails.ImplicitlyWaitTime.value = returnvalue(workBook.ActiveSheet.Cells(15,3).Value);
		TesConfigDetails.TextLoadWaitTime.value = returnvalue(workBook.ActiveSheet.Cells(16,3).Value);

		if(workBook.ActiveSheet.Cells(17,3).Value==="Yes"){
			TesConfigDetails.executeYes.checked = true;

		}else{
			TesConfigDetails.executeNo.checked = true;
		}
		TesConfigDetails.OverideTimeoutOnFailure.value = returnvalue(workBook.ActiveSheet.Cells(18,3).Value);
		TesConfigDetails.Smtp_HostName.value = returnvalue(workBook.ActiveSheet.Cells(20,3).Value);
		TesConfigDetails.SenderMailId.value = returnvalue(workBook.ActiveSheet.Cells(21,3).Value);
		TesConfigDetails.SuiteName.value = returnvalue(workBook.ActiveSheet.Cells(22,3).Value);

		if(workBook.ActiveSheet.Cells(23,3).Value==="Yes"){
			TesConfigDetails.SendMailYes.checked = true;
			EnableFields()
		}else{
			TesConfigDetails.SendMailNo.checked = true;
			DisableFields();
		}

		TesConfigDetails.ToEmail.value = returnvalue(workBook.ActiveSheet.Cells(24,3).Value);
		TesConfigDetails.CcEmail.value = returnvalue(workBook.ActiveSheet.Cells(25,3).Value);
		TesConfigDetails.MsgConfHostName.value = returnvalue(workBook.ActiveSheet.Cells(26,3).Value);
		TesConfigDetails.MsgConfSenderAddress.value = returnvalue(workBook.ActiveSheet.Cells(27,3).Value);
		TesConfigDetails.MsgConfUserName.value = returnvalue(workBook.ActiveSheet.Cells(28,3).Value);
		TesConfigDetails.MsgConfPassword.value = returnvalue(workBook.ActiveSheet.Cells(29,3).Value);
		TesConfigDetails.MsgConfSuiteName.value = returnvalue(workBook.ActiveSheet.Cells(30,3).Value);
		TesConfigDetails.Project_Name.value = returnvalue(workBook.ActiveSheet.Cells(39,3).Value);
		TesConfigDetails.Version_Name.value = returnvalue(workBook.ActiveSheet.Cells(40,3).Value);
		TesConfigDetails.Tc_Settings_Excelpath.value = returnvalue(workBook.ActiveSheet.Cells(41,3).Value);
		TesConfigDetails.client_logo_path.value = returnvalue(workBook.ActiveSheet.Cells(44,3).Value);
		TesConfigDetails.sun_logo_path.value = returnvalue(workBook.ActiveSheet.Cells(45,3).Value);

		Excel.Quit();
	}



function updatetestconfigdetails() {
		//Get Test Configuration excel
		var TstConfigExcelLoc = dir+"/src/test/resources/TestConfiguration.xls";
		alert(TstConfigExcelLoc.charAt(1));
		//remove / from first index
		if (TstConfigExcelLoc.charAt(0) == "/") TstConfigExcelLoc = TstConfigExcelLoc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		excel = new ActiveXObject("Excel.Application"); 
		excel.DisplayAlerts = false;
		excel.Visible = false;
		workBook = excel.WorkBooks.open(TstConfigExcelLoc);
		workSheet = workBook.Worksheets("Sheet1");

		if(document.all.Testing_on_yes.checked){
				workSheet.Cells(3,3).value = document.all.Testing_on_yes.value;
			}
			else{
				workSheet.Cells(3,3).value = document.all.Testing_on_no.value;
		}
		workSheet.Cells(4,3).value = document.all.PlatFormName.value;
		workSheet.Cells(5,3).value = document.all.url.value;
		workSheet.Cells(6,3).value = document.all.Browser.value;
		workSheet.Cells(8,3).value = document.all.IpAddress.value;
		workSheet.Cells(9,3).value = document.all.PortNumber.value;
		workSheet.Cells(10,3).value = document.all.TestDataFile.value;
		workSheet.Cells(12,3).value = document.all.PageLoadWaitTime.value;
		workSheet.Cells(13,3).value = document.all.AppiumTimeOut.value;
		workSheet.Cells(14,3).value = document.all.ElementLoadWaitTime.value;
		workSheet.Cells(15,3).value = document.all.ImplicitlyWaitTime.value;
		workSheet.Cells(16,3).value = document.all.TextLoadWaitTime.value;
		
		if(document.all.executeYes.checked){
			workSheet.Cells(17,3).value = document.all.executeYes.value;
			}
			else{
			workSheet.Cells(17,3).value = document.all.executeNo.value;
		}
		workSheet.Cells(18,3).value = document.all.OverideTimeoutOnFailure.value;

		if(document.all.SendMailYes.checked){
			workSheet.Cells(23,3).value = document.all.SendMailYes.value;
			}
			else{
			workSheet.Cells(23,3).value = document.all.SendMailNo.value;
		}
		workSheet.Cells(20,3).value = document.all.Smtp_HostName.value;
		workSheet.Cells(21,3).value = document.all.SenderMailId.value;
		workSheet.Cells(22,3).value = document.all.SuiteName.value;
		workSheet.Cells(24,3).value = document.all.ToEmail.value;
		workSheet.Cells(25,3).value = document.all.CcEmail.value;
		workSheet.Cells(26,3).value = document.all.MsgConfHostName.value;
		workSheet.Cells(27,3).value = document.all.MsgConfSenderAddress.value;
		workSheet.Cells(28,3).value = document.all.MsgConfUserName.value;
		workSheet.Cells(29,3).value = document.all.MsgConfPassword.value;
		workSheet.Cells(30,3).value = document.all.MsgConfSuiteName.value;
		workSheet.Cells(39,3).value = document.all.Project_Name.value;
		workSheet.Cells(40,3).value = document.all.Version_Name.value;
		workSheet.Cells(41,3).value = document.all.Tc_Settings_Excelpath.value;
		workSheet.Cells(44,3).value = document.all.client_logo_path.value;
		workSheet.Cells(45,3).value = document.all.sun_logo_path.value;
		workBook.save(); 
		excel.SaveAs(TstConfigExcelLoc).    
		excel.quit();
		excel = null;
	}



	function Hidefield()
	{

		document.TesConfigDetails.PlatFormName.disabled = true ;
		document.TesConfigDetails.PlatFormName.style.background = "#CCCCCC";
		document.TesConfigDetails.IpAddress.disabled = true ;
		document.TesConfigDetails.IpAddress.style.background = "#CCCCCC";
		document.TesConfigDetails.PortNumber.disabled = true ;
		document.TesConfigDetails.PortNumber.style.background = "#CCCCCC";
		document.TesConfigDetails.AppiumTimeOut.disabled = true ;
		document.TesConfigDetails.AppiumTimeOut.style.background = "#CCCCCC";

	}


	function Displayfield()
	{

		document.TesConfigDetails.PlatFormName.disabled = false ;
		document.TesConfigDetails.PlatFormName.style.background = "#FFFFFF";
		document.TesConfigDetails.IpAddress.disabled = false ;
		document.TesConfigDetails.IpAddress.style.background = "#FFFFFF";
		document.TesConfigDetails.PortNumber.disabled = false ;
		document.TesConfigDetails.PortNumber.style.background = "#FFFFFF";
		document.TesConfigDetails.AppiumTimeOut.disabled = false ;
		document.TesConfigDetails.AppiumTimeOut.style.background = "#FFFFFF";
	}

	function EnableFields()
	{
		document.TesConfigDetails.Smtp_HostName.disabled = false ;
		document.TesConfigDetails.Smtp_HostName.style.background = "#FFFFFF";
		
		document.TesConfigDetails.SenderMailId.disabled = false ;
		document.TesConfigDetails.SenderMailId.style.background = "#FFFFFF";
		
		document.TesConfigDetails.SuiteName.disabled = false ;
		document.TesConfigDetails.SuiteName.style.background = "#FFFFFF";
		
		document.TesConfigDetails.ToEmail.disabled = false ;
		document.TesConfigDetails.ToEmail.style.background = "#FFFFFF";
		
		document.TesConfigDetails.CcEmail.disabled = false ;
		document.TesConfigDetails.CcEmail.style.background = "#FFFFFF";

	}
	function DisableFields()
	{

		document.TesConfigDetails.Smtp_HostName.disabled = true ;
		document.TesConfigDetails.Smtp_HostName.style.background = "#CCCCCC";
		
		document.TesConfigDetails.SenderMailId.disabled = true ;
		document.TesConfigDetails.SenderMailId.style.background = "#CCCCCC";
		
		document.TesConfigDetails.SuiteName.disabled = true ;
		document.TesConfigDetails.SuiteName.style.background = "#CCCCCC";
		
		document.TesConfigDetails.ToEmail.disabled = true ;
		document.TesConfigDetails.ToEmail.style.background = "#CCCCCC";
		
		document.TesConfigDetails.CcEmail.disabled = true ;
		document.TesConfigDetails.CcEmail.style.background = "#CCCCCC";

	}


function GetTestCaseCellData() {
		//Get Test Configuration excel
		
		
		var TstCaseExcelLoc = dir+"/src/test/resources/TestCaseSettings.xls"

		

		//remove / from first index
		if (TstCaseExcelLoc.charAt(0) == "/") TstCaseExcelLoc = TstCaseExcelLoc.substr(1);

		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);
		var Excel;
		
		Excel = new ActiveXObject("Excel.Application"); 
		
		Excel.Visible = false;
		
		var workBook;
		
		workBook = Excel.Workbooks.Open(TstCaseExcelLoc);

		TestCaseDetails.TC1.value = returnvalue(workBook.ActiveSheet.Cells(2,5).Value);

		TestCaseDetails.TC2.value = returnvalue(workBook.ActiveSheet.Cells(3,5).Value);

		TestCaseDetails.TC3.value = returnvalue(workBook.ActiveSheet.Cells(4,5).Value);

		TestCaseDetails.TC4.value = returnvalue(workBook.ActiveSheet.Cells(5,5).Value);

		TestCaseDetails.TC5.value = returnvalue(workBook.ActiveSheet.Cells(6,5).Value);

		Excel.quit();
		Excel = null;
	}

	function updatetestcasedetails() {
		//Get Test Configuration excel
		var TstCaseExcelLoc = dir+"/src/test/resources/TestCaseSettings.xls";
		//remove / from first index
		if (TstCaseExcelLoc.charAt(0) == "/") TstCaseExcelLoc = TstCaseExcelLoc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		excel = new ActiveXObject("Excel.Application"); 
		excel.DisplayAlerts = false;
		excel.Visible = false;
		workBook = excel.WorkBooks.open(TstCaseExcelLoc);
		workSheet = workBook.Worksheets("Sheet1");

		workSheet.Cells(2,5).value = document.all.TC1.value;

		workSheet.Cells(3,5).value = document.all.TC2.value;

		workSheet.Cells(4,5).value = document.all.TC3.value;

		workSheet.Cells(5,5).value = document.all.TC4.value;

		workSheet.Cells(6,5).value = document.all.TC5.value;

		workBook.save(); 
		excel.SaveAs(TstCaseExcelLoc).    
		excel.quit();
		excel = null;
	}

function hideshow()
{
var pf1= document.getElementById('PlatFormName_1');
var os1= document.getElementById('OS_Windows_1');
var os2= document.getElementById('OS_Android_1');
var os3= document.getElementById('OS_IOS_1');
var osvw1= document.getElemenyById('Windows_1');
var osva1= document.getElemenyById('Android_1');
var osvi1= document.getElemenyById('Ios_1');

document.TesConfigDetails.Smtp_HostName.disabled = true ;
document.TesConfigDetails.Smtp_HostName.style.background = "#CCCCCC";

}


function GetGridTestCaseCellData() {
		//Get Test Configuration excel
		
		
		var TstCaseExcelLoc = dir+"/src/test/resources/TestCaseSettings.xls";

		

		//remove / from first index
		if (TstCaseExcelLoc.charAt(0) == "/") TstCaseExcelLoc = TstCaseExcelLoc.substr(1);

		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);
		var Excel;
		
		Excel = new ActiveXObject("Excel.Application"); 
		
		Excel.Visible = false;
		
		var workBook;
		
		workBook = Excel.Workbooks.Open(TstCaseExcelLoc);

		TestCaseDetails.TC1.value = returnvalue(workBook.ActiveSheet.Cells(2,5).Value);

		TestCaseDetails.TC2.value = returnvalue(workBook.ActiveSheet.Cells(3,5).Value);

		TestCaseDetails.TC3.value = returnvalue(workBook.ActiveSheet.Cells(4,5).Value);

		Excel.Quit();
	}

	function updategridtestcasedetails() {
		//Get Test Configuration excel
		var TstCaseExcelLoc = dir+"/src/test/resources/TestCaseSettings.xls";
		//remove / from first index
		if (TstCaseExcelLoc.charAt(0) == "/") TstCaseExcelLoc = TstCaseExcelLoc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		excel = new ActiveXObject("Excel.Application"); 
		excel.DisplayAlerts = false;
		excel.Visible = false;
		workBook = excel.WorkBooks.open(TstCaseExcelLoc);
		workSheet = workBook.Worksheets("Sheet1");

		workSheet.Cells(2,5).value = document.all.TC1.value;

		workSheet.Cells(3,5).value = document.all.TC2.value;

		workSheet.Cells(4,5).value = document.all.TC3.value;

		workBook.save(); 
		excel.SaveAs(TstCaseExcelLoc).    
		excel.quit();
		excel = null;
	}


	function GetGridTestConfigData() {
		//Get Test Configuration excel
		
		
		var TstCaseExcelLoc = dir+"/src/test/resources/TestConfiguration.xls";
		//remove / from first index
		if (TstCaseExcelLoc.charAt(0) == "/") TstCaseExcelLoc = TstCaseExcelLoc.substr(1);

		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);
		var Excel;
		
		Excel = new ActiveXObject("Excel.Application"); 
		
		Excel.Visible = false;
		
		var workBook;
		
		workBook = Excel.Workbooks.Open(TstCaseExcelLoc);

		TestGridConfigDetails.DeviceType_1.value = returnvalue(workBook.ActiveSheet.Cells(2,2).Value);

		TestGridConfigDetails.IpAddress_1.value = returnvalue(workBook.ActiveSheet.Cells(2,3).Value);

		TestGridConfigDetails.port_1.value = returnvalue(workBook.ActiveSheet.Cells(2,4).Value);

		TestGridConfigDetails.PlatFormName_1.value = returnvalue(workBook.ActiveSheet.Cells(2,5).Value);

		TestGridConfigDetails.OS_Version_1.value = returnvalue(workBook.ActiveSheet.Cells(2,6).Value);

		TestGridConfigDetails.Browser_Name_1.value = returnvalue(workBook.ActiveSheet.Cells(2,7).Value);

		TestGridConfigDetails.BrowserVersion_1.value = returnvalue(workBook.ActiveSheet.Cells(2,8).Value);

		TestGridConfigDetails.TC_1.value = returnvalue(workBook.ActiveSheet.Cells(2,9).Value);



		TestGridConfigDetails.DeviceType_2.value = returnvalue(workBook.ActiveSheet.Cells(3,2).Value);

		TestGridConfigDetails.IpAddress_2.value = returnvalue(workBook.ActiveSheet.Cells(3,3).Value);

		TestGridConfigDetails.port_2.value = returnvalue(workBook.ActiveSheet.Cells(3,4).Value);

		TestGridConfigDetails.PlatFormName_2.value = returnvalue(workBook.ActiveSheet.Cells(3,5).Value);

		TestGridConfigDetails.OS_Version_2.value = returnvalue(workBook.ActiveSheet.Cells(3,6).Value);

		TestGridConfigDetails.Browser_Name_2.value = returnvalue(workBook.ActiveSheet.Cells(3,7).Value);

		TestGridConfigDetails.BrowserVersion_2.value = returnvalue(workBook.ActiveSheet.Cells(3,8).Value);

		TestGridConfigDetails.TC_2.value = returnvalue(workBook.ActiveSheet.Cells(3,9).Value);

		

		TestGridConfigDetails.DeviceType_3.value = returnvalue(workBook.ActiveSheet.Cells(4,2).Value);

		TestGridConfigDetails.IpAddress_3.value = returnvalue(workBook.ActiveSheet.Cells(4,3).Value);

		TestGridConfigDetails.port_3.value = returnvalue(workBook.ActiveSheet.Cells(4,4).Value);

		TestGridConfigDetails.PlatFormName_3.value = returnvalue(workBook.ActiveSheet.Cells(4,5).Value);

		TestGridConfigDetails.OS_Version_3.value = returnvalue(workBook.ActiveSheet.Cells(4,6).Value);

		TestGridConfigDetails.Browser_Name_3.value = returnvalue(workBook.ActiveSheet.Cells(4,7).Value);

		TestGridConfigDetails.BrowserVersion_3.value = returnvalue(workBook.ActiveSheet.Cells(4,8).Value);

		TestGridConfigDetails.TC_3.value = returnvalue(workBook.ActiveSheet.Cells(4,9).Value);

		
		
		
		TestGridConfigDetails.DeviceType_4.value = returnvalue(workBook.ActiveSheet.Cells(5,2).Value);

		TestGridConfigDetails.IpAddress_4.value = returnvalue(workBook.ActiveSheet.Cells(5,3).Value);

		TestGridConfigDetails.port_4.value = returnvalue(workBook.ActiveSheet.Cells(5,4).Value);

		TestGridConfigDetails.PlatFormName_4.value = returnvalue(workBook.ActiveSheet.Cells(5,5).Value);

		TestGridConfigDetails.OS_Version_4.value = returnvalue(workBook.ActiveSheet.Cells(5,6).Value);

		TestGridConfigDetails.Browser_Name_4.value = returnvalue(workBook.ActiveSheet.Cells(5,7).Value);

		TestGridConfigDetails.BrowserVersion_4.value = returnvalue(workBook.ActiveSheet.Cells(5,8).Value);

		TestGridConfigDetails.TC_4.value = returnvalue(workBook.ActiveSheet.Cells(5,9).Value);


		
		TestGridConfigDetails.DeviceType_5.value = returnvalue(workBook.ActiveSheet.Cells(6,2).Value);

		TestGridConfigDetails.IpAddress_5.value = returnvalue(workBook.ActiveSheet.Cells(6,3).Value);

		TestGridConfigDetails.port_5.value = returnvalue(workBook.ActiveSheet.Cells(6,4).Value);

		TestGridConfigDetails.PlatFormName_5.value = returnvalue(workBook.ActiveSheet.Cells(6,5).Value);

		TestGridConfigDetails.OS_Version_5.value = returnvalue(workBook.ActiveSheet.Cells(6,6).Value);

		TestGridConfigDetails.Browser_Name_5.value = returnvalue(workBook.ActiveSheet.Cells(6,7).Value);

		TestGridConfigDetails.BrowserVersion_5.value = returnvalue(workBook.ActiveSheet.Cells(6,8).Value);

		TestGridConfigDetails.TC_5.value = returnvalue(workBook.ActiveSheet.Cells(6,9).Value);


		
		Excel.Quit();
	}

	function updategridtestconfigdetails() {
		//Get Test Configuration excel
	
		var TstCaseExcelLoc = dir+"/src/test/resources/TestConfiguration.xls";
		//remove / from first index
		if (TstCaseExcelLoc.charAt(0) == "/") TstCaseExcelLoc = TstCaseExcelLoc.substr(1);
		//remove / from last index
//		if (string.charAt(string.length - 1) == "/") string = string.substr(0, string.length - 1);

		excel = new ActiveXObject("Excel.Application"); 
		excel.DisplayAlerts = false;
		excel.Visible = false;
		workBook = excel.WorkBooks.open(TstCaseExcelLoc);
		workSheet = workBook.Worksheets("Grid Config");

		workSheet.Cells(2,2).value = document.all.DeviceType_1.value;

		workSheet.Cells(2,3).value = document.all.IpAddress_1.value;

		workSheet.Cells(2,4).value = document.all.port_1.value;

		workSheet.Cells(2,5).value = document.all.PlatFormName_1.value;

		workSheet.Cells(2,6).value = document.all.OS_Version_1.value;

		workSheet.Cells(2,7).value = document.all.Browser_Name_1.value;

		workSheet.Cells(2,8).value = document.all.BrowserVersion_1.value;

		workSheet.Cells(2,9).value = document.all.TC_1.value;



		
		workSheet.Cells(3,2).value = document.all.DeviceType_2.value;

		workSheet.Cells(3,3).value = document.all.IpAddress_2.value;

		workSheet.Cells(3,4).value = document.all.port_2.value;

		workSheet.Cells(3,5).value = document.all.PlatFormName_2.value;

		workSheet.Cells(3,6).value = document.all.OS_Version_2.value;

		workSheet.Cells(3,7).value = document.all.Browser_Name_2.value;

		workSheet.Cells(3,8).value = document.all.BrowserVersion_2.value;

		workSheet.Cells(3,9).value = document.all.TC_2.value;



		
		workSheet.Cells(4,2).value = document.all.DeviceType_3.value;

		workSheet.Cells(4,3).value = document.all.IpAddress_3.value;

		workSheet.Cells(4,4).value = document.all.port_3.value;

		workSheet.Cells(4,5).value = document.all.PlatFormName_3.value;

		workSheet.Cells(4,6).value = document.all.OS_Version_3.value;

		workSheet.Cells(4,7).value = document.all.Browser_Name_3.value;

		workSheet.Cells(4,8).value = document.all.BrowserVersion_3.value;

		workSheet.Cells(4,9).value = document.all.TC_3.value;


		

		
		workSheet.Cells(5,2).value = document.all.DeviceType_4.value;

		workSheet.Cells(5,3).value = document.all.IpAddress_4.value;

		workSheet.Cells(5,4).value = document.all.port_4.value;

		workSheet.Cells(5,5).value = document.all.PlatFormName_4.value;

		workSheet.Cells(5,6).value = document.all.OS_Version_4.value;

		workSheet.Cells(5,7).value = document.all.Browser_Name_4.value;

		workSheet.Cells(5,8).value = document.all.BrowserVersion_4.value;

		workSheet.Cells(5,9).value = document.all.TC_4.value;




		
		workSheet.Cells(6,2).value = document.all.DeviceType_5.value;

		workSheet.Cells(6,3).value = document.all.IpAddress_5.value;

		workSheet.Cells(6,4).value = document.all.port_5.value;

		workSheet.Cells(6,5).value = document.all.PlatFormName_5.value;

		workSheet.Cells(6,6).value = document.all.OS_Version_5.value;

		workSheet.Cells(6,7).value = document.all.Browser_Name_5.value;

		workSheet.Cells(6,8).value = document.all.BrowserVersion_5.value;

		workSheet.Cells(6,9).value = document.all.TC_5.value;





		workBook.save(); 
		excel.SaveAs(TstCaseExcelLoc).    
		excel.quit();
		excel = null;
	}





