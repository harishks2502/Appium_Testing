package com.example.ecommerceapptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

class ProductsFragmentTest {

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

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginEmailIDField")))
				.sendKeys("harish@gmail.com");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/loginPasswordField")))
				.sendKeys("admin");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/loginButton"))).click();
	}

	@Test
	public void testScrollThroughProductList() {
		System.out.println("\n------ Scrolling through Products List Test Case ------");

		WebElement recyclerView = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/productsRecyclerView")));
		System.out.println("RecyclerView found. Scrolling...");

		boolean reachedEnd = false;
		String lastProductName = "";

		while (!reachedEnd) {
			List<WebElement> productItems = recyclerView.findElements(By.className("android.widget.TextView"));
			if (productItems.isEmpty()) {
				System.out.println("Product List is not visible after scrolling!");
				return;
			}
			String lastVisibleProduct = productItems.get(productItems.size() - 1).getText();
			scrollDown(recyclerView);
			if (lastVisibleProduct.equals(lastProductName))
				reachedEnd = true;
			else
				lastProductName = lastVisibleProduct;
		}

		System.out.println("Scrolling Test Passed: Successfully scrolled through the product list.");
	}

	public void scrollDown(WebElement recyclerView) {
		int startX = recyclerView.getLocation().getX() + recyclerView.getSize().getWidth() / 2;
		int startY = recyclerView.getLocation().getY() + (int) (recyclerView.getSize().getHeight() * 0.8);
		int endY = recyclerView.getLocation().getY() + (int) (recyclerView.getSize().getHeight() * 0.2);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(
				finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe));
	}

	@Test
	public void testSortingByPrice() {
		System.out.println("\n------ Sorting by Price Test Case ------");

		WebElement priceSpinner = wait.until(
				ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/priceFilterSpinner")));
		priceSpinner.click();

		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.CheckedTextView")));

		try {
			WebElement lowToHighOption = driver
					.findElement(By.xpath("//android.widget.CheckedTextView[@text='Price: Low to High']"));
			lowToHighOption.click();
		} catch (NoSuchElementException e) {
			System.out.println("Low to High option not found using XPath. Trying UIAutomator...");
			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Low to High\")")).click();
		}

		System.out.println("Sorting applied: Low to High");

		shortWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/productsRecyclerView")));

		List<WebElement> productPrices = driver.findElements(By.id("com.example.ecommerceapp:id/productPrice"));
		assertFalse(productPrices.isEmpty(), "No products found after applying sorting!");

		List<Double> prices = new ArrayList<>();
		for (WebElement priceElement : productPrices) {
			String priceText = priceElement.getText().trim();
			String numericPrice = priceText.replaceAll("[^0-9.]", "");
			prices.add(Double.parseDouble(numericPrice));
		}

		List<Double> sortedPrices = new ArrayList<>(prices);
		Collections.sort(sortedPrices);

		assertEquals(sortedPrices, prices, "Sorting by price failed! Prices are not in ascending order.");

		System.out.println("Sorting test passed: Prices are sorted in ascending order.");
	}

	@Test
	public void testFilteringByCategory() {
		System.out.println("------ Filtering by Category Test Case ------");

		WebElement categorySpinner = wait.until(
				ExpectedConditions.elementToBeClickable(By.id("com.example.ecommerceapp:id/categoryFilterSpinner")));
		categorySpinner.click();

		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.CheckedTextView")));

		try {
			WebElement electronicsOption = driver
					.findElement(By.xpath("//android.widget.CheckedTextView[@text='Electronics']"));
			electronicsOption.click();
		} catch (NoSuchElementException e) {
			System.out.println("Electronics option not found using XPath. Trying UIAutomator...");
			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Electronics\")")).click();
		}

		System.out.println("Filtering applied: Electronics");

		shortWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("com.example.ecommerceapp:id/productsRecyclerView")));

		List<WebElement> productCategories = driver.findElements(By.id("com.example.ecommerceapp:id/productCategory"));
		assertFalse(productCategories.isEmpty(), "No products found after applying filter!");

		for (WebElement categoryElement : productCategories) {
			String categoryText = categoryElement.getText().trim().replace("Category: ", "");
			assertEquals("Electronics", categoryText, "Filtering by category failed! Unexpected product found.");
		}

		System.out.println("Filtering test passed: Only 'Electronics' products are displayed.");
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
