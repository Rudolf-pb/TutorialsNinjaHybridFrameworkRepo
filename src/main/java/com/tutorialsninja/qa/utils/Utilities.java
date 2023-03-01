package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static final int Implicit_wait_Time =10;
	public static final int Page_Load_time =5;
	
	public static String generateDateandTime() {
		
		Date date = new Date();
		
		String TimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		
		return "PramodBej"+TimeStamp+"@gmail.com";
	}
	
	public static Object[][] getTestDataFromExcel(String SheetName) {
		
		XSSFWorkbook  workbook = null;
		File ExcelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\tutorialsNinjaTestData.xlsx");
		
		try {
			FileInputStream fisExcel = new FileInputStream(ExcelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		XSSFSheet Sheet = workbook.getSheet(SheetName);
		
		int rows = Sheet.getLastRowNum();
		int columns = Sheet.getRow(0).getLastCellNum();
		
		Object [][] data = new Object[rows][columns];
		
		for(int i =0;i<rows;i++) {
			XSSFRow row = Sheet.getRow(i+1);
			
			for(int j=0;j<columns;j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
				
				case STRING:
					data[i][j]= cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]= cell.getBooleanCellValue();
					break;
				default:
					break;
				}
				
			}
		}
		return data;
	}

}
