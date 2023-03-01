package com.tutorialsninjaqa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageObject.HomePage;
import com.tutorialsninja.qa.pageObject.SearchPage;

public class SearchTest extends Base {
	public WebDriver driver;
	HomePage homepage;
	SearchPage searchpage;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		homepage = new HomePage(driver);
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProducts() {
	
		homepage.enterProductNameinSearchBox(dataProp.getProperty("validProduct"));
		searchpage= homepage.ClickOnsearchButton();
		
		Assert.assertTrue(searchpage.displayStatusHPValidProduct());		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		homepage.enterProductNameinSearchBox(dataProp.getProperty("invalidProduct"));
		searchpage = homepage.ClickOnsearchButton();
		
		String ActualNonAvailableMessage = searchpage.retrieveNoProductMessage();
		Assert.assertEquals(ActualNonAvailableMessage, "There is no product that matches the search criteria.","No product message in searhc results is displayed");
	}
	
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		
		searchpage = homepage.ClickOnsearchButton();
		
		String ActualNonAvailableMessage = searchpage.retrieveNoProductMessage();
		Assert.assertEquals(ActualNonAvailableMessage, "There is no product that matches the search criteria.","No product message in searhc results is displayed");
	}
	

}
