package appium_testing;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.URL;

public class TodoAppTest {

	public static void main(String[] args) throws InterruptedException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");

		caps.setCapability("appPackage", "com.example.todoapplication");
		caps.setCapability("appActivity", "com.example.todoapplication.ui.MainActivity");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

		try {
			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

			Thread.sleep(3000);

			WebElement addTaskButton = driver.findElement(By.id("com.example.todoapplication:id/fab_add"));
			addTaskButton.click();

			Thread.sleep(2000);

			WebElement taskText = driver.findElement(By.id("com.example.todoapplication:id/taskText"));
			taskText.sendKeys("Running");

			WebElement taskDescriptionText = driver
					.findElement(By.id("com.example.todoapplication:id/taskDescriptionText"));
			taskDescriptionText.sendKeys("Run 5 km everyday");

			WebElement addButton = driver.findElement(By.id("com.example.todoapplication:id/addButton"));
			addButton.click();

			Thread.sleep(2000);

			WebElement titleTextView = driver
					.findElement(By.xpath("//android.widget.TextView[contains(@text,'Running')]"));
			WebElement descriptionTextView = driver
					.findElement(By.xpath("//android.widget.TextView[contains(@text,'Run 5 km everyday')]"));

			if (titleTextView.isDisplayed() && descriptionTextView.isDisplayed()) {
				System.out.println("Successfully navigated to AddTaskFragment!!!");
				System.out.println("Task & Description added Successfully!");
			} else {
				System.out.println("Task or Description was NOT added.");
			}

			driver.quit();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

	}

}
