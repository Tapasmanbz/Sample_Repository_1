package com.dashline.wda.location;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dashline.wda.main.Base;
import com.dashline.wda.util.Utility;
import com.dashline.wda.util.WebUIAutomation;

public class AddnewLocation extends Base {
	SoftAssert softassert = new SoftAssert();

	@DataProvider(name = "getTestData")
	public static Object[][] testData() throws Exception {
		return Utility.getTestData("location");
	}

	@Test(dataProvider = "getTestData")
	public void addNewLocation(HashMap<String, String> hm) {
		if (!(Utility.checkRunmode(this.getClass().getSimpleName(), hm.get("Runmode")))) {
			throw new SkipException("Runmode set to No");
		}

		try {

			driver.get("http://139.162.165.152/admin/");

			// click on [mat icon] button
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-RIGHT_MAT_ICON-ADMIN_PAGE"),
					"unable to click on [mat icon] button");

			// click on [log out] button

			softassert.assertTrue(WebUIAutomation.clickObj("BTN-LOG_OUT-ADMIN_PAGE"),
					"unable to click on [log out] button");

			// enter [email]
			String email = "admin@admin.aa";
			softassert.assertTrue(WebUIAutomation.setText("INPUT-Email-LOG_IN_ADMIN_PAGE", email),
					"unable to enter [email]");

			// enter [password]
			String password = "admin";
			softassert.assertTrue(WebUIAutomation.setText("INPUT-Password-LOG_IN_ADMIN_PAGE", password),
					"unable to enter [password]");

			// click on [login]
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-LOG_IN-ADMIN_PAGE"),
					"unable to click on [login] button");

			// switch windows
			driver.findElement(By
					.xpath("html/body/div[2]/div[2]/div[2]/md-dialog-container/app-login-dialog/md-dialog-content/div/button"))
					.click();

			Set<String> set = driver.getWindowHandles();
			System.out.println(set.size());
			Iterator<String> it = set.iterator();
			String pid = it.next();
			// String cid = it.next();
			driver.switchTo().window(pid);

			driver.findElement(By
					.xpath("html/body/div[2]/div[2]/div[2]/md-dialog-container/app-login-dialog/md-dialog-content/div/button"))
					.click();
			Thread.sleep(1000);
			// click on [topleft] button
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-LEFT_MAT_ICON-ADMIN_PAGE"),
					"unable to click on [topleft] button");

			// CLICK ON [location] button
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-LOCATION-LOCATION_PAGE"),
					"unable to click on [location]");

			// click on [add new location] button
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-ADD_NEW_ITEM-LOCATION_PAGE"),
					"unable to click on [add new location] button");
			// Thread.sleep(2000);

			// enter [new location]
			String loc = "store #456";
			softassert.assertTrue(WebUIAutomation.setText("INPUT-NEW_LOCATION_NAME-LOCATION_PAGE", loc),
					"unable to enter on [new location] field");

			// click on [edit] button to set location
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-EDIT-LOCATION_PAGE"),
					"unable to click on [edit] button");

			// click on [map]
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-CLICK_ON_MAP-LOCATION_PAGE"),
					"unable to click on [map]");

			// click on save button
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-SAVE_LOCATION-LOCATION_PAGE"),
					"unable to click on [save] button");
			Thread.sleep(500);

			// click to [edit] button to save work hours
			// driver.findElement(By.xpath(".//*[@id='cdk-overlay-16']/md-dialog-container/app-add-loc-dialog/md-dialog-content/div/div[5]/app-time-picker/div/button")).click();

			softassert.assertTrue(WebUIAutomation.clickObj("BTN-EDIT_HOUR-LOCATION_PAGE"),
					"unable to click on [edit] button");

			// click on [open time]
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-OPEN_TIME-LOCATION_PAGE "),
					"unable to click on [open time] button");

			// select [open time]
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-SELECT_OPEN_TIME-LOCATION_PAGE"),
					"unable to select [open time]");

			// click on [close time]
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-CLOSE_TIME-LOCATION_PAGE"),
					"unable to click on [close time]");

			// select [close time]
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-SELECT_CLOSE_TIME-LOCATION_PAGE"),
					"unable to select [close time]");

			// select [day off]
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-SELECT_DAY_OFF-LOCATION_PAGE"),
					"unable to click on [day off]");

			// [save] work hours
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-SAVE_WORK_HOURS-LOCATION_PAGE"),
					"unable to [save] save work hours");

			// [add] a new location
			softassert.assertTrue(WebUIAutomation.clickObj("BTN-ADD_NEW_LOCATION-LOCATION_PAGE"),
					"unable to [add] new location");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}