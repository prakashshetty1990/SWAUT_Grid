package com.sun.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Datatable class for fetching value from the excel sheet
 *
 */
public class DataTable {
	protected Hashtable<String, String> testData;
	private String testName;
	public ArrayList<String> testCaseDataSets = new ArrayList();
	public int testCaseRow;
	public int testCaseDataRow;
	public HSSFWorkbook hssfWorkbook;
	public HSSFSheet dataSheet;


	public DataTable(String testName) {
		this.testName=testName;
		loadTestData();		
	}

	private void loadTestData() {
		hssfWorkbook = getWorkBook(testName);	
		dataSheet = getSheet(hssfWorkbook);
		ArrayList<String> columNames= getColumnNames(dataSheet);
	}


	public ArrayList<String> updateTestDataSet(String testCaseName)
	{	    	    
		String testCaseDataSet = null;
		String executionFlag = null;
		Boolean flag = Boolean.valueOf(false);
		for (int caseRow = 0; caseRow < dataSheet.getLastRowNum(); caseRow++) {
			testCaseDataSets.clear();
			try{
				String strActTestCaseName = dataSheet.getRow(caseRow).getCell(1).getStringCellValue();
				if (testCaseName.equals(strActTestCaseName))
				{
					for (int DataRow = caseRow; DataRow < dataSheet.getLastRowNum(); DataRow++)
					{
						testCaseRow = caseRow + 1;	          
						try {
							testCaseDataSet = dataSheet.getRow(DataRow).getCell(1).getStringCellValue();
							executionFlag = dataSheet.getRow(DataRow).getCell(2).getStringCellValue();
						} catch (Exception e) {
							testCaseDataSet = "";
						}		   
						if ((testCaseDataSet.startsWith(testCaseName)) && (executionFlag.toUpperCase().equals("YES")))
						{
							testCaseDataSets.add(testCaseDataSet);
						} else if (testCaseDataSet.isEmpty())
						{
							flag = Boolean.valueOf(true);
							break;
						}
					}
				}
			}catch(Exception ex){	    	  
				//do nothing
			}

			if (flag.booleanValue()) {
				break;
			}
		}	    
		return testCaseDataSets;
	}


	/**
	 * Get value from the data sheet
	 * @param key column name in the data sheet
	 * @return 
	 */
	public String getValue(String key){
		return testData.get(key);
	}

	private HSSFSheet getSheet(HSSFWorkbook hssfWorkbook) {

		TestSettings testSetting= new TestSettings();
		String sheetName=testSetting.getSheetName();		
		HSSFSheet sheet = hssfWorkbook.getSheet(sheetName); 	
		return sheet;
	}

	private HSSFWorkbook getWorkBook(String testName) {		
		String path=TestUtils.getRelativePath() + "//src//test//resources//TestData.xls";
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(path);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
			fileInputStream.close();
			return hssfWorkbook;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private Hashtable<String, String> prepareTestDataRowHashTable(
			ArrayList<String> columNames, Row testDataRow) {
		Hashtable<String, String> testDataRowHashTable= new Hashtable<String,String>();		
		for (Cell cell : testDataRow) {
			String columnName=columNames.get(cell.getColumnIndex());
			String columnValue=cell.getStringCellValue();
			testDataRowHashTable.put(columnName, columnValue);
		}
		return testDataRowHashTable;
	}

	private ArrayList<String> getColumnNames(Sheet testDataSheet) {
		ArrayList<String> columnNameList= new ArrayList<String>();
		Row row = testDataSheet.getRow(0);
		for (Cell cell : row) {
			columnNameList.add(cell.getStringCellValue());
		}
		return columnNameList;
	}


	private Row getTestDataRowForTestCase(Sheet testDataSheet) {
		for (Row row : testDataSheet) {
			if (IsRequiredTestCaseRow(row,this.testName)) {
				return row;
			} 
		}		
		return null;
	}

	private boolean IsRequiredTestCaseRow(Row row, String testCaseName) {
		Cell testCaseIdCell= row.getCell(0);
		String testCaseId=testCaseIdCell.getStringCellValue();
		if (testCaseId.trim().equals(testCaseName)) {
			return true;
		}
		return false;
	}


	//=======================Data Driven=============================

	public String getData(int row, int col)
	{
		Cell c = dataSheet.getRow(row-1).getCell(col-1);
		return c.getStringCellValue();
	}


	public int returnRowNo(int colIndex, String rowLabel) {
		boolean flag = true;
		int temp = 0;
		while (flag)
		{
			try
			{
				temp++;
				if (getData(temp, colIndex).trim().equalsIgnoreCase(rowLabel.trim()))
				{
					flag = false;
					return temp;
				}
			}
			catch (Exception e){
				System.out.println("'" + rowLabel + "' label not found" + " row no-->" + colIndex + " column no-->" + temp);
			}
		}

		return 0;
	}

	public int returnRowNumber(String Label)
	{
		this.testCaseDataRow = returnRowNo(2, Label);
		return testCaseDataRow;
	}

	public String retrieve(String Label)
	{
		return retrieveData(testCaseRow, testCaseDataRow, Label);
	}

	public String retrieveData(int intLabelRow, int intDataRow, String colLabel) { 
		return getData(intDataRow, returnColNo(intLabelRow, colLabel)); }

	public int returnColNo(int datasetNo, String colLabel) {
		boolean flag = true;
		int temp = 0;
		while (flag)
		{
			try
			{

				temp++;
				if (getData(datasetNo, temp).trim().equalsIgnoreCase(colLabel.trim()))
				{
					flag = false;
					return temp;
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		return 0;
	}
}
