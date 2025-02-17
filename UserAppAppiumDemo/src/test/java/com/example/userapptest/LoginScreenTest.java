package com.example.userapptest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

class LoginScreenTest {

	private static IOSDriver driver;
	private static WebDriverWait wait;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		XCUITestOptions options = new XCUITestOptions();
		options.setPlatformName("iOS");
		options.setPlatformVersion("16.0");
		options.setDeviceName("iPhone 14 Pro");
		options.setAutomationName("XCUITest");
		options.setApp(
				"/Users/admin/Library/Developer/Xcode/DerivedData/UserApp-bujabygyankrhfdnmtgtyczajhgk/Build/Products/Debug-iphonesimulator/UserApp.app");
		options.setNoReset(true);
		options.setNewCommandTimeout(Duration.ofSeconds(60));

		driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	void testLoginScreen() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeTextField")))
				.sendKeys("HarishKS");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeSecureTextField")))
				.sendKeys("admin");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeButton[@name='Login']")))
				.click();

		String welcomeMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to the Dashboard']")))
				.getText();
		assertEquals(welcomeMessage, "Welcome to the Dashboard!");
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
