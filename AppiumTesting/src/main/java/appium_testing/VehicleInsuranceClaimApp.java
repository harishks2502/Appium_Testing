package appium_testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class VehicleInsuranceClaimApp {

	public static void main(String[] args) {

		AppiumDriver driver = null;

		try {
			UiAutomator2Options options = new UiAutomator2Options();
			options.setPlatformName("Android");
			options.setDeviceName("OnePlus CPH2423");
			options.setAppPackage("com.example.vehicleinsuranceclaim");
			options.setAppActivity("com.example.vehicleinsuranceclaim.ui.MainActivity");
			options.setAutomationName("UiAutomator2");

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement registerButton = wait.until(ExpectedConditions
					.elementToBeClickable(By.id("com.example.vehicleinsuranceclaim:id/registerButton")));
			registerButton.click();

			WebElement fullNameField = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("com.example.vehicleinsuranceclaim:id/fullNameField")));
			fullNameField.sendKeys("Harish K S");

			WebElement emailField = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("com.example.vehicleinsuranceclaim:id/emailField")));
			emailField.sendKeys("harishks@gmail.com");

			WebElement passwordField = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("com.example.vehicleinsuranceclaim:id/passwordField")));
			passwordField.sendKeys("admin123");

			WebElement registerButton01 = wait.until(ExpectedConditions
					.elementToBeClickable(By.id("com.example.vehicleinsuranceclaim:id/registerButton")));
			registerButton01.click();

			System.out.println("User Registered Successfully");

			WebElement emailFieldLogin = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("com.example.vehicleinsuranceclaim:id/emailField")));
			emailFieldLogin.sendKeys("harishk@gmail.com");

			WebElement passwordFieldLogin = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("com.example.vehicleinsuranceclaim:id/passwordField")));
			passwordFieldLogin.sendKeys("admin123");

			WebElement loginButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.id("com.example.vehicleinsuranceclaim:id/loginButton")));
			loginButton.click();

			System.out.println("Login Successful");

			WebElement attachPolicyButton = wait.until(ExpectedConditions
					.elementToBeClickable(By.id("com.example.vehicleinsuranceclaim:id/attachPolicyButton")));
			attachPolicyButton.click();

			driver.quit();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
}
