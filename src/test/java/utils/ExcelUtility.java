package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

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
	
	@Test
	public void testingEXCEL() throws IOException {
		String path=System.getProperty("user.dir")+"/src/test/resources/testdata/Excel-3.xlsx";
		FileInputStream fis=new FileInputStream(path);
		Workbook book=new XSSFWorkbook(fis);
		Sheet sheet=book.getSheet("Employee");
		
		List<Map<String, String>> listOfMaps=new ArrayList<>();
		int rowCount=sheet.getPhysicalNumberOfRows();
		int columnCount=sheet.getRow(0).getLastCellNum();
		
		for(int row=1;row<rowCount;row++) {
			Map<String, String> m=new LinkedHashMap<>();
			for(int col=0;col<columnCount;col++) {
				String key=sheet.getRow(0).getCell(col).toString();
				String value=sheet.getRow(row).getCell(col).toString();
				m.put(key, value);
			}
			listOfMaps.add(m);
		}
		
		for(Map<String, String> map:listOfMaps) {
			System.out.println(map);
		}
		
		book.close();
	}
	
}
