package com.learnautomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	XSSFWorkbook wb;
	public ExcelDataProvider()
	{
		File src=new File("./Testdata/Data.xlsx");
		
		
		
		 try {
			 FileInputStream fis=new FileInputStream(src);
			wb=new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Uable to read excel file"+e.getMessage());
		}
	}
	
	public String getStringData(int sheetNum,int row,int col)
	{
		return wb.getSheetAt(sheetNum).getRow(row).getCell(col).getStringCellValue();
		
	}
	
	public String getStringData(String sheetname,int row,int col)  //Method overloading
	{
		return wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		
	}
	
	public double getNumericData(String sheetname,int row,int col)
	{
		return wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

}
