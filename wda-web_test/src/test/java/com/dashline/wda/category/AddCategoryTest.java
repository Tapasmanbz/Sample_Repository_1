package com.dashline.wda.category;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		if (!(Utility.checkRunmode(this.getClass().getSimpleName(), hm.get("Runmode")))) {
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

			// switch windows
			driver.findElement(By
					.xpath("html/body/div[2]/div[2]/div[2]/md-dialog-container/app-login-dialog/md-dialog-content/div/button"))
					.click();
			Set<String> set = driver.getWindowHandles();
			System.out.println(set.size());
			Iterator<String> it = set.iterator();
			String pid = it.next();
			String cid = it.next();
			driver.switchTo().window(pid);
			driver.switchTo().window(pid);

			driver.findElement(By
					.xpath("html/body/div[2]/div[2]/div[2]/md-dialog-container/app-login-dialog/md-dialog-content/div/button"))
					.click();

			Thread.sleep(2000);

			// click on [topleft] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-LEFT_MAT_ICON-ADMIN_PAGE"),
					"unable to click on [topleft] button");

			// click on [categories] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-CATEGORIES-CATEGORIES_PAGE"),
					"unable to click on [categories] button");
			Thread.sleep(200);

			// click on [add new item] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-ADD_NEW_ITEM-CATEGORIES_PAGE"),
					"unable to click on [add new item] button");

			Thread.sleep(200);

			// enter [new category] name
			String newcat = "c6";
			softAssert.assertTrue(WebUIAutomation.setText("INPUT-NEW_CATEGORY_NAME-PRIORITY", (newcat)),
					"unable to type [new category] name");

			Thread.sleep(500);

			// enter [priority]
			String priority = "0";
			softAssert.assertTrue(WebUIAutomation.setText("INPUT-PRIORITY-NEW_TAG_NAME", (priority)),
					"unable to type [priority] name");

			Thread.sleep(500);

			// enter [new tag] name
			String tag = "accesories";
			softAssert.assertTrue(WebUIAutomation.setText("INPUT-NEW_TAG_NAME-CATEGORIES_PAGE ", (tag)),
					"unable to type [new tag] name");
			Thread.sleep(500);

			// click on [add] button
			softAssert.assertTrue(WebUIAutomation.clickObj("BTN-ADD-CATEGORIES_PAGE"),
					"unable to click on [add] button");

			Thread.sleep(2000);

			// verify whether the made category is created or not
			WebElement wbel_search = driver.findElement(By.xpath(
					"html/body/app-root/md-sidenav-container/div[4]/div/div/app-categories/div/app-resource-table/div/div/button"));

			wbel_search.click();

			driver.findElement(By
					.xpath("html/body/app-root/md-sidenav-container/div[4]/div/div/app-categories/div/app-resource-table/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div[2]/datatable-body-cell[1]/div/div"))
					.sendKeys(newcat);

			List<WebElement> category = driver.findElements(By.xpath(
					"html/body/app-root/md-sidenav-container/div[4]/div/div/app-categories/div/app-resource-table/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div[2]/datatable-body-cell[1]/div/div"));

			boolean ispr = false;
			for (WebElement e : category) {
				if (e.getText().trim().equals(newcat)) {
					System.out.println("is there");
					ispr = true;
					break;
				}
			}
			System.err.println(ispr);
			softAssert.assertTrue(ispr, "Category not present");
			Thread.sleep(2000);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
