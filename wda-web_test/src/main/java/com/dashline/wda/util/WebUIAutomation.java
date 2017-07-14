package com.dashline.wda.util;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.dashline.wda.main.Base;

public class WebUIAutomation extends Base {

	/**
	 * This method will return locator of web element
	 * 
	 * @param strElement
	 * @param prop
	 * @return By locator
	 * @throws Exception
	 */
	private static By getLocator(String strElement, Properties prop) throws Exception {

		// retrieve the specified object from the object list
		String locator = prop.getProperty(strElement).trim();

		// extract the locator type and value from the object
		String locatorType = locator.substring(1, locator.indexOf(">")).trim();
		String locatorValue = locator.substring(locatorType.length() + 2).trim();

		// for testing and debugging purposes
		System.out.println(
				"Retrieving object of type '" + locatorType + "' and value '" + locatorValue + "' from the object map");

		// return a instance of the By class based on the type of the locator
		// this By can be used by the browser object in the actual test
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
		case "class":
			return By.className(locatorValue);
		case "tagname":
		case "tag":
			return By.className(locatorValue);
		case "linktext":
		case "link":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
		case "css":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			throw new Exception("Unknown locator type '" + locatorType + "'");
		}
	}

	/**
	 * This function is used to identify the object on the Application
	 * 
	 * @param xpathKey
	 *            - unique sudo name which we have kept for every object on the
	 *            web page
	 * @return WebElement
	 */
	public static WebElement getObject(String locatorKey) {

		WebElement obj = null;

		try {
			By locator = getLocator(locatorKey, OR);
			obj = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			obj = null;
		}
		return obj;
	}

	/**
	 * This is a overloaded function is used to identify the object on the
	 * Application
	 * 
	 * @param pathKey
	 * @param prop
	 * @return
	 */
	public static WebElement getObject(String locatorKey, Properties prop) {

		WebElement obj = null;

		try {
			obj = driver.findElement(getLocator(locatorKey, prop));
		} catch (Exception e) {
			obj = null;
		}
		return obj;
	}

	/**
	 * This function is used to identify the objects on the Application
	 * 
	 * @author
	 * @param xpathKeyOfElements
	 * @return List<WebElement>
	 */
	public static List<WebElement> getObjects(String locatorKeyOfElements) {

		List<WebElement> obj;

		try {
			By locator = getLocator(locatorKeyOfElements, OR);
			obj = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {

			obj = null;
		}

		return obj;

	}

	/**
	 * This is an overloaded function which is used to check that Web Element
	 * exist on the page or not
	 * 
	 * @param xpathKey
	 * @return boolean true/false
	 */
	public static boolean isElementPresent(String locatorKey) {

		try {
			WebElement webElem = getObject(locatorKey);
			if (!(webElem == null)) {

				return true;

			} else {

				return false;
			}

		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * This is an overloaded function which is used to click elements on the web
	 * page
	 * 
	 * @param xpathKey
	 * @return boolean - true/false
	 */
	public static boolean clickObj(String locatorKey) {

		try {
			WebElement webElem = getObject(locatorKey);

			if (!(webElem == null)) {

				wait.until(ExpectedConditions.elementToBeClickable(webElem)).click();
				return true;

			} else {

				return false;
			}
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This is an overloaded function which is used to enter value in the text
	 * box
	 * 
	 * @param xpathKey
	 * @param value
	 *            which needs to enter
	 * @return boolean - true/false
	 */
	public static boolean setText(String locatorKey, String value) {

		try {

			WebElement webElem = getObject(locatorKey);

			if (!(webElem == null)) {

				// Clearing the text box before typing value
				webElem.clear();

				// typing the value in the text box
				webElem.sendKeys(value);

				return true;

			} else {

				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This is an overloaded function used to wait for the element till it loads
	 * completely
	 * 
	 * @param xpathKey
	 * @param maxTime
	 *            maximum time in seconds for which we want the web driver to
	 *            wait for the element
	 * @return boolean - true/false
	 */
	public static boolean isObjPresent(String locatorKey, int maxTime) {

		try {
			for (int i = 0; i <= maxTime; i++) {

				if (isElementPresent(locatorKey)) {

					return true;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

		return false;
	}

	/**
	 * This is an overloaded function which is used to select value from drop
	 * down
	 * 
	 * @param driver
	 *            instance
	 * @param xpathKey
	 * @param strValue
	 *            value which needs to be selected from drop down
	 * @return boolean - true/false
	 */
	public static boolean selectValueFromDrpDwn(String locatorKey, String strValue) {

		try {
			Select element;
			if (!strValue.equals("")) {

				element = new Select(getObject(locatorKey));
				element.selectByVisibleText(strValue);
			}

			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method will select from dropdown which is not a select box.
	 * 
	 * @param containerKey
	 * @param optionsKey
	 * @param valueToSelect
	 * @return
	 */
	public static boolean selectOptionFromDrpDwn(String containerKey, String optionsKey, String valueToSelect) {

		try {

			// Click on container
			getObject(containerKey).click();

			// Select Options
			List<WebElement> options = getObjects(optionsKey);
			for (WebElement wbel : options) {

				if ((wbel.getText().trim()).equalsIgnoreCase(valueToSelect)) {

					return true;
				}

			}

			return false;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
}
