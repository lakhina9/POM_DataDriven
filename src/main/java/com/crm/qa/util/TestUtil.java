package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 30;
	public static String TESTDATA_SHEET_PATH = "C:/Selenium_02_12/FreeCRMPOM/src/main/java/com/crm/qa/testdata/CRMTestData.xls";
 
	
	static HSSFWorkbook book;
	static Sheet sheet;
	static FileInputStream fis;

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public static Object[][] getTestData(String sheetName) {
		File file = new File(TESTDATA_SHEET_PATH);

		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			book = new HSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sheet sheet = book.getSheet(sheetName);
		// int numRow=sheet.getPhysicalNumberOfRows();
		// int numCol=sheet.getRow(0).getLastCellNum();

		Object data[][] = new Object[sheet.getPhysicalNumberOfRows()][sheet
				.getRow(0).getLastCellNum()];

		for (int i = 1; i < sheet.getPhysicalNumberOfRows()-1; i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i).getCell(j).toString();
				System.out.println(data[i][j]);
			}
		}

		return data;
	}
public static void takeScreenshotAtEndOfTest() throws IOException{
	
	File fileSrc= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String currentDir=System.getProperty("user.dir");
	
  FileUtils.copyFile(fileSrc, new File(currentDir +"/ScreenShots/"+System.currentTimeMillis()+ ".png"));
	
//	FileUtils.copyFile(fileSrc, new File("C:/Selenium_02_12/FreeCRMPOM/ScreenShots/" +".png"));


	/*public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		*/
		}

	
}