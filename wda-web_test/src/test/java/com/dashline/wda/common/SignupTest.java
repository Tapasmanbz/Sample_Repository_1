package com.dashline.wda.common;

import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dashline.wda.main.Base;
import com.dashline.wda.util.Utility;

public class SignupTest extends Base {

	@DataProvider(name = "getTestData")
	public static Object[][] testData() throws Exception {

		return Utility.getTestData("Signup");
	}

	@Test(dataProvider = "getTestData")
	public void signupTest(HashMap<String, String> hm) {

		if (!(Utility.checkRunmode(this.getClass().getSimpleName(), hm.get("Runmode")))) {

			throw new SkipException("Runmode set to No");
		}

		logger.info("Inside Test method ...");

		if (driver != null)
			driver.navigate().refresh();
		else
			setUp();

	}
}
