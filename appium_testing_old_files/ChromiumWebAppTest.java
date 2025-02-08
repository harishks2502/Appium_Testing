package appium_testing;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ChromiumWebAppTest {

	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://www.apple.com/in");

			WebElement learnMoreButton = driver.findElement(By.xpath("//a[contains(text(),'Learn more')]"));

			learnMoreButton.click();

			System.out.println("Page Title: " + driver.getTitle());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
