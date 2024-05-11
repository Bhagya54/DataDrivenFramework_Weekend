package utility;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class DataUtils extends BaseTest {
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		
		String sheetName = m.getName();
		int rowCount = excel.getRowCount(sheetName);
		int colCount = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rowCount-1][colCount];
		
		for (int row = 2; row<=rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				data[row-2][col] = excel.getCellData(sheetName, col, row);
			}
		}
		

		return data;
	}
}
