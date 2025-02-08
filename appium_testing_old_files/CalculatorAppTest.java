package appium_testing;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CalculatorAppTest {

	public static void main(String[] args) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

		capabilities.setCapability("appPackage", "com.example.calculator");
		capabilities.setCapability("appActivity", ".MainActivity");

		try {

			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

			WebElement firstNumber = driver.findElement(By.id("com.example.calculator:id/etFirstNumber"));
			WebElement secondNumber = driver.findElement(By.id("com.example.calculator:id/etSecondNumber"));
			WebElement addButton = driver.findElement(By.id("com.example.calculator:id/btnAdd"));
			WebElement resultView = driver.findElement(By.id("com.example.calculator:id/tvResult"));

			firstNumber.sendKeys("5");
			secondNumber.sendKeys("3");
			addButton.click();

			String resultText = resultView.getText();
			if (resultText.contains("8")) {
				System.out.println("Test Passed: Result is correct.");
			} else {
				System.out.println("Test Failed: Incorrect result.");
			}

			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}