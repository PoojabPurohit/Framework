package com.learnautomation.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.learnautomation.utilities.BrowserFactory;
import com.learnautomation.utilities.ConfiDataProvider;
import com.learnautomation.utilities.ExcelDataProvider;
import com.learnautomation.utilities.Helper;

public class BaseClass {

	public WebDriver driver = null;
	public ExcelDataProvider excel = null;
	public ConfiDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {
		Reporter.log("Setting up reports and test is getting ready", true); // true writes to console as well

		excel = new ExcelDataProvider();
		config = new ConfiDataProvider();
		ExtentSparkReporter extent = new ExtentSparkReporter(new File(
				System.getProperty("user.dir") + "/Reports/JpetStore" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);

		Reporter.log("Setting up done and test can be started", true);
	}

	@BeforeClass
	public void setUp() {
		Reporter.log("starting browser", true);

		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getQaURL());
		System.out.println(driver.getTitle());

		Reporter.log("Browser and application is up and running", true);
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {

		Reporter.log("Test is about to complete ", true);

		if (result.getStatus() == ITestResult.FAILURE) {
			// Helper.captureScreenShots(driver);
			logger.fail("Test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenShots(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenShots(driver)).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip("Test skipped",	
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenShots(driver)).build());
		}

		report.flush(); // to print report

		Reporter.log("Test completed and report generated", true);
	}
}
