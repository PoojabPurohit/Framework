package com.learnautomation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import freemarker.template.SimpleDate;

public class Helper {

	//screenshots, alerts,frames,wondows,sync issue, javascript executer
	public static String captureScreenShots(WebDriver driver)
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath= System.getProperty("user.dir")+"./Screenshots/"+getCurrentDateTime()+"_Defect.png";
		try {
			FileHandler.copy(src, new File(screenShotPath));
			System.out.println("Screenshot captured");
		} catch (IOException e) {
			System.out.println("Unable to capture screenshot"+e.getMessage());
		}
		return screenShotPath;
	}
	
	public static String getCurrentDateTime()
	{
		DateFormat dateFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate=new Date(); 
		return dateFormat.format(currentDate);
		
	}
}
