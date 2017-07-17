package com.dashline.wda.category;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dashline.wda.main.Base;
import com.dashline.wda.util.Utility;
import com.dashline.wda.util.WebUIAutomation;

public class AddCategoryTest extends Base {
	SoftAssert softAssert = new SoftAssert();

	@DataProvider(name = "getTestData")
	public static Object[][] testData() throws Exception {
		return Utility.getTestData("category");

	}

	@Test(dataProvider = "getTestData")
	public void addCategoryTest(HashMap<String, String> hm) {
		if (!(Utility.checkRunmode(this.getClass().getSimpleName(), hm.get("Runmode")))) 
		{
			throw new SkipException("Runmode set to No");
		}
		// WebDriver driver4 = null;
		try {
			driver.get("http://139.162.165.152/admin/");

			// click on [mat icon] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-RIGHT_MAT_ICON-ADMIN_PAGE"),
					"Unable to click on [topright] button on WDA admin page");

			// click on [log out] button

			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-LOG_OUT-ADMIN_PAGE"),
					"Unable to locate [log out] button");
			
			
			// enter [email]
		String email = "admin@admin.aa";
		softAssert.assertTrue(WebUIAutomation.setText("INPUT-Email-LOG_IN_ADMIN_PAGE", email),
					"unable to enter [email] in email field");
			
			

			
			// enter [password]
			String password = "admin";
			softAssert.assertTrue(WebUIAutomation.setText("INPUT-Password-LOG_IN_ADMIN_PAGE", password),
					"unable to enter [password] field");

			// click on [login]
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-LOG_IN-ADMIN_PAGE"),
					"unable to click on [login] button");
			
			//switch windows
			driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/md-dialog-container/app-login-dialog/md-dialog-content/div/button")).click();
			Set<String> set = driver.getWindowHandles();
			System.out.println(set.size());
			Iterator<String> it = set.iterator();
			String pid=it.next();
			String cid=it.next();
			driver.switchTo().window(pid);
			
			driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/md-dialog-container/app-login-dialog/md-dialog-content/div/button")).click();
			
			Thread.sleep(5000);
 
			// click on [topleft] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-LEFT_MAT_ICON-ADMIN_PAGE"),
					"unable to click on [topleft] button");

			// click on [categories] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-CATEGORIES-CATEGORIES_PAGE"),
					"unable to click on [categories] button");

			// click on [add new item] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-ADD_NEW_ITEM-CATEGORIES_PAGE"),
					"unable to click on [add new item] button");

			// enter [new category] name
			String newcat = "bikes";
			softAssert.assertTrue(WebUIAutomation.setText("INPUT-NEW_CATEGORY_NAME-PRIORITY", (newcat)),
					"unable to type [new category] name");

			// enter [priority]
			String priority = "P3";
			softAssert.assertTrue(WebUIAutomation.setText("INPUT-PRIORITY-NEW_TAG_NAME", (priority)),
					"unable to type [priority] name");

			// enter [new tag] name
			String tag = "tw";
			softAssert.assertTrue(WebUIAutomation.setText("INPUT-NEW_TAG_NAME-CATEGORIES_PAGE ", (tag)),
					"unable to type [new tag] name");

			// click on [add] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-ADD-CATEGORIES_PAGE"),
					"unable to click on [add] button");
			
			//verify whether the list is there or not
			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
