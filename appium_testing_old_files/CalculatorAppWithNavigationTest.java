package appium_testing;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CalculatorAppWithNavigationTest {

	public static void main(String[] args) throws InterruptedException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");
		caps.setCapability("appPackage", "com.example.calculator");
		caps.setCapability("appActivity", "com.example.calculator.MainActivity");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

		try {
			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

			WebElement num1Field = driver.findElement(By.id("com.example.calculator:id/etFirstNumber"));
			System.out.println("NumField 1 : " + num1Field);
			num1Field.sendKeys("5");

			WebElement num2Field = driver.findElement(By.id("com.example.calculator:id/etSecondNumber"));
			num2Field.sendKeys("3");

			WebElement addButton = driver.findElement(By.id("com.example.calculator:id/btnAdd"));
			addButton.click();

			WebElement navigateButton = driver.findElement(By.id("com.example.calculator:id/btnNavigate"));
			navigateButton.click();

			Thread.sleep(3000);
			
			WebElement resultView = driver.findElement(By.id("com.example.calculator:id/textViewMessage"));
			
			String resultText = resultView.getText();
			System.out.println(" resultText :: " + resultText);
			
			if (resultText.equalsIgnoreCase("Welcome to the Second Screen!")) {
				System.out.println("Successfully navigated to SecondActivity!!!");
			} else {
				System.out.println("Not able to navigate!!!.");
			}

			System.out.println("Navigation to the second screen was successful!");

			driver.quit();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
	
}
