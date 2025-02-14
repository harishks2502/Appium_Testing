package com.example.ecommerceapptest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

class CartFragmentTest {

	private AppiumDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("Pixel 9 Pro XL API 35");
		options.setAppPackage("com.example.ecommerceapp");
		options.setAppActivity("com.example.ecommerceapp.ui.MainActivity");
		options.setAutomationName("UiAutomator2");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void testCartFragment() {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginEmailIDField")))
				.sendKeys("harish@gmail.com");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginPasswordField")))
				.sendKeys("admin");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/loginButton"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/cartSectionButton")))
				.click();

		WebElement cartFragment = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/cartSectionLayout")));
		assertNotNull(cartFragment, "Cart Fragment should be visible after navigating");
		System.out.println("Successfully navigated to Cart Fragment");
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
