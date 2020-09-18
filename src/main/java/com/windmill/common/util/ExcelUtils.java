package com.windmill.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelUtils {
	public static String[][] readExcel(String filePath) {
		Workbook wb = null;
		String[][] data = (String[][]) null;

		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			wb = Workbook.getWorkbook(is);
			Sheet sheet = wb.getSheet(0);
			int cols = sheet.getColumns();
			int rows = sheet.getRows();
			data = new String[rows][cols];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					Cell cell = sheet.getCell(j, i);
					data[i][j] = cell.getContents();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				wb.close();
			}
			safeClose(is);
		}
		return data;
	}

	private static void safeClose(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			String[][] data = readExcel("D:/test.xls");
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					System.out.print(data[i][j] + ", ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}