package appium_testing;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTesting {

	public static void main(String[] args) throws InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Pixel 9 Pro XL API 35");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		try {
			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

			driver.get("https://www.apple.com/in");

			Thread.sleep(2000);

			WebElement learnMoreButton = driver.findElement(By.xpath("//a[contains(text(), 'Learn more')]"));

			System.out.println("learnMoreButton: " + learnMoreButton);

			learnMoreButton.click();

			Thread.sleep(2000);

			String currentUrl = driver.getCurrentUrl();

			System.out.println(" current URL :: " + currentUrl);

			if (currentUrl.equalsIgnoreCase("https://www.apple.com/in/iphone-16-pro/")) {
				System.out.println("Successfully navigated to: " + currentUrl);
			} else {
				System.out.println("URL did not change correctly.");
			}

			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
