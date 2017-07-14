package com.dashline.wda.category;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dashline.wda.main.Base;
import com.dashline.wda.util.Utility;
import com.dashline.wda.util.WebUIAutomation;

public class AddCategoryTest extends Base{
	SoftAssert softAssert = new SoftAssert();
	 @DataProvider(name = "getTestData")
	 public static Object[][] testData() throws Exception 
	 {
		 return Utility.getTestData("category");
		 
	 }
   @Test(dataProvider = "getTestData")
	   public void addCategoryTest(HashMap<String, String> hm)
	   {
	   if(!(Utility.checkRunmode(this.getClass().getSimpleName(), hm.get("Runmode"))))
		 {
			 throw new SkipException("Runmode set to No");
		 }
	   WebDriver driver4 = null;
	   try {
		   driver4.get("http://139.162.165.152/admin/");
		   //click on [top right] button
		   softAssert.assertTrue(WebUIAutomation.clickObj("BTN-TOP_RIGHT-ADMIN_PAGE"),"Unable to click on [top right] button on WDA admin page");
		   
           //check on [log in] popup
		   softAssert.assertTrue(WebUIAutomation.isElementPresent("POPUP-LOG_IN-ADMIN_PAGE"),"Unable to locate [log in] popup");
		
		   //enter [email]
		   String email = "admin@admin.aa";
		   softAssert.assertTrue(WebUIAutomation.setText("INPUT-Email-LOG_IN_LANDING_PAGE",hm.get(email) ),"unable to enter [email] in email field");
		   
		   //enter [password]
		   String password="admin";
		   softAssert.assertTrue(WebUIAutomation.setText("INPUT-Password-LOG_IN_ADMIN_PAGE",hm.get(password)), "unable to enter [password] field");
		   
		   //click on [login]
		   softAssert.assertTrue(WebUIAutomation.clickObj("INPUT-Email-LOG_IN_ADMIN_PAGE"),"unable to click on [login] button");
		   
		   //click on [topleft] button
		   softAssert.assertTrue(WebUIAutomation.clickObj("BTN-TOP_LEFT-ADMIN_PAGE"), "unable to click on [topleft] button");
		   
		   //check whether the 
	} catch (Exception e) {
		// TODO: handle exception
	}
	   }
   }
}
