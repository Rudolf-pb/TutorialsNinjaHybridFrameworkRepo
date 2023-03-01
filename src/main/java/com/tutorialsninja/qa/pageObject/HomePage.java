package com.tutorialsninja.qa.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	
	@FindBy(linkText="Login")
	private WebElement LoginOption;
	
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement SearchButton;
	
	//Actions
	
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectedRegisterOption() {
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductNameinSearchBox(String Product) {
		searchBoxField.sendKeys(Product);
	}
	
	public SearchPage ClickOnsearchButton() {
		SearchButton.click();
		return new SearchPage(driver);
	}

}
