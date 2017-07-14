package com.dashline.wda.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.dashline.wda.util.MSExcelAutomation;
import com.dashline.wda.util.Utility;

public class Base {

	public static boolean loggedin = false;

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;

	public static MSExcelAutomation datatable = null;
	public static String executionBrowser = null;
	public static Properties config = null;
	public static Properties OR = null;

	public String wdaApplicationURL = null;
	public static String filePath = null;
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@BeforeSuite
	public void beforeSuite() throws IOException {

		if (datatable == null) {
			// Initialize Test data File
			datatable = new MSExcelAutomation(
					System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
							+ "resources" + File.separator + "testdata" + File.separator + "Controller.xls");
		}

		if (config == null) {

			// Load configuration
			config = new Properties();
			FileInputStream fp = new FileInputStream(System.getProperty("user.dir") + File.separator + "src"
					+ File.separator + "test" + File.separator + "resources" + File.separator + "config.properties");
			config.load(fp);

		}

		if (OR == null) {

			OR = new Properties();
			FileInputStream fpObjectRepo = new FileInputStream(new File(".").getCanonicalPath() + File.separator
					+ File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator
					+ config.getProperty("OBJECT_REPOSITORY_FILE"));
			OR.load(fpObjectRepo);
		}

	}

	@BeforeTest
	public void setExecutionBrowser() {

		executionBrowser = config.getProperty("browser");
		System.out.println("Execution Browser:" + executionBrowser);

		if (Utility.checkTestCaseRunmode(this.getClass().getSimpleName())) {

			// Initialization
			setUp();

		} else {
			throw new SkipException("Test Runmode set to No");
		}

	}

	public void setUp() {

		String os = System.getProperty("os.name");

		System.out.println("Setup Test Browser for ::" + this.getClass().getName());

		try {

			if (executionBrowser.equalsIgnoreCase("firefox")) {

				if (os.equals("Linux")) {

					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + File.separator + "lib" + File.separator + "geckodriver");

				} else if (os.startsWith("Windows")) {

					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "lib"
							+ File.separator + "geckodriver.exe");

				}

				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);

				driver = new FirefoxDriver(capabilities);
				driver.manage().window().maximize();

			} else if (executionBrowser.equalsIgnoreCase("chrome")) {

				if (os.equals("Linux")) {

					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + File.separator + "lib" + File.separator + "chromedriver");

				} else if (os.startsWith("Windows")) {

					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator
							+ "lib" + File.separator + "chromedriver.exe");

				}

				ChromeOptions options = new ChromeOptions();

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);

				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.addArguments("start-maximized");
				options.addArguments("--disable-notifications");

				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				driver = new ChromeDriver(capabilities);

			} else if (executionBrowser.equalsIgnoreCase("ie")) {

				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(ieCapabilities);

			}

			// Set Implicit Wait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// Navigate to application URL
			wdaApplicationURL = "http://" + config.getProperty("serverUsername") + ":"
					+ config.getProperty("serverPassword") + "@" + config.getProperty("testURL");
			System.out.println("Executing Tests on server " + wdaApplicationURL);
			driver.get(wdaApplicationURL);

			// Thread.sleep(3000);
			driver.switchTo().alert().accept();

			driver.get(wdaApplicationURL);

			wait = new WebDriverWait(driver, 20);

		} catch (SkipException e) {

			// driver = null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@AfterTest
	public void closeBrowser() {

		if (driver != null) {
			driver.quit();
		}

	}

//	@AfterSuite
//	public void afterSuite() {
//		// System.out.println("in afterSuite");
//	}

}
