package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	public WebDriver driver;
	public static Properties prop;
	public static Properties dataProp;
	
	public Base() {
		prop = new Properties();

		File propFile = new File(System.getProperty("user.dir")+"\\Config\\config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		dataProp=new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream fis = new FileInputStream(dataPropFile);
			dataProp.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver  = new ChromeDriver();
			
		}
		if(browser.equalsIgnoreCase("firefox")) {
			driver  = new FirefoxDriver();
			
		}
		if(browser.equalsIgnoreCase("edge")) {
			driver  = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_wait_Time));
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.Page_Load_time));
		
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
