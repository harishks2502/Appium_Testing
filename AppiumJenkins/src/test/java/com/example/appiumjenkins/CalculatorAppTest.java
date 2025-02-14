package com.example.appiumjenkins;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CalculatorAppTest {

	private AppiumDriver driver;
	private WebDriverWait wait;

	@Before
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");

		caps.setCapability("appPackage", "com.example.calculator");
		caps.setCapability("appActivity", "com.example.calculator.MainActivity");

		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void testAddition() {
		WebElement firstNumberField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/firstNumberField")));
		System.out.println("First Number Field: " + firstNumberField);
		firstNumberField.sendKeys("5");

		WebElement secondNumberField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/secondNumberField")));
		secondNumberField.sendKeys("3");

		WebElement addButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.calculator:id/addButton")));
		addButton.click();

		WebElement result = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.calculator:id/addResult")));

		String resultText = result.getText();
		System.out.println(resultText);
		assertEquals("Result: 8.0", resultText);
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
