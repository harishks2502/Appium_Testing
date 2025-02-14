package com.example.appiumjenkins;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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

public class MovieAppTest {

	private AppiumDriver driver;
	private WebDriverWait wait;

	@Before
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");

		caps.setCapability("appPackage", "com.example.movieapp");
		caps.setCapability("appActivity", "com.example.movieapp.ui.MainActivity");

		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void testMovieDetails() {
		WebElement title = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.movieapp:id/title")));
		System.out.println("App Title: " + title.getText());

		WebElement recyclerView = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.movieapp:id/moviesRecyclerView")));

		List<WebElement> movieItems = recyclerView.findElements(By.id("com.example.movieapp:id/titleView"));
		if (!movieItems.isEmpty()) {
			System.out.println("Movie Title: " + movieItems.get(2).getText());
		}

		List<WebElement> movieItemsDetail = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("com.example.movieapp:id/viewDetails")));
		if (!movieItemsDetail.isEmpty()) {
			movieItemsDetail.get(2).click();
		}

		WebElement imageView = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.movieapp:id/posterImageView")));

		boolean isImageDisplayed = imageView.isDisplayed();
		if (isImageDisplayed) {
			System.out.println("Image is displayed");
		} else {
			System.out.println("Image is not displayed");
		}

		String contentDescription = imageView.getAttribute("content-desc");
		System.out.println("Image Content Description: " + contentDescription);

		WebElement overviewTextView = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.movieapp:id/overviewTextView")));
		System.out.println("Movie Description: " + overviewTextView.getText());
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}