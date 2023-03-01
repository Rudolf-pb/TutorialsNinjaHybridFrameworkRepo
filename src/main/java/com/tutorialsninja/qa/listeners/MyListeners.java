package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	String testname;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReported();
	}

	@Override
	public void onTestStart(ITestResult result) {
		testname = result.getName();
		extentTest = extentReport.createTest(testname);
		extentTest.log(Status.INFO, testname+" started Executing");
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testname = result.getName();
		
		extentTest.log(Status.PASS, testname+" got successfully Executed ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testname = result.getName();
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File srcScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshot\\"+testname+".png";
		
		try {
			FileHandler.copy(srcScreenShot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testname+" got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testname = result.getName();
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname+" got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String PathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File ExtentReport = new File(PathOfExtentReport);
		try {
			Desktop.getDesktop().browse(ExtentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
