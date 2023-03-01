package com.tutorialsninjaqa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageObject.AccountPage;
import com.tutorialsninja.qa.pageObject.HomePage;
import com.tutorialsninja.qa.pageObject.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	public WebDriver driver;
	LoginPage loginpage;
	
	public LoginTest() {
		super();
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@BeforeMethod
	public void Setup() {
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		
		HomePage homepage = new HomePage(driver);
		
		homepage.clickOnMyAccount();
		loginpage = homepage.selectLoginOption();
		
	}
	
	@Test(priority=1,dataProvider="ValidCredentialsSupplier")
	public void verifyWithValidCredentials(String email, String Password) {
		
		loginpage.enterEmailAdress(email);
		loginpage.enterPassword(Password);
		AccountPage accountpage=loginpage.clickOnLoginButton();
		
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption());
		
	}
	
	@DataProvider(name="ValidCredentialsSupplier")
	public Object[][] supplyData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		
		return data;
	}
	
	@Test(priority=2)
	public void verifyWithInvalidCredentials() {
		
		loginpage.enterEmailAdress(Utilities.generateDateandTime());
		
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		
		loginpage.clickOnLoginButton();
		
		
		
		String ExpectedErrorMessage =  dataProp.getProperty("emailPasswordNotMatchingMessage");
		
		String ActualErrorMessage = loginpage.retrieveWarningMessageTest();
	
		Assert.assertTrue(ExpectedErrorMessage.contains(ActualErrorMessage));		
		
	}
	
	
	

}
