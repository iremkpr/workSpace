package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	private static Workbook book;
	private static Sheet sheet;
 
	private static void openExcel(String filePath) {
 		try {
			FileInputStream fis=new FileInputStream(filePath);
			book=new XSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private static void loadSheet(String sheetName) {
		sheet=book.getSheet(sheetName);
	}
	private static int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	private static int columnCount() {
		return sheet.getRow(0).getLastCellNum();
	}
	private static String cellData(int rowIndex, int columnIndex) {
		return sheet.getRow(rowIndex).getCell(columnIndex).toString();
	}
	public static Object[][] excelIntoArray(String filePath, String sheetName) {
		openExcel(filePath);
		loadSheet(sheetName);
		Object [][] data= new Object[rowCount()-1][columnCount()];
		for(int i=1;i<rowCount();i++) {//ROW
			for (int j = 0; j < columnCount(); j++) {//COLUMN
				data[i-1][j]=cellData(i, j);
			}
		}
		return data;
 			
	}
	
}
