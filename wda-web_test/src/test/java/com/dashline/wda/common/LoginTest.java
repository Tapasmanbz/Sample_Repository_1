package com.dashline.wda.common;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dashline.wda.main.Base;
import com.dashline.wda.util.Utility;
import com.dashline.wda.util.WebUIAutomation;

public class LoginTest extends Base {

	@DataProvider(name = "getTestData")
	public static Object[][] testData() throws Exception {

		return Utility.getTestData("Login");
	}

	@Test(dataProvider = "getTestData")
	public void loginTest(HashMap<String, String> hm) {

		if (!(Utility.checkRunmode(this.getClass().getSimpleName(), hm.get("Runmode")))) {

			throw new SkipException("Runmode set to No");
		}

		try {

			// Clicking on [SIGN IN] button
			// WebElement wbel_signin =
			// WebUIAutomation.getObject("BTN-SIGN_IN-LANGING_PAGE");
			// wait.until(ExpectedConditions.elementToBeClickable(wbel_signin)).click();

			WebUIAutomation.clickObj("BTN-SIGN_IN-LANGING_PAGE");

			// [SIGN IN] POP UP

			// Enter text in [Email field] of "SIGN IN" popup
			WebUIAutomation.setText("INPUT-Email-SIGN_IN_LANDING_PAGE", "testing.tekno2@gmail.com");

			// Enter text in [Password field] of "SIGN IN" popup
			WebUIAutomation.setText("INPUT-Password-SIGN_IN_LANDING_PAGE", "testing");

		} catch (Exception e) {

			System.out.println("In Test Exception - " + e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() {

		if (driver != null)
			driver.navigate().refresh();
		else
			setUp();

	}

}
