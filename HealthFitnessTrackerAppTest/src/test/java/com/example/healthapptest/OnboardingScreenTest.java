package com.example.healthapptest;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

class OnboardingScreenTest {

	private AndroidDriver driver;
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
	public void testOnboardingScreen() {
		System.out.println("------ Checking Onboarding Screen ------");

		WebElement viewPager = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.healthfitnesstracker:id/viewPager")));
		assertNotNull(viewPager, "Onboarding ViewPager should be displayed");
		System.out.println("Onboarding ViewPager found!");

		swipeLeft(driver);
		swipeLeft(driver);

		WebElement skipButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.healthfitnesstracker:id/btnSkip")));
		assertNotNull(skipButton, "Skip button should be displayed");
		skipButton.click();
		System.out.println("Skip button clicked. Navigating to Dashboard...");

		boolean isDashboardLoaded = wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.id("com.example.healthfitnesstracker:id/viewPager")));
		assertTrue(isDashboardLoaded, "Dashboard should be loaded after skipping onboarding");
		System.out.println("Dashboard loaded successfully!");
	}

	public static void swipeLeft(AndroidDriver driver) {
		Dimension screenSize = driver.manage().window().getSize();
		int startX = (int) (screenSize.width * 0.75);
		int endX = (int) (screenSize.width * 0.25);
		int startY = screenSize.height / 2;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, startY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(List.of(swipe));
		System.out.println("Swiped left on Onboarding ViewPager.");
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
