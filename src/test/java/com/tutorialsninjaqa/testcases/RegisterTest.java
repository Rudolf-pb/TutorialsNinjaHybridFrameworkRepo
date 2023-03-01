package com.tutorialsninjaqa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageObject.AccountSuccessPage;
import com.tutorialsninja.qa.pageObject.HomePage;
import com.tutorialsninja.qa.pageObject.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;


public class RegisterTest extends Base {
	public WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	
	public RegisterTest() {
		super();
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		
		registerpage=homepage.selectedRegisterOption();
		
	}
	
	@Test(priority=1)
	public void VerifyRegisteringAccountWithMandatoryFields() {
		
		registerpage.EnterFirstName(dataProp.getProperty("firstName"));
		
		registerpage.EnterLastName(dataProp.getProperty("LastName"));
		
		registerpage.enterEmailAddressField(Utilities.generateDateandTime());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		
		registerpage.enterPassword(dataProp.getProperty("validPassword"));
		
		registerpage.enterConfirmPassword(dataProp.getProperty("validPassword"));
		
		registerpage.clickOnPrivacyPolicyButton();
		accountsuccesspage = registerpage.clickOnContinueButton();
		
		String actualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		
		String expectedSuccessHeading = "Your Account Has Been Created!";
		
		Assert.assertEquals(actualSuccessHeading, expectedSuccessHeading,"Account is not created");
		
	}
	
	
	@Test(priority=2)
	public void verifyRegisteringAccountsbyAllFields() {
		
		registerpage.EnterFirstName(dataProp.getProperty("firstName"));
		
		registerpage.EnterLastName(dataProp.getProperty("LastName"));
		
		registerpage.enterEmailAddressField(Utilities.generateDateandTime());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		
		registerpage.enterPassword(dataProp.getProperty("validPassword"));
		
		registerpage.enterConfirmPassword(dataProp.getProperty("validPassword"));
		
		registerpage.selectNewsLetter();
		registerpage.clickOnPrivacyPolicyButton();
		accountsuccesspage =registerpage.clickOnContinueButton();
		
		String actualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		
		String expectedSuccessHeading = "Your Account Has Been Created!";
		
		Assert.assertEquals(actualSuccessHeading, expectedSuccessHeading,"Account is not created");
		
		
	}
	
	@Test(priority=4,dependsOnMethods= {"verifyRegisteringAccountWithoutFillinganyDetails"})
	public void verifyRegisteringwithExistingEmailAddress() {
	
		registerpage.EnterFirstName(dataProp.getProperty("firstName"));
		
		registerpage.EnterLastName(dataProp.getProperty("LastName"));
		
		registerpage.enterEmailAddressField("mtkindia0@gmail.com");
		registerpage.enterTelephoneNumber("987654321");
		
		registerpage.enterPassword("1234567");
		
		registerpage.enterConfirmPassword("1234567");
		
		registerpage.selectNewsLetter();
		registerpage.clickOnPrivacyPolicyButton();
		registerpage.clickOnContinueButton();
		
		String actualWarningHeading = registerpage.retrieveDuplicateEmailAddressWarning();
		
		
		Assert.assertTrue(actualWarningHeading.contains("Warning: E-Mail Address is already registered!"));
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithoutFillinganyDetails() {
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String privacyWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		Assert.assertTrue(privacyWarningMessage.contains(" Warning: You must agree to the Privacy Policy!"));
		
		String firstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(firstNameWarning,"First Name must be between 1 and 32 characters!","first name warning is not displayed:");
		
		String LastnameWarning = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
		Assert.assertEquals(LastnameWarning, "Last Name must be between 1 and 32 characters!","Last name warning is not displayed");
		
	}

}
