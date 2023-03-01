package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter { 
	public static ExtentReports generateExtentReported() {
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Reports");
		
		sparkReporter.config().setDocumentTitle("TN Automation Results");
		
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configprop = new Properties();
		
		File configPropFile = new File(System.getProperty("user.dir")+"\\Config\\config.properties");
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(configPropFile);
			configprop.load(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configprop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configprop.getProperty("browser"));
		extentReport.setSystemInfo("Email", configprop.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configprop.getProperty("validPassword"));
		
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}

}
