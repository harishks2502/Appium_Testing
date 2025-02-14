package appium_testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ShoppingAppTest {
	private static AppiumDriver driver;

	public static void main(String[] args) {
		try {
			setUp();
			performVisibleSwipe();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tearDown();
		}
	}

	public static void setUp() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("Pixel 9 Pro XL API 35");
		options.setAppPackage("com.example.shoppingapp");
		options.setAppActivity("com.example.shoppingapp.MainActivity");
		options.setAutomationName("UiAutomator2");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
	}

	public static void performVisibleSwipe() {
		System.out.println("Waiting for RecyclerView to be visible...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement recyclerView = wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.className("androidx.recyclerview.widget.RecyclerView")));

		System.out.println("RecyclerView found. Performing swipe...");

		int startX = recyclerView.getLocation().getX() + (int) (recyclerView.getSize().getWidth() * 0.8);
		int endX = recyclerView.getLocation().getX() + (int) (recyclerView.getSize().getWidth() * 0.2);
		int y = recyclerView.getLocation().getY() + recyclerView.getSize().getHeight() / 2;

		System.out.println("startX: " + startX + ", endX: " + endX + ", y: " + y);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, y));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, y));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe));

		System.out.println("Swipe completed.");
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
