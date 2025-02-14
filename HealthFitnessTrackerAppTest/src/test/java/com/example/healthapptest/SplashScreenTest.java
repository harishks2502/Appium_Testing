package com.example.healthapptest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

class SplashScreenTest {

	private AppiumDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("Pixel 9 Pro XL API 35");
		options.setAppPackage("com.example.healthfitnesstracker");
		options.setAppActivity("com.example.healthfitnesstracker.SplashActivity");
		options.setAutomationName("UiAutomator2");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void testSplashScreen() {
		System.out.println("------ Checking Splash Screen ------");

		assertDoesNotThrow(
				() -> wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.id("com.example.healthfitnesstracker:id/splashLogo"))),
				"Splash Logo should be displayed");
		System.out.println("Splash Logo is displayed!");

		boolean isSplashGone = wait.withTimeout(Duration.ofSeconds(5)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.id("com.example.healthfitnesstracker:id/splashLogo")));

		assertTrue(isSplashGone, "Splash Screen should disappear smoothly!");
		System.out.println("Splash Screen disappeared smoothly!");
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
