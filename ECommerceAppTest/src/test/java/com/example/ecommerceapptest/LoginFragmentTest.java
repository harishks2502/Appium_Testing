package com.example.ecommerceapptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

class LoginFragmentTest {

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
	public void testLoginWithValidCredentials() {
		System.out.println("------ Valid Credentials Test Case ------");

		WebElement emailField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginEmailIDField")));
		emailField.sendKeys("harish@gmail.com");

		WebElement passwordField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginPasswordField")));
		passwordField.sendKeys("admin");

		WebElement forgotPasswordField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/forgotPasswordLink")));
		assertTrue(forgotPasswordField.isDisplayed(), "Forgot Password field is not visible");
		System.out.println("Forgot Password field is visible");

		WebElement signUpField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/registerLink")));
		assertTrue(signUpField.isDisplayed(), "SignUp field is not visible");
		System.out.println("SignUp field is visible");

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/loginButton")));
		loginButton.click();

		WebElement productsFragment = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/productsLayout")));
		assertNotNull(productsFragment, "Products Fragment should be visible after successful login");
		System.out.println("Successfully Logged in to Product Fragment");

		try {
			WebElement toastMessage = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//android.widget.Toast[contains(@text, 'Login Successful')]")));
			String toastText = toastMessage.getText();
			assertEquals("Login Successful", toastText, "Toast message should indicate 'Login Successful'");
			System.out.println("Toast Message: '" + toastText + "' successfully displayed");
		} catch (Exception e) {
			System.out.println("Toast Message not found or timed out");
		}
	}

	@Test
	public void testLoginWithInvalidCredentials() {
		System.out.println("\n------ Invalid Credentials Test Case ------");

		WebElement emailField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginEmailIDField")));
		emailField.sendKeys("harishks2502@gmail.com");

		WebElement passwordField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginPasswordField")));
		passwordField.sendKeys("admin@2502");

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/loginButton")));
		loginButton.click();

		try {
			WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//android.widget.Toast[contains(@text, 'Invalid Credentials')]")));
			String toastText = toastMessage.getText();
			assertEquals("Invalid Credentials", toastText, "Toast message should indicate 'Invalid Credentials'");
			System.out.println("Toast Message: '" + toastText + "' successfully displayed");
		} catch (Exception e) {
			System.out.println("Toast Message not found or timed out");
		}
	}

	@Test
	public void testLoginWithEmptyFields() {
		System.out.println("\n------ Empty Fields Test Case ------");

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/loginButton")));
		loginButton.click();

		try {
			WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//android.widget.Toast[contains(@text, 'Both fields are required')]")));
			String toastText = toastMessage.getText();
			assertEquals("Both fields are required", toastText,
					"Toast message should indicate 'Both fields are required'");
			System.out.println("Toast Message: '" + toastText + "' successfully displayed");
		} catch (Exception e) {
			System.out.println("Toast Message not found or timed out");
		}
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
