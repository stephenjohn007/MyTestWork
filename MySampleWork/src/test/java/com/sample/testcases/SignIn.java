package com.sample.testcases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.sample.baseclass.BasePages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignIn extends BasePages {

	public SignIn() throws IOException {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.sample.mobile:id/edt_email")
	public WebElement emailID;
	@AndroidFindBy(id = "com.sample.mobile:id/edt_password")
	public WebElement password;
	@AndroidFindBy(id = "com.sample.mobile:id/btn_login")
	public WebElement loginButton;
	@AndroidFindBy(id = "com.sample.mobile:id/skip_tv")
	public WebElement skipPage;
	@AndroidFindBy(id = "com.sample.mobile:id/back_arrow_mobileNoFragment")
	public WebElement skipPhoneNumber;
	@AndroidFindBy(id = "com.sample.mobile:id/contry_code_tv")
	public WebElement countryCode;
	@AndroidFindBy(id = "com.sample.mobile:id/search_et")
	public WebElement countryName;
	@AndroidFindBy(id = "com.sample.mobile:id/country_list_layout")
	public WebElement countryList;
	@AndroidFindBy(id = "com.sample.mobile:id/phone_et")
	public WebElement enterPhoneNumber;
	@AndroidFindBy(id = "com.sample.mobile:id/next_bt")
	public WebElement nextButton;
	@AndroidFindBy(accessibility = "Inbound label chooser")
	public WebElement inbox;

	public void signIn() throws InterruptedException {
		sendKeys(emailID, prop.getProperty("EmailID"), "EmailID");
		sendKeys(password, prop.getProperty("Password"), "Password");
		tapOn("Login Button", loginButton);
		tapOn("SkipPage", skipPage);
		tapOn("Country code", countryCode);
		sendKeys(countryName, "India", "Search Country");
		tapOn("Country name", countryList);
		sendKeys(enterPhoneNumber, prop.getProperty("PhoneNumber"), "Enter the phone number");
		tapOn("Next", nextButton);
		otp();
		try {
			tapOn("Skip phone number", skipPhoneNumber);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		wait(inbox);
		assertEquals(inbox, "Inbox", "Inbox view");

	}

}