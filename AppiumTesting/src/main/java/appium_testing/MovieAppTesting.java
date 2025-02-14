package appium_testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MovieAppTesting {

	public static void main(String[] args) {

		AppiumDriver driver = null;

		try {
			UiAutomator2Options options = new UiAutomator2Options();
			options.setPlatformName("Android");
			options.setDeviceName("Pixel 9 Pro XL API 35");
			options.setAppPackage("com.example.movieapp");
			options.setAppActivity("com.example.movieapp.ui.MainActivity");
			options.setAutomationName("UiAutomator2");

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

			WebElement imageView = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("com.example.movieapp:id/posterImageView")));

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
