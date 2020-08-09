package com.learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;
import com.learnautomation.utilities.BrowserFactory;
import com.learnautomation.utilities.ExcelDataProvider;
import com.learnautomation.utilities.Helper;

public class LoginTest extends BaseClass {

	@Test
	public void loginApp() {

		logger = report.createTest("Login to JpetStore");

		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);

		logger.info("Starting application");

		loginpage.loginToApp(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));

		logger.pass("Login success");

	}

}
