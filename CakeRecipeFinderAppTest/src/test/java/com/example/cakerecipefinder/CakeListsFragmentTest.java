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

class CakeListsFragmentTest {

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
	void testCakeLists() {
		WebElement titleView = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/titleView")));
		assertTrue(titleView.isDisplayed(), "Title 'Cake Lists' is not displayed.");

		WebElement recyclerView = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("cakelistsRecyclerView")));
		assertTrue(recyclerView.isDisplayed(), "RecyclerView is not displayed.");

		List<WebElement> cakeItems = recyclerView.findElements(By.id("com.example.cakerecipefinder:id/titleView"));
		assertTrue(cakeItems.size() > 0, "RecyclerView has no items.");

		List<WebElement> cakeItemsDetail = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.id("com.example.cakerecipefinder:id/viewDetails")));
		if (!cakeItemsDetail.isEmpty()) {
			cakeItemsDetail.get(2).click();
		}

		WebElement recipePage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.cakerecipefinder:id/productsLayout")));
		assertTrue(recipePage.isDisplayed(), "Recipe page did not load.");
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
