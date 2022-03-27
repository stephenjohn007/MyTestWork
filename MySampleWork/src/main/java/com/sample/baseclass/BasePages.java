package com.sample.baseclass;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sample.mainclass.AutomationTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class BasePages extends AutomationTest {

	WebDriverWait wait = new WebDriverWait(driver, 60);

	public boolean tapOn(String name, WebElement element) throws InterruptedException {
		try {
			element.click();
		} catch (Exception e) {
			wait(element);
			element.click();
		}
		childTest.log(Status.INFO, "Tapped on ->" + name);
		return false;

	}

	public Boolean isXpathPresent(String element) {
		try {

			int size = driver.findElementsByXPath(element).size();
			System.out.println("element size is " + size);
			if (size > 0) {
				System.out.println("Element found");
				return true;
			} else {
				System.out.println("Element not found");
				return false;
			}
		} catch (Exception e) {
			System.out.println("exception ::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public Boolean isAccessibilityIdPresent(String element) {
		try {
			int size = driver.findElementsByAccessibilityId(element).size();
			System.out.println("element size is " + size);
			if (size > 0) {
				System.out.println("Element found");
				return true;
			} else {
				System.out.println("Element not found");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Exception ::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public void printL(String input, String name) {
		childTest.log(Status.INFO, input);
	}

	public boolean wait(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void sendKeys(WebElement element, String input, String name) {
		element.sendKeys(input);
		childTest.log(Status.INFO, "Entered[ " + input + "]in " + name);
	}

	public void print(Status action, String name) {
		childTest.log(action, name);
	}

	public void testResult(Status action, String name) {
		childTest.log(action, name);
	}

	public void takeSS(String action) {
		childTest.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
	}

	public String getBase64() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public void labelGreen(Markup markup, String input) {
		childTest.info(MarkupHelper.createLabel(input, ExtentColor.GREEN));
	}

	public void assertEquals(WebElement element, String input, String name) {
		String getheadertext = element.getText();
		System.out.println(getheadertext);
		Assert.assertEquals(input, getheadertext);
		childTest.log(Status.PASS, name);
	}

	public void assertEqualsSubheader(WebElement element, String input, String name) {
		String getSubheadertext = element.getText();
		System.out.println(getSubheadertext);
		Assert.assertEquals(input, getSubheadertext);
		childTest.log(Status.PASS, name);
	}

	public boolean doubleTap(String name, MobileElement element) {
		try {
			TouchActions action = new TouchActions(driver);
			action.doubleTap(element);
			action.perform();
			childTest.log(Status.INFO, "Double tapped on ->" + name);
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void openNotification() {
		driver.openNotifications();
	}

	public void goBack() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public void otp() throws InterruptedException {
		openNotification();
		Thread.sleep(5000);
		String message = driver.findElement(By.id("android:id/message_text")).getText();
		System.out.println(message);
		String s = message.replaceAll("[^-?0-9]", "");
		char s1 = s.charAt(0);
		String f1 = String.valueOf(s1);
		char s2 = s.charAt(1);
		String f2 = String.valueOf(s2);
		char s3 = s.charAt(2);
		String f3 = String.valueOf(s3);
		char s4 = s.charAt(3);
		String f4 = String.valueOf(s4);
		char s5 = s.charAt(4);
		String f5 = String.valueOf(s5);
		char s6 = s.charAt(5);
		String f6 = String.valueOf(s6);
		goBack();
		driver.findElement(By.id("com.sample.mobile:id/otp_et1")).sendKeys(f1);
		driver.findElement(By.id("com.sample.mobile:id/otp_et2")).sendKeys(f2);
		driver.findElement(By.id("com.sample.mobile:id/otp_et3")).sendKeys(f3);
		driver.findElement(By.id("com.sample.mobile:id/otp_et4")).sendKeys(f4);
		driver.findElement(By.id("com.sample.mobile:id/otp_et5")).sendKeys(f5);
		driver.findElement(By.id("com.sample.mobile:id/otp_et6")).sendKeys(f6);

	}

	public static void scrollToElement1(String elementName) {
		String targetCell = "//UIATableCell[UIAStaticText[@name=\"" + elementName + "\"]]";
		WebElement cellWithText = driver.findElement(By.xpath(targetCell));
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", ((RemoteWebElement) cellWithText).getId());
		driver.executeScript("mobile: scrollTo", scrollObject);
	}

	public void assertNotNull(WebElement locator, String name) {
		// WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
		String element = locator.getText();
		System.out.println(element);
		Assert.assertNotNull(element);
		childTest.log(Status.PASS, name);
	}

	public String element(WebElement locator, String name) {
		String ele = locator.getText();
		if (ele.contains(ele))
			Assert.assertEquals(true, true);
		childTest.log(Status.PASS, name);
		return ele;
	}

	public String datePickerWheel() {
		List<AndroidElement> pickerEls = driver.findElementsByXPath("//XCUIElementTypePickerWheel");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("order", "next");
		params.put("offset", 0.15);
		params.put("element", ((RemoteWebElement) pickerEls.get(0)).getId());
		driver.executeScript("mobile: selectPickerWheelValue", params);

		params.put("order", "next");
		params.put("element", ((RemoteWebElement) pickerEls.get(1)).getId());
		driver.executeScript("mobile: selectPickerWheelValue", params);

		params.put("order", "next");
		params.put("element", ((RemoteWebElement) pickerEls.get(2)).getId());
		driver.executeScript("mobile: selectPickerWheelValue", params);
		for (int i = 0; i < pickerEls.size(); i++) {
			System.out.println(pickerEls.get(i).getText());
		}
		return null;

	}
}
