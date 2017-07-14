package com.dashline.wda.util;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.dashline.wda.main.Base;

public class BusinessFunctions extends Base {

	/**
	 * This method will login to the wda application
	 * 
	 * @param userName
	 * @param password
	 * @return true if login is successful else false
	 */
	public static boolean login(String userName, String password) {

		try {
			if (!loggedin) {

				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(""))).click();

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(""))).sendKeys(userName);
				driver.findElement(By.id("")).sendKeys(password);
				driver.findElement(By.id("")).click();

				loggedin = driver.findElement(By.xpath("")).isDisplayed();
				System.out.println("Logged in");
			}

			return loggedin;

		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * This method will logout from application
	 * 
	 * @return true is logout is successful else false
	 */
	public static boolean logout() {

		try {

			driver.findElement(By.xpath("")).click();

			if (driver.findElement(By.xpath("")).isDisplayed()) {
				loggedin = false;
				return true;

			} else {

				return false;
			}

		} catch (Exception e) {

			return false;
		}
	}

}
