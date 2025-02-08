package appium_testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MovieAppTesting {

	public static void main(String[] args) throws InterruptedException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");

		caps.setCapability("appPackage", "com.example.movieapp");
		caps.setCapability("appActivity", "com.example.movieapp.ui.MainActivity");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

		try {
			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

			Thread.sleep(3000);

			WebElement title = driver.findElement(By.id("com.example.movieapp:id/title"));
			System.out.println("App Title: " + title.getText());

			WebElement recyclerView = driver.findElement(By.id("com.example.movieapp:id/moviesRecyclerView"));

			List<WebElement> movieItems = recyclerView.findElements(By.id("com.example.movieapp:id/titleView"));
			if (!movieItems.isEmpty()) {
				System.out.println("Movie Title: " + movieItems.get(2).getText());
			}

			List<WebElement> movieItemsDetail = recyclerView.findElements(By.id("com.example.movieapp:id/viewDetails"));
			if (!movieItemsDetail.isEmpty()) {
				movieItemsDetail.get(2).click();
			}

			Thread.sleep(2000);

			WebElement imageView = driver.findElement(By.id("com.example.movieapp:id/posterImageView"));

			boolean isImageDisplayed = imageView.isDisplayed();
			if(isImageDisplayed) {
				System.out.println("Image is displayed");
			} else {
				System.out.println("Image is not displayed");
			}

			String contentDescription = imageView.getAttribute("content-desc");
			System.out.println("Image Content Description: " + contentDescription);

			WebElement overviewTextView = driver.findElement(By.id("com.example.movieapp:id/overviewTextView"));
			System.out.println("Movie Description: " + overviewTextView.getText());

			driver.quit();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

	}

}
