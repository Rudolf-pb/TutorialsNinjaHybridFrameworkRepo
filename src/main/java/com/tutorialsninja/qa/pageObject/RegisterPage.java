package com.tutorialsninja.qa.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="newsletter")
	private WebElement newsLetterOption;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@type='checkbox']")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	//Action class
	
	public void EnterFirstName(String FirstName) {
		firstNameField.sendKeys(FirstName);
	}
	
	public void EnterLastName(String LastName) {
		lastNameField.sendKeys(LastName);
	}
	
	public void enterEmailAddressField(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String psswordText) {
		passwordField.sendKeys(psswordText);
	}
	
	public void enterConfirmPassword(String psswordText) {
		passwordConfirmField.sendKeys(psswordText);
	}
	
	public void selectNewsLetter() {
		newsLetterOption.click();
	}
	
	public void clickOnPrivacyPolicyButton() {
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText=duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	

}
