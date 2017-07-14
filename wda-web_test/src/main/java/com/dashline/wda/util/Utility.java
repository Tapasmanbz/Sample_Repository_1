package com.dashline.wda.util;

import java.util.HashMap;

import com.dashline.wda.main.Base;

public class Utility extends Base {

	/**
	 * This method will help to determine whether test case is runnable or not
	 * 
	 * @param testCase
	 * @return true if Runmode is Y else false
	 */
	public static boolean checkTestCaseRunmode(String testCase) {
		for (int i = 2; i <= datatable.getRowCount("Tests"); ++i) {

			if (datatable.getCellData("Tests", "Test Case ID", i).equalsIgnoreCase(testCase)) {
				return (datatable.getCellData("Tests", "Runmode", i).equalsIgnoreCase("Y"));
			}

		}

		datatable = null;
		return false;
	}

	public static boolean checkRunmode(String testCase, String testDataRunmode) {
		// TODO Auto-generated method stub
		return checkTestCaseRunmode(testCase) && (testDataRunmode.equalsIgnoreCase("Y"));
	}

	/**
	 * This method will help to take test data from excel sheet
	 * 
	 * @param sheetName
	 * @return double array of object with xls sheet data
	 */

	public static Object[][] getTestData(String sheetName) {
		if (!(datatable.isSheetExist(sheetName))) {
			datatable = null;
			return new Object[1][0];
		}

		int rows = datatable.getRowCount(sheetName) - 1;
		System.out.println("Total Number of test rows:" + rows);

		if (rows <= 0) {
			Object[][] testData = new Object[1][0];
			return testData;
		}

		rows = datatable.getRowCount(sheetName);
		int cols = datatable.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		int r = 0;

		for (int rowNum = 2; rowNum <= rows; ++rowNum) {

			HashMap<String, String> hm = new HashMap<String, String>();

			for (int colNum = 0; colNum < cols; ++colNum) {
				hm.put(datatable.getCellData(sheetName, colNum, 1), datatable.getCellData(sheetName, colNum, rowNum));
				data[r][0] = hm;
			}

			r++;
		}

		return data;

	}

}
