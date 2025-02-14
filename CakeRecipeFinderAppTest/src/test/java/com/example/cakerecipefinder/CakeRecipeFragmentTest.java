package com.example.cakerecipefinder;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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

class CakeRecipeFragmentTest {

	private AppiumDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("Pixel 9 Pro XL API 35");
		options.setAppPackage("com.example.cakerecipefinder");
		options.setAppActivity("com.example.cakerecipefinder.ui.MainActivity");
		options.setAutomationName("UiAutomator2");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	void testCakeRecipe() {
		List<WebElement> cakeItemsDetail = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.id("com.example.cakerecipefinder:id/viewDetails")));
		if (!cakeItemsDetail.isEmpty()) {
			cakeItemsDetail.get(2).click();
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement cakeImage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeImage")));
		assertTrue(cakeImage.isDisplayed(), "Cake image is not displayed.");

		WebElement cakeTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeTitle")));
		assertTrue(cakeTitle.isDisplayed(), "Cake title is not displayed.");

		WebElement cakeCategory = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeCategory")));
		assertTrue(cakeCategory.isDisplayed(), "Cake category is not displayed.");

		WebElement cakeArea = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeArea")));
		assertTrue(cakeArea.isDisplayed(), "Cake area is not displayed.");

		WebElement cakeInstructions = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeInstructions")));
		assertTrue(cakeInstructions.isDisplayed(), "Cake instructions are not displayed.");

		WebElement cakeTags = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeTags")));
		assertTrue(cakeTags.isDisplayed(), "Cake tags are not displayed.");

		WebElement cakeReference = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeReference")));
		assertTrue(cakeReference.isDisplayed(), "Cake reference is not displayed.");

		WebElement cakeSource = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/cakeSource")));
		assertTrue(cakeSource.isDisplayed(), "Cake source is not displayed.");

	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
